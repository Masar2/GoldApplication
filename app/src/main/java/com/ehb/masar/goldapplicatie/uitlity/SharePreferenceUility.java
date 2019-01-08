package com.ehb.masar.goldapplicatie.uitlity;

import android.content.Context;
import android.content.SharedPreferences;



import com.ehb.masar.goldapplicatie.MyApp;

/**
 * Created by masar on 25/12/2018.
 */

//tijdelijk gebruik van key-value data
public class SharePreferenceUility {


    private static String PREFERENCE_NAME = "MyApplication";
    private static SharePreferenceUility  sharePreferenceUility ;
    private SharedPreferences sharedPreferences ;

    private SharePreferenceUility(Context context){

        //wij creeren hier een unieke naam dat alleen data wordt gedeelt met onze app en niet met andere apps
        PREFERENCE_NAME = PREFERENCE_NAME + context.getPackageName();
        this.sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);

    }

    //getInstance
    public static SharePreferenceUility getInstance(){
        if(sharePreferenceUility == null){
            sharePreferenceUility = new SharePreferenceUility(MyApp.getContext());
        }
        return  sharePreferenceUility;
    }

    //wij gebruiken dit methode om de response van de server te saven
    //login response user_id , 123
    public void saveString(String key , String value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key , value);
        editor.commit();



    }

    //om een value te returenen
    //als er geen value wordt de defulat value getrouned
    public String getString(String key , String value){
        return sharedPreferences.getString(key , value);

    }


}
