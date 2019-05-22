package org.iel.codesimatic.util;

import android.util.Log;

public class Util {

    public static float getRandom(float range, float start) {
        return (float) (Math.random() * range) + start;
    }

    public static void loggerAsyncTask(String mensagem){
        Log.i("AsyncTask",mensagem+" Thread: " + Thread.currentThread().getName());
    }

}
