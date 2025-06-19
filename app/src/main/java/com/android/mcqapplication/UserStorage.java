package com.android.mcqapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class UserStorage {
    private static final String PREFS_NAME = "UserData";
    private static final String USERS_KEY = "users";

    // Save new user
    public static void saveUser(Context context, String email, String password) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String storedUsers = prefs.getString(USERS_KEY, "");

        storedUsers += email + ":" + password + ":false;";
        editor.putString(USERS_KEY, storedUsers);
        editor.apply();
    }

    // Check login credentials & submission status
    public static int checkUserStatus(Context context, String email, String password) {
        // 0 = not found, 1 = valid, 2 = already submitted
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String storedUsers = prefs.getString(USERS_KEY, "");
        for (String user : storedUsers.split(";")) {
            String[] parts = user.split(":");
            if (parts.length == 3 && parts[0].equals(email) && parts[1].equals(password)) {
                return parts[2].equals("true") ? 2 : 1;
            }
        }
        return 0;
    }

    // Mark user as submitted
    public static void markUserSubmitted(Context context, String email) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String storedUsers = prefs.getString(USERS_KEY, "");
        StringBuilder updatedUsers = new StringBuilder();

        for (String user : storedUsers.split(";")) {
            if (user.isEmpty()) continue;
            String[] parts = user.split(":");
            if (parts.length == 3 && parts[0].equals(email)) {
                updatedUsers.append(parts[0]).append(":").append(parts[1]).append(":true;");
            } else {
                updatedUsers.append(user).append(";");
            }
        }

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USERS_KEY, updatedUsers.toString());
        editor.apply();
    }
}
