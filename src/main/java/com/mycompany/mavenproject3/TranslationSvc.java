/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3;

import static com.mycompany.mavenproject3.AuthService.getLanguage;
import static com.mycompany.mavenproject3.AuthService.langs;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author fatih
 */
public class TranslationSvc {
    
    public static Map<String,ResourceBundle> bundles = new HashMap<String,ResourceBundle>() {    
    {
        for (Language ln:langs){
                    put(ln.getName(),ResourceBundle.getBundle("ApplicationMessages"+"_"+ln.getLanguage()+"_"+ln.getTerritory() , new Locale(ln.getLanguage(),ln.getTerritory())));
                }
    }
    };
    public static String getText(String code){
        String ln = getLanguage().getName();
        ResourceBundle b = bundles.get(ln);
        String r = b.getString(code);
        return r;
    }
    
}
