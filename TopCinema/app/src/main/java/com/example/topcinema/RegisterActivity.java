package com.example.topcinema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.topcinema.controllers.UsuarioController;
import com.example.topcinema.modelos.Usuario;

public class RegisterActivity extends AppCompatActivity {

    Button btnCrear;

    EditText etCorreo;
    EditText etNombre;
    EditText etApellido1;
    EditText etApellido2;
    EditText etEdad;
    EditText etUsuario;
    EditText etPassword1;
    EditText etPassword2;

    Usuario user;
    UsuarioController usuarioController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnCrear = findViewById(R.id.btnCrear);

        etCorreo = findViewById(R.id.etCorreo);
        etNombre = findViewById(R.id.etNombre);
        etApellido1 = findViewById(R.id.etApellido1);
        etApellido2 = findViewById(R.id.etApellido2);
        etEdad = findViewById(R.id.etEdad);
        etUsuario = findViewById(R.id.etUsuario);
        etPassword1 = findViewById(R.id.etPassword);
        etPassword2 = findViewById(R.id.etPassword2);
        usuarioController = new UsuarioController(RegisterActivity.this);

        btnCrear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                try{
                    String correo = etCorreo.getText().toString();

                    String nombre = etNombre.getText().toString();
                    String apellido1 = etApellido1.getText().toString();
                    String apellido2 = etApellido2.getText().toString();
                    String edad = etEdad.getText().toString();
                    String usuario = etUsuario.getText().toString();
                    String password1 = etPassword1.getText().toString();
                    String password2 = etPassword2.getText().toString();
                    /*
                    String n1 ="Name",n2="Correo",n3="Usuario",n4="Contrasenia";
                    int n5 = 7;
                    double n6 = 100;
                    user = new Usuario(n1,n2,n3,n4,n5,n6);
                    long creado = usuarioController.nuevoUsuario(user);
                    if (creado==-1)
                    {
                        Toast.makeText(Main2Crear.this, "Error al insertar el usuario",Toast.LENGTH_LONG).show();
                    }else
                        {
                        Toast.makeText(Main2Crear.this,"Se inserto correctamente",Toast.LENGTH_LONG).show();
                    }
                    */
                    ///*
                    if ("".equals(nombre)){
                        etNombre.setError("debes ingresar el nombre");
                        etNombre.requestFocus();
                        return;
                    }
                    if ("".equals(apellido1)) {
                        etApellido1.setError("debes ingresar el apellido");
                        etApellido1.requestFocus();
                        return;
                    }
                    if ("".equals(edad)){
                        etEdad.setError("debes ingresar la edad");
                        etEdad.requestFocus();
                        return;
                    }
                    if ("".equals(usuario)){
                        etUsuario.setError("debes ingresar el usuario");
                        etUsuario.requestFocus();
                        return;
                    }
                    if ("".equals(password1)){
                        etPassword1.setError("debes ingresar la contraseña");
                        etPassword1.requestFocus();
                        return;
                    }
                    if ("".equals(password2)){
                        etPassword2.setError("debes verificar la contraseña");
                        etPassword2.requestFocus();
                        return;
                    }
                    if(password1.compareTo(password2) == 0)
                    {
                        int edadF = Integer.parseInt(edad);

                        user = new Usuario(correo,nombre,apellido1,apellido2,usuario,password1,edadF);
                        long creado = usuarioController.nuevoUsuario(user);
                        if (creado==-1){
                            Toast.makeText(RegisterActivity.this, "Error al insertar el usuario",Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(RegisterActivity.this,"Se insertó correctamente",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(RegisterActivity.this,InicioActividad.class);
                            startActivity(intent);
                        }
                    }
                    else
                    {
                        etPassword2.setError("Las contraseñas no coinciden, vuelva a intentarlo");
                        etPassword2.requestFocus();
                        return;
                    }
                    //*/

                }catch (Exception ex){
                    Toast.makeText(RegisterActivity.this, ex.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

        });
    }
}