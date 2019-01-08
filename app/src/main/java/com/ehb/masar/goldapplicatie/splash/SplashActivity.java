package com.ehb.masar.goldapplicatie.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.ehb.masar.goldapplicatie.home.HomeActivity;
//import com.ehb.masar.goldapplicatie.login.SignInActivity;
import com.ehb.masar.goldapplicatie.R;
import com.ehb.masar.goldapplicatie.login.SignUpActivity;
import com.ehb.masar.goldapplicatie.uitlity.SharePreferenceUility;
/**
 * Created by masar on 25/12/2018.
 */

public class SplashActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        //wij roepen hier de timing methode
        init();
    }


    public void init(){
        //na 5 seconden , gaat de methode kijken binnen de run()
        //als de user geregistreed is of niet
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //if user register
                //then show home screen
                //else login screen
                //key = registered_key
                if(SharePreferenceUility.getInstance().getString("registered_key","").equalsIgnoreCase("")){
                    //not registered user so show login screen
                    Intent intent = new Intent(SplashActivity.this, SignUpActivity.class);
                    startActivity(intent);
                } else {
                    //registered user so show home screen
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                finish();

            }
        }, 5000);
    }

}
