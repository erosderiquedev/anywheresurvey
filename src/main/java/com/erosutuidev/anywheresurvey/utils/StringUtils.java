/**
 * Author: Eros Derique Utui
 * User:erosderiquedev
 * Date:03/03/2025
 * Time:13:22
 */
package com.erosutuidev.anywheresurvey.utils;

public class StringUtils {
    private StringUtils(){} // Private constructor to prevent instantiation

    public static boolean isNullOrEmpty(String str){
        return str == null || str.trim().isEmpty();
    }

    public static String capitalize(String str) {
        if (isNullOrEmpty(str)) {
            return str;
        }
        return str.toUpperCase();
    }

    public static String toLowerCase(String str) {
        if (isNullOrEmpty(str)) {
            return str;
        }
        return str.toLowerCase();
    }

    public static String capitalizeFirstLetter(String str) {
        if (isNullOrEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}

