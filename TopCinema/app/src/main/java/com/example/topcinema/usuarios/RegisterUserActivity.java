package com.example.topcinema.usuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.topcinema.R;
import com.example.topcinema.controllers.UsuarioController;
import com.example.topcinema.modelos.Usuario;
import com.example.topcinema.controllers.AyudanteBaseDeDatos;
import com.example.topcinema.peliculas.RegisterPeliculaActivity;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterUserActivity extends AppCompatActivity {

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

    //pattern correo
    Pattern pat = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+");
    //patern texto
    Pattern patTexto = Pattern.compile("[a-zA-Z]");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        btnCrear = findViewById(R.id.btnCrear);

        etCorreo = findViewById(R.id.etCorreo);
        etNombre = findViewById(R.id.etNombre);
        etApellido1 = findViewById(R.id.etApellido1);
        etApellido2 = findViewById(R.id.etApellido2);
        etEdad = findViewById(R.id.etEdad);
        etUsuario = findViewById(R.id.etUsuario);
        etPassword1 = findViewById(R.id.etPassword);
        etPassword2 = findViewById(R.id.etPassword2);
        usuarioController = new UsuarioController(RegisterUserActivity.this);

        btnCrear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                try{
                    String correo = etCorreo.getText().toString();
                    Matcher matcher = pat.matcher(correo);
                    String nombre = etNombre.getText().toString();
                    Matcher matcherN = patTexto.matcher(nombre);
                    String apellido1 = etApellido1.getText().toString();
                    Matcher matcherAp = patTexto.matcher(apellido1);
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
                        if(Locale.getDefault().getLanguage().equals("en")){
                            etNombre.setError("You need to enter a name");
                        }
                        else if(Locale.getDefault().getLanguage().equals("es")){
                            etNombre.setError("Debes ingresar el nombre");
                        }
                        etNombre.requestFocus();
                        return;
                    }
                    if (matcherN.find()==false){
                        if(Locale.getDefault().getLanguage().equals("en")){
                            etNombre.setError("Are you sure you're writing your name correctly?");
                        }
                        else if(Locale.getDefault().getLanguage().equals("es")){
                            etNombre.setError("¿Seguro que has introducido el nombre correctamente?");
                        }
                        etNombre.requestFocus();
                        return;
                    }
                    if ("".equals(apellido1)) {
                        if(Locale.getDefault().getLanguage().equals("en")){
                            etApellido1.setError("You need to add your last name");
                        }
                        else if(Locale.getDefault().getLanguage().equals("es")){
                            etApellido1.setError("Debes ingresar el apellido");
                        }
                        etApellido1.requestFocus();
                        return;
                    }
                    if (matcherAp.find()==false){
                        if(Locale.getDefault().getLanguage().equals("en")){
                            etApellido1.setError("Are you sure you're writing your last name correctly?");
                        }
                        else if(Locale.getDefault().getLanguage().equals("es")){
                            etApellido1.setError("¿Seguro que has introducido el apellido correctamente?");
                        }
                        etApellido1.requestFocus();
                        return;
                    }
                    if ("".equals(edad)){
                        if(Locale.getDefault().getLanguage().equals("en")){
                            etEdad.setError("You must enter your age");
                        }
                        else if(Locale.getDefault().getLanguage().equals("es")){
                            etEdad.setError("Debes ingresar la edad");
                        }
                        etEdad.requestFocus();
                        return;
                    }
                    if ("".equals(correo)){
                        if(Locale.getDefault().getLanguage().equals("en")){
                            etCorreo.setError("You need to enter you email");
                        }
                        else if(Locale.getDefault().getLanguage().equals("es")){
                            etCorreo.setError("Debes ingresar el correo");
                        }
                        etCorreo.requestFocus();
                        return;
                    }
                    if (matcher.find()==false){
                        if(Locale.getDefault().getLanguage().equals("en")){
                            etCorreo.setError("Invalid email");
                        }
                        else if(Locale.getDefault().getLanguage().equals("es")){
                            etCorreo.setError("El correo ingresado no es válido");
                        }
                        etCorreo.requestFocus();
                        return;
                    }

                    if ("".equals(usuario)){
                        if(Locale.getDefault().getLanguage().equals("en")){
                            etUsuario.setError("You need to a enter a username");
                        }
                        else if(Locale.getDefault().getLanguage().equals("es")){
                            etUsuario.setError("Debes ingresar el usuario");
                        }
                        etUsuario.requestFocus();
                        return;
                    }
                    if ("".equals(password1)){
                        if(Locale.getDefault().getLanguage().equals("en")){
                            etPassword1.setError("You need to a enter a password");
                        }
                        else if(Locale.getDefault().getLanguage().equals("es")){
                            etPassword1.setError("Debes ingresar la contraseña");
                        }
                        etPassword1.requestFocus();
                        return;
                    }
                    if ("".equals(password2)){
                        if(Locale.getDefault().getLanguage().equals("en")){
                            etPassword2.setError("You must verify your password");
                        }
                        else if(Locale.getDefault().getLanguage().equals("es")){
                            etPassword2.setError("debes verificar la contraseña");
                        }
                        etPassword2.requestFocus();
                        return;
                    }
                    if(password1.compareTo(password2) == 0)
                    {
                        int edadF = Integer.parseInt(edad);
                        if(validarCopia())
                        {
                            user = new Usuario(correo,nombre,apellido1,apellido2,usuario,password1,edadF);
                            long creado = usuarioController.nuevoUsuario(user);
                            if (creado==-1){
                                if(Locale.getDefault().getLanguage().equals("en")){
                                    Toast.makeText(RegisterUserActivity.this, "Error while inserting this user",Toast.LENGTH_LONG).show();
                                }
                                else if(Locale.getDefault().getLanguage().equals("es")){
                                    Toast.makeText(RegisterUserActivity.this, "Error al insertar el usuario",Toast.LENGTH_LONG).show();
                                }
                            }else{
                                if(Locale.getDefault().getLanguage().equals("en")){
                                    Toast.makeText(RegisterUserActivity.this,"User inserted successfully",Toast.LENGTH_LONG).show();
                                }
                                else if(Locale.getDefault().getLanguage().equals("es")){
                                    Toast.makeText(RegisterUserActivity.this,"Se insertó correctamente",Toast.LENGTH_LONG).show();
                                }
                                //Intent intent = new Intent(RegisterActivity.this,InicioActividad.class);
                                //startActivity(intent);
                                finish();
                            }
                        }


                    }
                    else
                    {
                        if(Locale.getDefault().getLanguage().equals("en")){
                            etPassword2.setError("These passwords don't match, try again");
                        }
                        else if(Locale.getDefault().getLanguage().equals("es")){
                            etPassword2.setError("Las contraseñas no coinciden, vuelva a intentarlo");
                        }
                        etPassword2.requestFocus();
                        return;
                    }
                    //*/

                }catch (Exception ex){
                    Toast.makeText(RegisterUserActivity.this, ex.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

        });
    }
    public boolean validarCopia()
    {
        boolean noExiste = true;
        try{
            String usuarioString = etUsuario.getText().toString();
            AyudanteBaseDeDatos ayudanteBaseDeDatos = new AyudanteBaseDeDatos(RegisterUserActivity.this);
            SQLiteDatabase bd = ayudanteBaseDeDatos.getReadableDatabase();
            Cursor c = bd.rawQuery("SELECT usuario, password FROM usuarios", null);
            if(c.getCount() == 0) Toast.makeText(RegisterUserActivity.this, "Error!",Toast.LENGTH_LONG).show();
            if(c.moveToFirst()){
                do{
                    String usuarioEncontrado = c.getString(0);
                    if(usuarioString.equals(usuarioEncontrado)) {
                        if(Locale.getDefault().getLanguage().equals("en")){
                            Toast.makeText(RegisterUserActivity.this, "Error, this user already exists.",Toast.LENGTH_LONG).show();
                        }
                        else if(Locale.getDefault().getLanguage().equals("es")){
                            Toast.makeText(RegisterUserActivity.this, "Error, el Usuario ya existe.",Toast.LENGTH_LONG).show();
                        }
                        noExiste = false;
                        break;
                    }
                } while (c.moveToNext());
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(RegisterUserActivity.this, "Error: "+ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        return noExiste;
    }
}