package com.example.smarthome.Utility;




import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import com.example.instagramcloneapp.Home.HomeActivity;
import com.example.instagramcloneapp.Likes.LikesActivity;
import com.example.instagramcloneapp.Profile.ProfileActivity;
import com.example.instagramcloneapp.R;
import com.example.instagramcloneapp.Search.SearchActivity;
import com.example.instagramcloneapp.Share.ShareActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import androidx.annotation.NonNull;


public class BottomNavigationViewHelper
{
    private static String TAG = "BottomNavigationViewHelper";
    public static void setUpBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx)
    {
        Log.d(TAG,"setUpBottomNavigationView:Called");
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }

    public static void enableNavigation(final Context context, BottomNavigationViewEx view)
    {
        Log.d(TAG,"enableNavigation:called");
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch(menuItem.getItemId())
                {
                    case R.id.ic_house:
                        Intent intent0 = new Intent(context, HomeActivity.class);
                        context.startActivity(intent0);
                        break;

                    case R.id.ic_search:
                        Intent intent1 = new Intent(context, SearchActivity.class);
                        context.startActivity(intent1);
                        break;


                    case R.id.ic_circle:
                        Intent intent2 = new Intent(context, ShareActivity.class);
                        context.startActivity(intent2);
                        break;

                    case R.id.ic_alert:
                        Intent intent3 = new Intent(context, LikesActivity.class);
                        context.startActivity(intent3);
                        break;

                    case R.id.ic_android:
                        Intent intent4 = new Intent(context, ProfileActivity.class);
                        context.startActivity(intent4);
                        break;
                }
                return false;
            }
        });
    }
}
