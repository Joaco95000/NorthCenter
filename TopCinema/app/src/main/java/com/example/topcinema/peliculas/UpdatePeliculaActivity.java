package com.example.topcinema.peliculas;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topcinema.R;
import com.example.topcinema.controllers.PeliculaController;
import com.example.topcinema.modelos.Pelicula;

import java.io.IOException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdatePeliculaActivity extends AppCompatActivity {
    EditText etUpdateNombre, etUpdateGenero, etUpdateCompania, etUpdateDuracion, etUpdatePuntuacion;
    Button btnActualizar, btnUpdateImage;
    Pelicula pelicula;
    PeliculaController peliculaController;
    Pattern patPuntuacion = Pattern.compile("([1-5])");
    ImageView fotoPUpdate;
    Bitmap imgPUpdate;
    int id;
    private static final int PICK_IMAGE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            setContentView(R.layout.activity_pelicula_update);
            pelicula = new Pelicula();
            btnActualizar = findViewById(R.id.btnActualizar);
            btnUpdateImage = findViewById(R.id.btnUpdateImage);
            etUpdateNombre = findViewById(R.id.etUpdateNombre);
            etUpdateGenero = findViewById(R.id.etUpdateGenero);
            etUpdateCompania = findViewById(R.id.etUpdateCompania);
            etUpdateDuracion = findViewById(R.id.etUpdateDuracion);
            etUpdatePuntuacion = findViewById(R.id.etUpdatePuntuacion);
            fotoPUpdate = findViewById(R.id.fotoPUpdate);
            id = getIntent().getIntExtra("id", 0);
            peliculaController = new PeliculaController(UpdatePeliculaActivity.this);
            pelicula = peliculaController.peliculaEspecifica(id);
            etUpdateNombre.setText(pelicula.getNombre());
            etUpdateGenero.setText(pelicula.getGenero());
            etUpdateCompania.setText(pelicula.getCompania());
            etUpdateDuracion.setText(pelicula.getDuracion()+"", TextView.BufferType.EDITABLE);
            etUpdatePuntuacion.setText(pelicula.getPuntuacion()+"", TextView.BufferType.EDITABLE);
            fotoPUpdate.setImageBitmap(pelicula.getFoto());

            btnUpdateImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://media/internal/images/media"));
                        startActivityForResult(intent,PICK_IMAGE);
                    } catch (Exception ex)
                    {
                        Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });

            btnActualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        String nombre = etUpdateNombre.getText().toString();
                        String genero = etUpdateGenero.getText().toString();
                        String compania = etUpdateCompania.getText().toString();
                        String duracion = etUpdateDuracion.getText().toString();
                        String puntuacion = etUpdatePuntuacion.getText().toString();
                        Matcher matcherP = patPuntuacion.matcher(puntuacion);

                        if ("".equals(nombre)) {
                            if(Locale.getDefault().getLanguage().equals("en")){
                                etUpdateNombre.setError("You need to give the movie a name");
                            }
                            else if(Locale.getDefault().getLanguage().equals("es")){
                                etUpdateNombre.setError("Debes ingresar el Nombre de la Película");
                            }
                            etUpdateNombre.requestFocus();
                            return;
                        }
                        if ("".equals(genero)) {
                            if(Locale.getDefault().getLanguage().equals("en")){
                                etUpdateGenero.setError("You need to give the movie a genre");
                            }
                            else if(Locale.getDefault().getLanguage().equals("es")){
                                etUpdateGenero.setError("Debes ingresar el Genero de la Película");
                            }
                            etUpdateGenero.requestFocus();
                            return;
                        }
                        if ("".equals(compania)) {
                            if(Locale.getDefault().getLanguage().equals("en")){
                                etUpdateCompania.setError("You need to give the movie a Production Company");
                            }
                            else if(Locale.getDefault().getLanguage().equals("es")){
                                etUpdateCompania.setError("Debes ingresar la Compañía de la Película");
                            }
                            etUpdateCompania.requestFocus();
                            return;
                        }
                        if ("".equals(duracion)) {
                            if(Locale.getDefault().getLanguage().equals("en")){
                                etUpdateDuracion.setError("You need to give the movie a Duration/Runtime");
                            }
                            else if(Locale.getDefault().getLanguage().equals("es")){
                                etUpdateDuracion.setError("Debes ingresar La Duración de la Película");
                            }
                            etUpdateDuracion.requestFocus();
                            return;
                        }
                        if ("".equals(puntuacion)) {
                            if(Locale.getDefault().getLanguage().equals("en")){
                                etUpdatePuntuacion.setError("You need to rate the movie (1-5 stars)");
                            }
                            else if(Locale.getDefault().getLanguage().equals("es")){
                                etUpdatePuntuacion.setError("Debes ingresar la Puntuación de la Película");
                            }
                            etUpdatePuntuacion.requestFocus();
                            return;
                        } else {
                            if (puntuacion.length() > 1) {
                                if(Locale.getDefault().getLanguage().equals("en")){
                                    etUpdatePuntuacion.setError("Invalid data");
                                }
                                else if(Locale.getDefault().getLanguage().equals("es")){
                                    etUpdatePuntuacion.setError("Dato no válido");
                                }
                                etUpdatePuntuacion.requestFocus();
                                return;
                            } else {
                                if (matcherP.find() == false) {
                                    if(Locale.getDefault().getLanguage().equals("en")){
                                        etUpdatePuntuacion.setError("The rating needs to be between 1 and 5");
                                    }
                                    else if(Locale.getDefault().getLanguage().equals("es")){
                                        etUpdatePuntuacion.setError("El dato debe estar entre 1-5");
                                    }
                                    etUpdatePuntuacion.requestFocus();
                                    return;
                                } else {
                                    int duracionP = Integer.parseInt(duracion);
                                    int puntuacionP = Integer.parseInt(puntuacion);
                                    if(fotoPUpdate.getDrawable()!=null && imageToStore!=null) {
                                        pelicula = new Pelicula(id, nombre, genero, compania, duracionP, puntuacionP, imageToStore);
                                    }
                                    else {
                                        pelicula = new Pelicula(id, nombre, genero, compania, duracionP, puntuacionP);
                                    }
                                    long actualizado = peliculaController.actualizarPelicula(pelicula);
                                    if (actualizado == -1){
                                        if(Locale.getDefault().getLanguage().equals("en")){
                                            Toast.makeText(UpdatePeliculaActivity.this, "Error while updating this movie", Toast.LENGTH_LONG).show();
                                        }
                                        else if(Locale.getDefault().getLanguage().equals("es")){
                                            Toast.makeText(UpdatePeliculaActivity.this, "Error al actualizar la película", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    else {
                                        if(Locale.getDefault().getLanguage().equals("en")){
                                            Toast.makeText(UpdatePeliculaActivity.this, "Movie updated successfully", Toast.LENGTH_LONG).show();
                                        }
                                        else if(Locale.getDefault().getLanguage().equals("es")){
                                            Toast.makeText(UpdatePeliculaActivity.this, "Se actualizó correctamente", Toast.LENGTH_LONG).show();
                                        }
                                        finish();
                                    }
                                }
                            }
                        }
                    }
                    catch (Exception ex)
                    {
                        Toast.makeText(UpdatePeliculaActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        catch (Exception ex){
            Toast.makeText(this, "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
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
                fotoPUpdate.setImageBitmap(imageToStore);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
