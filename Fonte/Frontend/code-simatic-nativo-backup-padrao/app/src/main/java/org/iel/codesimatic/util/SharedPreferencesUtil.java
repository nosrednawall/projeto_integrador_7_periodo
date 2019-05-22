package org.iel.codesimatic.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {
    public static String getTokenFromSharedPreferences(Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(KeysUtil.KEY_SHAREDPREFERENCES, Context.MODE_PRIVATE);
        String tokenRestaurado = prefs.getString(KeysUtil.KEY_TOKEN, null);
        return tokenRestaurado;
    }

    public static void deleteTokenInSharedPreferences(Context context)
    {
        SharedPreferences sharedPref = context.getSharedPreferences(KeysUtil.KEY_SHAREDPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(KeysUtil.KEY_TOKEN).commit();
    }
}
