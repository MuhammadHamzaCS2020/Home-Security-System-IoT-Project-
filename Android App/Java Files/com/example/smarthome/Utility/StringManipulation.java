package com.example.smarthome.Utility;

public class StringManipulation
{
    public static String expandUsername(String username)
    {
        return username.replace("."," ");
    }

    public static String condenseUsername(String username)
    {
        return username.replace(" ",".");
    }

}
