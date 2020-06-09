package com.example.topcinema.peliculas;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.topcinema.R;
import com.example.topcinema.controllers.AyudanteBaseDeDatos;
import com.example.topcinema.controllers.PeliculaController;
import com.example.topcinema.modelos.Pelicula;
import com.example.topcinema.usuarios.RegisterUserActivity;

import java.io.IOException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterPeliculaActivity extends AppCompatActivity {
        Button btnCrear, btnAddImage;

        EditText etNombre;
        EditText etGenero;
        EditText etCompania;
        EditText etDuracion;
        EditText etPuntuacion;
        Pattern patPuntuacion = Pattern.compile("([1-5])");

        ImageView fotoP;

        Pelicula pelicula;
        PeliculaController peliculaController;

        String imgP;

    private static final int PICK_IMAGE=100;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula_register);
        btnCrear = findViewById(R.id.btnCrear);
        btnAddImage = findViewById(R.id.btnAddImage);

        etNombre = findViewById(R.id.etNombre);
        etGenero = findViewById(R.id.etGenero);
        etCompania = findViewById(R.id.etCompania);
        etDuracion = findViewById(R.id.etDuracion);
        etPuntuacion = findViewById(R.id.etPuntuacion);
        fotoP = findViewById(R.id.fotoP);
        peliculaController = new PeliculaController(RegisterPeliculaActivity.this);

        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent();
                    intent.setType("image/*");

                    intent.setAction(intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent,PICK_IMAGE);
                } catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });



        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String nombre = etNombre.getText().toString();
                    String genero = etGenero.getText().toString();
                    String compania = etCompania.getText().toString();
                    String duracion = etDuracion.getText().toString();
                    String puntuacion = etPuntuacion.getText().toString();
                    Matcher matcherP = patPuntuacion.matcher(puntuacion);

                    if (imageToStore == null) {
                        if(Locale.getDefault().getLanguage().equals("en")){
                            Toast.makeText(RegisterPeliculaActivity.this, "You need to add a movie poster", Toast.LENGTH_LONG).show();
                        }
                        else if(Locale.getDefault().getLanguage().equals("es")){
                            Toast.makeText(RegisterPeliculaActivity.this, "Debes asignar un Poster para la Película", Toast.LENGTH_LONG).show();
                        }
                        return;
                    }

                    if ("".equals(nombre)) {
                        if(Locale.getDefault().getLanguage().equals("en")){
                            etNombre.setError("You need to give the movie a name");
                        }
                        else if(Locale.getDefault().getLanguage().equals("es")){
                            etNombre.setError("Debes ingresar el Nombre de la Película");
                        }
                        etNombre.requestFocus();
                        return;
                    }
                    if ("".equals(genero)) {
                        if(Locale.getDefault().getLanguage().equals("en")){
                            etGenero.setError("You need to give the movie a genre");
                        }
                        else if(Locale.getDefault().getLanguage().equals("es")){
                            etGenero.setError("Debes ingresar el Genero de la Película");
                        }
                        etGenero.requestFocus();
                        return;
                    }
                    if ("".equals(compania)) {
                        if(Locale.getDefault().getLanguage().equals("en")){
                            etCompania.setError("You need to give the movie a Production Company");
                        }
                        else if(Locale.getDefault().getLanguage().equals("es")){
                            etCompania.setError("Debes ingresar la Compañía de la Película");
                        }
                        etCompania.requestFocus();
                        return;
                    }
                    if ("".equals(duracion)) {
                        if(Locale.getDefault().getLanguage().equals("en")){
                            etDuracion.setError("You need to give the movie a Duration/Runtime");
                        }
                        else if(Locale.getDefault().getLanguage().equals("es")){
                            etDuracion.setError("Debes ingresar La Duración de la Película");
                        }
                        etDuracion.requestFocus();
                        return;
                    }
                    if ("".equals(puntuacion)) {
                        if(Locale.getDefault().getLanguage().equals("en")){
                            etPuntuacion.setError("You need to rate the movie (1-5 stars)");
                        }
                        else if(Locale.getDefault().getLanguage().equals("es")){
                            etPuntuacion.setError("Debes ingresar la Puntuación de la Película");
                        }
                        etPuntuacion.requestFocus();
                        return;
                    } else {
                        if(puntuacion.length()>1) {
                            if(Locale.getDefault().getLanguage().equals("en")){
                                etPuntuacion.setError("Invalid data");
                            }
                            else if(Locale.getDefault().getLanguage().equals("es")){
                                etPuntuacion.setError("Dato no válido");
                            }
                            etPuntuacion.requestFocus();
                            return;
                        }
                        else {
                            if(matcherP.find()==false) {
                                if(Locale.getDefault().getLanguage().equals("en")){
                                    etPuntuacion.setError("The rating needs to be between 1 and 5");
                                }
                                else if(Locale.getDefault().getLanguage().equals("es")){
                                    etPuntuacion.setError("El dato debe estar entre 1-5");
                                }
                                etPuntuacion.requestFocus();
                                return;
                            }
                            else{
                                if(validarCopia()) {
                                    int duracionP = Integer.parseInt(duracion);
                                    int puntuacionP = Integer.parseInt(puntuacion);
                                    pelicula = new Pelicula(nombre, genero, compania, duracionP, puntuacionP, imageToStore);
                                    long creado = peliculaController.nuevaPelicula(pelicula);
                                    if (creado == -1) {
                                        if(Locale.getDefault().getLanguage().equals("en")){
                                            Toast.makeText(RegisterPeliculaActivity.this, "Error while inserting the movie", Toast.LENGTH_LONG).show();
                                        }
                                        else if(Locale.getDefault().getLanguage().equals("es")){
                                            Toast.makeText(RegisterPeliculaActivity.this, "Error al insertar película", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        if(Locale.getDefault().getLanguage().equals("en")){
                                            Toast.makeText(RegisterPeliculaActivity.this, "Movie was inserted correctly", Toast.LENGTH_LONG).show();
                                        }
                                        else if(Locale.getDefault().getLanguage().equals("es")){
                                            Toast.makeText(RegisterPeliculaActivity.this, "Se insertó correctamente", Toast.LENGTH_LONG).show();
                                        }
                                        finish();
                                    }
                                }

                            }
                        }

                    }
                }
                catch (Exception ex)
                {
                    Toast.makeText(RegisterPeliculaActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private Uri imageFilePath;
    private Bitmap imageToStore;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==PICK_IMAGE && data!=null && data.getData()!=null){
            imageFilePath = data.getData();
            try {
                imageToStore= MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);
                fotoP.setImageBitmap(imageToStore);

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean validarCopia()
    {
        boolean noExiste = true;
        try{
            String peliculaString = etNombre.getText().toString();
            AyudanteBaseDeDatos ayudanteBaseDeDatos = new AyudanteBaseDeDatos(RegisterPeliculaActivity.this);
            SQLiteDatabase bd = ayudanteBaseDeDatos.getReadableDatabase();
            Cursor c = bd.rawQuery("SELECT nombre FROM pelicula", null);
            if(c.getCount() == 0) Toast.makeText(RegisterPeliculaActivity.this, "Error !",Toast.LENGTH_LONG).show();
            if(c.moveToFirst()){
                do{
                    String peliculaEncontrado = c.getString(0);
                    if(peliculaString.equals(peliculaEncontrado)) {
                        Toast.makeText(RegisterPeliculaActivity.this, "Error, ya hay una pelicula con ese nombre.",Toast.LENGTH_LONG).show();
                        noExiste = false;
                        break;
                    }
                } while (c.moveToNext());
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(RegisterPeliculaActivity.this, "Error: "+ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        return noExiste;
    }
}

