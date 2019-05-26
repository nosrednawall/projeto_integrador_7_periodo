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

    public static String getUltimoServidorFromSharedPreferences(Context context){
        SharedPreferences preferences = context.getSharedPreferences(KeysUtil.KEY_SHAREDPREFERENCES, Context.MODE_PRIVATE);
        String servidor = preferences.getString(KeysUtil.KEY_SERVIDOR, ConexaoUtil.CONEXAO_LOCAL);
        return servidor;
    }

    /**
     *
     * @param context
     * @param servidor
     */
    public static void salvaEnderecoServidorNoSharedPreferences(Context context, String servidor){
        SharedPreferences preferences = context.getSharedPreferences(KeysUtil.KEY_SHAREDPREFERENCES,0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KeysUtil.KEY_SERVIDOR, servidor);

        //salva
        editor.commit();
    }
}
