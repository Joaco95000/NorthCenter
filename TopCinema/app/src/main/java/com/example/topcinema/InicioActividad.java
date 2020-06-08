package com.example.topcinema;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class InicioActividad extends AppCompatActivity {
    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_actividad);
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            finishAffinity();
            /*finish();
            System.exit(0);
            super.onBackPressed();
            return;*/
        } else {
            backToast = Toast.makeText(getBaseContext(), "Presione de nuevo para salirse", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    public void cargarQuienesSomos(View w)
    {
        try {
            Intent intent = new Intent(this, QuienesSomosActivity.class);
            startActivity(intent);
        }
        catch (Exception ex) {
            Toast.makeText(this,ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void cargarRegister(View w)
    {
        try {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }
        catch (Exception ex) {
            Toast.makeText(this,ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void cargarLogin(View w)
    {
        try {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        catch (Exception ex) {
            Toast.makeText(this,ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void cargarIdioma(View w)
    {
        try {
            Intent intent = new Intent(this, IdiomActivity.class);
            startActivity(intent);
        }
        catch (Exception ex) {
            Toast.makeText(this,ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
