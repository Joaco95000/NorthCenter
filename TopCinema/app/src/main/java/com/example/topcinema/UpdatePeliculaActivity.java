package com.example.topcinema;
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
import com.example.topcinema.controllers.PeliculaController;
import com.example.topcinema.modelos.Pelicula;

import java.io.IOException;

public class UpdatePeliculaActivity extends AppCompatActivity {
    EditText etUpdateNombre, etUpdateGenero, etUpdateCompania, etUpdateDuracion, etUpdatePuntuacion;
    Button btnActualizar, btnUpdateImage;
    Pelicula pelicula;
    PeliculaController peliculaController;
    ImageView fotoPUpdate;
    Bitmap imgPUpdate;
    int id;
    private static final int PICK_IMAGE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            setContentView(R.layout.activity_update_pelicula);
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
                        if ("".equals(nombre)) {
                            etUpdateNombre.setError("Debes ingresar el nombre de la Película");
                            etUpdateNombre.requestFocus();
                            return;
                        }
                        if ("".equals(genero)) {
                            etUpdateGenero.setError("Debes ingresar el Genero de La Película");
                            etUpdateGenero.requestFocus();
                            return;
                        }
                        if ("".equals(compania)) {
                            etUpdateCompania.setError("Debes ingresar la Compañía de la Película");
                            etUpdateCompania.requestFocus();
                            return;
                        }
                        if ("".equals(duracion)) {
                            etUpdateDuracion.setError("Debes ingresar la Duración de La Película");
                            etUpdateDuracion.requestFocus();
                            return;
                        }

                        if ("".equals(puntuacion)) {
                            etUpdatePuntuacion.setError("Debes ingresar la Puntuación de la Película");
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
                            if (actualizado == -1) Toast.makeText(UpdatePeliculaActivity.this, "Error al actualizar la pelicula", Toast.LENGTH_LONG).show();
                            else {
                                Toast.makeText(UpdatePeliculaActivity.this, "Se actualizó correctamente", Toast.LENGTH_LONG).show();
                                finish();
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
