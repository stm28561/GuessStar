package com.example.guessstar;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.regex.Pattern;

public class Stars extends MainActivity {
    public static HashMap<String, Bitmap> starsList = new HashMap();
    public static String[] starsNames = new String[40];
    private static Pattern patternForParsingSite = Pattern.compile("common_rating_list(.*?)show_more_ratings");
    private static Pattern patternForName = Pattern.compile("alt=\"(.*?)\" t");
    private static Pattern patternForPhoto = Pattern.compile("<img src=\"(.*?)\" alt");

    public static String getRandomName (String[] strings) {
        int randomPosition = (int) (Math.random() * 40);
        return strings[randomPosition];
    }

    public static HashMap<String, Bitmap> getStarsList() {
        return starsList;
    }

    public static String[] getStarsNames() {
        return starsNames;
    }

    public static Pattern getPatternForParsingSite() {
        return patternForParsingSite;
    }

    public static Pattern getPatternForName() {
        return patternForName;
    }

    public static Pattern getPatternForPhoto() {
        return patternForPhoto;
    }
}
