package com.example.topcinema;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imgDonRamon;
    //Animation animation;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        imgDonRamon = findViewById(R.id.ivDonRamon);
        trans();
        background.start();

    }

    Thread background = new Thread() {
        public void run() {
            try {

                sleep(1700);
                Intent i=new Intent(getBaseContext(), InicioActividad.class);
                startActivity(i);
                finish();
            } catch (Exception e) {

            }
        }

    };
    Thread anim = new Thread() {
        public void run() {
            try {
                sleep(500);
                imgDonRamon.setVisibility(View.INVISIBLE);
                //imgDonRamon.startAnimation(animation);
                //
            }
            catch (Exception e) {

            }
        }

    };


    public void trans(){

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.donramontop);
        imgDonRamon.setVisibility(View.VISIBLE);
        imgDonRamon.startAnimation(animation);
        imgDonRamon.setVisibility(View.INVISIBLE);

    }


}