package com.example.smarthome.Models;

public class UserSettings
{
    private User user;
    private UserAccountSettings userAccountSettings;

    public UserSettings(User user, UserAccountSettings userAccountSettings)
    {
        this.user = user;
        this.userAccountSettings = userAccountSettings;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public void setUserAccountSettings(UserAccountSettings userAccountSettings)
    {
        this.userAccountSettings = userAccountSettings;
    }

    public User getUser()
    {
        return user;
    }

    public UserAccountSettings getUserAccountSettings()
    {
        return userAccountSettings;
    }

    @Override
    public String toString()
    {
        return "UserSettings{" +
                "user=" + user +
                ", userAccountSettings=" + userAccountSettings +
                '}';
    }
}
