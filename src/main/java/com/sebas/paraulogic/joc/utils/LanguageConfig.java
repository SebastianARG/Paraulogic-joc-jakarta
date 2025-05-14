/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sebas.paraulogic.joc.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author sebastianr
 */
public class LanguageConfig {
    public static String get(Locale locale, String key) {
        String lang = locale.getLanguage();
        ResourceBundle bundle = ResourceBundle.getBundle("lang", locale);
        return bundle.containsKey(key) ? bundle.getString(key) : "???" + key + "???";
    }
    
}
