package com.example.smarthome.Utility;

import android.Manifest;

public class Permissions
{
    public static final String[] PERMISSION = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };


    public static final String[] CAMERA_PERMISSION =
    {
            Manifest.permission.CAMERA
    };

    public static final String[] WRITE_EXTERNAL_STORAGE_PERMISSION =
    {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    public static final String[] READ_EXTERNAL_STORAGE_PERMISSION =
    {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
}
