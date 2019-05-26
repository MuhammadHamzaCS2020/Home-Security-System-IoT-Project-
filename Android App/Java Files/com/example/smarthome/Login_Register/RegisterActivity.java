package com.example.smarthome.Login_Register;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarthome.Utility.Firebase_Methods;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity
{
    private static String TAG = "RegisterActivity";
    private Context mContext = RegisterActivity.this;
    private static final int ACTIVITY_NUM=3;

    private String SUsername;
    private String SEmail;
    private String SPassword;
    private String append;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    private EditText musername;
    private EditText memail;
    private EditText mpassword;
    private Button mregister;
    private ProgressBar mprogressbar;
    private TextView mprogresstext;
    private Firebase_Methods firebase_methods;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        Log.d(TAG,"onCreate:called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        musername = findViewById(R.id.RegisterInputFullName);
        mpassword = findViewById(R.id.RegisterInputPassword);
        memail = findViewById(R.id.RegisterInputEmail);
        mprogressbar = findViewById(R.id.RegisterProgessBar);
        mprogresstext = findViewById(R.id.RegisterProgressText);
        mprogressbar.setVisibility(View.GONE);
        mprogresstext.setVisibility(View.GONE);

        firebase_methods = new Firebase_Methods(mContext);
        setupFirebaseAuth();
        init();

      //  mAuth.signOut();

    }

    // ************************* Firebase *******************************************^^^^^^^^^^^^^^^^^^^^^^^^66

    private void init()
    {
        mregister = findViewById(R.id.btn_Register);
        mregister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d(TAG,"onClick: Register Button called");
                SUsername = musername.getText().toString();
                SEmail = memail.getText().toString();
                SPassword = mpassword.getText().toString();

                if(checkInputs(SUsername,SEmail,SPassword))
                {
                    mprogressbar.setVisibility(View.VISIBLE);
                    mprogresstext.setVisibility(View.VISIBLE);
                    Log.d(TAG,"RegisterNewEmail called below"+SUsername+" "+SEmail+" "+SPassword);
                    boolean check=firebase_methods.registerNewEmail(SUsername,SEmail,SPassword);
                    if(check==false)
                    {
                        Log.d(TAG,"check==false...removing the view of progressbar and please wait text");
                        mprogressbar.setVisibility(View.GONE);
                        mprogresstext.setVisibility(View.GONE);
                        Toast.makeText(mContext,R.string.register_authentication_fail,Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });
    }

    /**
     * Checking if username already exist in the database.
     * @param username
     */
    public void checkIfUsernameExists(final String username)
    {
        Log.d(TAG,"checkIfUsernameExists:called"+username);

        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        Query query = mDatabaseReference
                .child(getString(R.string.dbname_users))
                .orderByChild(getString(R.string.field_username))
                .equalTo(username);

        query.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                    for(DataSnapshot singleSnapShot:dataSnapshot.getChildren())
                    {
                        Log.d(TAG,"checkIfUsernameExist:addListenerForSingleValueEvent on Query:called"+singleSnapShot.getValue(User.class).getUsername());
                        append = databaseReference.push().getKey().substring(3,10);
                        Log.d(TAG,"Username already exist. Appending a new string");
                    }

                    String mUsername;
                    mUsername = username+append;
                    firebase_methods.addNewUser(SEmail,mUsername,"","","");
                    Toast.makeText(mContext,"Sign up Successfull, Sending Verifcaiton Email",Toast.LENGTH_SHORT).show();

                    mAuth.signOut();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }


    private boolean checkInputs(String username,String email,String password)
    {
        if(username.equals("") || email.equals("") || password.equals(""))
        {
            Toast.makeText(mContext,"Please fill out all fields",Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            return true;
        }
    }

    private void setupFirebaseAuth()
    {
        Log.d(TAG,"setupFirebaseAuth:called");
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        mAuthStateListener = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null)
                {
                    String uid = user.getUid();
                    Log.d(TAG,"onAuthStateChanged: signed_in"+uid);

                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener()
                    {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {
                            checkIfUsernameExists(SUsername);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError)
                        {

                        }
                    });

                    finish();
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
