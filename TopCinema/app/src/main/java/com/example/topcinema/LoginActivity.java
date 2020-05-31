package com.example.topcinema;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etIS, etCon;
    Button btnIS;
    TextView tvIS;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etIS = findViewById(R.id.etIngresarUsuario);
        etCon = findViewById(R.id.etIngresarContrasenia);
        btnIS = findViewById(R.id.btnIngresarIngresar);
        tvIS = findViewById(R.id.tvIngresarMensaje);

        btnIS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    tvIS.setText("");
                    String usuarioString = etIS.getText().toString();
                    String passwordString = etCon.getText().toString();
                    AyudanteBaseDeDatos ayudanteBaseDeDatos = new AyudanteBaseDeDatos(LoginActivity.this);
                    SQLiteDatabase bd = ayudanteBaseDeDatos.getReadableDatabase();
                    Cursor c = bd.rawQuery("SELECT usuario, password FROM usuarios", null);
                    if(c.moveToFirst()){

                        do{
                            String usuarioEncontrado = c.getString(0);
                            String passwordEncontrada = c.getString(1);

                            if(usuarioString.equals(usuarioEncontrado)&&passwordString.equals(passwordEncontrada))
                            {
                                llamarPanel();
                            }
                        }while (c.moveToNext());
                        if(tvIS.getText().toString().equals("")){
                            tvIS.setText("Error!");
                        }
                    }

                }
                catch (Exception ex){
                    Toast.makeText(LoginActivity.this, "Error: "+ex.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

        });
    }
  public void llamarPanel()
  {
      try {
          Intent intent = new Intent(this, PanelActivity.class);
          startActivity(intent);
      }
      catch (Exception ex)
      {
          Toast.makeText(this,ex.getMessage(), Toast.LENGTH_SHORT).show();
      }
  }
}