package com.example.smarthome.Utility;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.instagramcloneapp.Models.User;
import com.example.instagramcloneapp.Models.UserAccountSettings;
import com.example.instagramcloneapp.Models.UserSettings;
import com.example.instagramcloneapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;

public class Firebase_Methods
{
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    private Context mContext;
    private static String TAG = "Firebase_Methods";
    private String userID;
    private boolean check = true;


    public Firebase_Methods(Context mContext)
    {
        Log.d(TAG,"Fire_Method Class Accessed");

        mAuth = FirebaseAuth.getInstance();
        this.mContext = mContext;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        if (mAuth.getCurrentUser() != null)
        {
            userID = mAuth.getCurrentUser().getUid();
        }
    }

    public boolean registerNewEmail(final String username, final String email, String password) {
        Log.d(TAG, "registerNewEmail:called");

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            Log.d(TAG, "createUserWithEmail:success");
                            sendingVerifcationEmail();
                            userID = mAuth.getCurrentUser().getUid();
                        }
                        else
                         {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            check = false;
                        }

                        // ...
                    }
                });

        return check;
    }

    public void sendingVerifcationEmail()
    {
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>()
            {
                @Override
                public void onComplete(@NonNull Task<Void> task)
                {
                    if (!task.isSuccessful())
                    {
                        Log.d(TAG, "sendEmailVerifcation: onComplete :called");
                        Toast.makeText(mContext, "Unable to send verifcation email.", Toast.LENGTH_SHORT).show();
                    } else {

                    }
                }
            });
        }
    }

    public void addNewUser(String email, String username, String description, String website, String profile_photo)
    {
        User user = new User(userID, 1, email, StringManipulation.condenseUsername(username));
        databaseReference.child(mContext.getString(R.string.dbname_users))
                .child(userID)
                .setValue(user);

        UserAccountSettings userAccountSettings = new UserAccountSettings(
                description,
                username,
                0,
                0,
                0,
                profile_photo,
                StringManipulation.condenseUsername(username),
                website
        );

        databaseReference.child(mContext.getString(R.string.dbname_user_account_settings))
                .child(userID)
                .setValue(userAccountSettings);
    }

    /**
     * Retreieve the account settings for the current user that is logged in,
     * Database: User Account Settings Node
     *
     * @param dataSnapshot
     * @return
     */
    public UserSettings getUserSettings(DataSnapshot dataSnapshot)
    {
        UserAccountSettings userAccountSettings = new UserAccountSettings();
        User user = new User();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            //User Account Settings Node
            if (ds.getKey().equals(mContext.getString(R.string.dbname_user_account_settings)))
            {
                Log.d(TAG, "UserSettings(UserAccountSetings:Nodes" + ds);

                try {
                    userAccountSettings.setDisplay_name(ds.child(userID)
                            .getValue(UserAccountSettings.class)
                            .getDisplay_name());

                    userAccountSettings.setUsername(ds.child(userID)
                            .getValue(UserAccountSettings.class)
                            .getUsername());

                    userAccountSettings.setWebsite(ds.child(userID)
                            .getValue(UserAccountSettings.class)
                            .getWebsite());

                    userAccountSettings.setDescription(ds.child(userID)
                            .getValue(UserAccountSettings.class)
                            .getDescription());

                    userAccountSettings.setProfile_photo(ds.child(userID)
                            .getValue(UserAccountSettings.class)
                            .getProfile_photo());

                    userAccountSettings.setPosts(ds.child(userID)
                            .getValue(UserAccountSettings.class)
                            .getPosts());

                    userAccountSettings.setFollowers(ds.child(userID)
                            .getValue(UserAccountSettings.class)
                            .getFollowers());

                    userAccountSettings.setFollowing(ds.child(userID)
                            .getValue(UserAccountSettings.class)
                            .getFollowing());

                } catch (NullPointerException e) {
                    Log.e(TAG, "NULLPOINTEREXCEPTION" + e);
                }

            }
            //User Account Settings Node
            if (ds.getKey().equals(mContext.getString(R.string.dbname_users)))
            {
                Log.d(TAG, "UserSettings(UserAccountSetings:Nodes" + ds);

                try {
                    user.setUser_id(ds.child(userID)
                            .getValue(User.class)
                            .getUser_id());

                    user.setUsername(ds.child(userID)
                            .getValue(User.class)
                            .getUsername());

                    user.setEmail(ds.child(userID)
                            .getValue(User.class)
                            .getEmail());

                    user.setPhone_number(ds.child(userID)
                            .getValue(User.class)
                            .getPhone_number());
                } catch (NullPointerException e) {
                    Log.e(TAG, "NULLPOINTEREXCEPTION" + e);
                    Log.d(TAG, "Retreiving User Data: " + user.toString());
                }

            }
        }
        return new UserSettings(user,userAccountSettings);

    }

    public void updateUserAccountSettings(String displayName, String website,String description,long phoneNumber)
    {
        Log.d(TAG,"updateUserAccountSettings: updating user accounting settings "+displayName+" "+website+" "+description+" "+phoneNumber);

        if(displayName!=null)
        {
            databaseReference.child(mContext.getString(R.string.dbname_user_account_settings))
                    .child(userID)
                    .child(mContext.getString(R.string.field_displayName))
                    .setValue(displayName);
        }

        if(website!=null)
        {
            databaseReference.child(mContext.getString(R.string.dbname_user_account_settings))
                    .child(userID)
                    .child(mContext.getString(R.string.field_website))
                    .setValue(website);
        }


        if(description!=null)
        {
            databaseReference.child(mContext.getString(R.string.dbname_user_account_settings))
                    .child(userID)
                    .child(mContext.getString(R.string.field_description))
                    .setValue(description);
        }

        if(phoneNumber!=0)
        {
            databaseReference.child(mContext.getString(R.string.dbname_users))
                    .child(userID)
                    .child(mContext.getString(R.string.field_phoneNumber))
                    .setValue(phoneNumber);
        }

    }

    public void updateUsername(String username)
    {
        databaseReference.child(mContext.getString(R.string.dbname_users))
                .child(userID)
                .child(mContext.getString(R.string.field_username))
                .setValue(username);

        databaseReference.child(mContext.getString(R.string.dbname_user_account_settings))
                .child(userID)
                .child(mContext.getString(R.string.field_username))
                .setValue(username);
    }

    public void updateEmail(String email)
    {
        databaseReference.child(mContext.getString(R.string.dbname_users))
                .child(userID)
                .child(mContext.getString(R.string.field_email))
                .setValue(email);

    }
}
