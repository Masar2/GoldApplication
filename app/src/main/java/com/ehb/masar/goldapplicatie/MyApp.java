package com.ehb.masar.goldapplicatie;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by masar on 25/12/2018.
 */

public class MyApp extends Application{

  // it's the context of current state of the application/object.
  // It lets newly-created objects understand what has been going on. Typically you call it to get information regarding another part of your program (activity and package/application).
    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        //Dit initilezed de application context
        return context;
    }

}
