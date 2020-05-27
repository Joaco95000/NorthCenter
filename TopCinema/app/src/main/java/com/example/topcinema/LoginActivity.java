package com.example.topcinema;

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
                    String contraseniaString = etCon.getText().toString();
                    AyudanteBaseDeDatos ayudanteBaseDeDatos = new AyudanteBaseDeDatos(LoginActivity.this);
                    SQLiteDatabase bd = ayudanteBaseDeDatos.getReadableDatabase();
                    Cursor c = bd.rawQuery("SELECT usuario, contrasenia FROM estudiante", null);
                    if(c.moveToFirst()){

                        do{
                            String usuarioEncontrado = c.getString(0);
                            String contraseñaEncontrada = c.getString(1);

                            if(usuarioString.equals(usuarioEncontrado)&&contraseniaString.equals(contraseñaEncontrada))
                            {
                                tvIS.setText("Bienvenido");
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
}