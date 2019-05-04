package org.iel.codesimatic.util;

import java.util.Random;

public class Util {

    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


    public static float randFloat(float min, float max) {

        Random rand = new Random();

        float result = rand.nextFloat() * (max - min) + min;

        return result;

    }

    public static float getRandom(float range, float start) {
        return (float) (Math.random() * range) + start;
    }

}
