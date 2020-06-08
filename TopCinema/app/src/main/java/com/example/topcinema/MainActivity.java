package com.example.topcinema;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main);
        background.start();
    }
    Thread background = new Thread() {
        public void run() {
            try {
                sleep(1500);
                Intent i=new Intent(getBaseContext(), InicioActividad.class);
                startActivity(i);
                finish();
            } catch (Exception e) {

            }
        }
    };
}