package com.example.smarthome.Login_Register;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instagramcloneapp.Home.HomeActivity;
import com.example.instagramcloneapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity
{
    private static String TAG = "LoginActivity";
    private Context mContext = LoginActivity.this;
    private static final int ACTIVITY_NUM=3;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private ProgressBar mProgressBar;
    private EditText email,password;
    private TextView pleasewait;
    private TextView registeration_link;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        Log.d(TAG,"onCreate:called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mProgressBar = findViewById(R.id.loginProgessBar);
        email = findViewById(R.id.loginInputEmail);
        password = findViewById(R.id.loginInputPassword);
        pleasewait = findViewById(R.id.loginPleaseWait);
        registeration_link = findViewById(R.id.registeration_link);

        mProgressBar.setVisibility(View.GONE);
        pleasewait.setVisibility(View.GONE);

        setupFirebaseAuth();
        init();
    }


    // ************************* Firebase *******************************************^^^^^^^^^^^^^^^^^^^^^^^^66


    private boolean isStringNull(String s)
    {
        if(s.equals(""))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private void init()
    {
        Button login_btn = findViewById(R.id.btn_Login);
        login_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String SEmail = email.getText().toString();
                String SPassword = password.getText().toString();


                if (isStringNull(SEmail) || isStringNull(SPassword))
                {
                    Toast.makeText(mContext, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mProgressBar.setVisibility(View.VISIBLE);
                    pleasewait.setVisibility(View.VISIBLE);

                    signIn(SEmail, SPassword);  // Method to Sign in
                }
            }
        });


        /**
         * Registration Link on Log in Page
         */
        registeration_link.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d(TAG,"Registeration Link Clicked:called");
                Intent intent = new Intent(mContext,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void signIn(String SEmail, String SPassword)
    {
        mAuth.signInWithEmailAndPassword(SEmail, SPassword)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success"+R.string.authentication_success);

                            FirebaseUser user = mAuth.getCurrentUser();
                            try
                            {
                                if(user.isEmailVerified())
                                {
                                    Log.d(TAG, "Logging in: Directing to Home Activity.");
                                    mProgressBar.setVisibility(View.GONE);
                                    pleasewait.setVisibility(View.GONE);
                                    Toast.makeText(LoginActivity.this, "Authentication Successful.",
                                            Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(mContext, HomeActivity.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Log.d(TAG, "Login Failed:Check inbox.");
                                    Toast.makeText(mContext, "Check your email inbox for verification", Toast.LENGTH_SHORT).show();
                                    mProgressBar.setVisibility(View.GONE);
                                    pleasewait.setVisibility(View.GONE);

                                }
                            }
                            catch(NullPointerException e)
                            {
                                Log.e(TAG,"NullPointerException:couldn't login");
                            }


                            mAuth.updateCurrentUser(user);
                        }
                        else
                        {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());

                            mProgressBar.setVisibility(View.GONE);
                            pleasewait.setVisibility(View.GONE);

                            Toast.makeText(LoginActivity.this, "Authentication Failed.",
                                    Toast.LENGTH_SHORT).show();


                        }



                    }
                });


      /*  if(mAuth.getCurrentUser()!=null)
        {
            Intent intent = new Intent(mContext,HomeActivity.class);
            startActivity(intent);
            finish(); // Not coming back to log in screen unless signed out by user.
        }*/
    }

    private void setupFirebaseAuth()
    {
        Log.d(TAG,"setupFirebaseAuth:called");
        FirebaseApp.initializeApp(mContext);
        mAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null)
                {
                    // Name, email address, and profile photo Url
                    String name = user.getDisplayName();
                    String email = user.getEmail();
                    Uri photoUrl = user.getPhotoUrl();

                    // Check if user's email is verified
                    boolean emailVerified = user.isEmailVerified();

                    // The user's ID, unique to the Firebase project. Do NOT use this value to
                    // authenticate with your backend server, if you have one. Use
                    // FirebaseUser.getIdToken() instead.
                    String uid = user.getUid();

                    Log.d(TAG,"onAuthStateChanged: signed_in"+uid);
                }
                else
                {
                    Log.d(TAG,"onAuthStateChanged: signed_out");
                }
            }
        };

    }

    @Override
    public void onStart()
    {
        /*super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);*/
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        if(mAuthStateListener!=null)
        {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }


}
