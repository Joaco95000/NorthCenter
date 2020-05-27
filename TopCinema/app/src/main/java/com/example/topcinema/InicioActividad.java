package com.example.topcinema;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

public class InicioActividad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_actividad);
    }

    public void cargarQuienesSomos(View w)
    {
        try {
            Intent intent = new Intent(this, QuienesSomosActivity.class);
            startActivity(intent);
        }
        catch (Exception ex)
        {
            Toast.makeText(this,ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void cargarRegister(View w)
    {
        try {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }
        catch (Exception ex)
        {
            Toast.makeText(this,ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void cargarLogin(View w)
    {
        try {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        catch (Exception ex)
        {
            Toast.makeText(this,ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
