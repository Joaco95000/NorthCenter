package com.example.topcinema;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;
import com.example.topcinema.controllers.PeliculaController;
import com.example.topcinema.modelos.Pelicula;

public class ViewPeliculaActivity extends AppCompatActivity {
    PeliculaController peliculaController;
    RecyclerView rvPeliculas;
    ArrayList<Pelicula> listaDatos = new ArrayList<Pelicula>();
    ImageView imgPe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_pelicula);
            rvPeliculas = findViewById(R.id.rvPeliculas);
            imgPe = findViewById(R.id.fotoP);
            mostrarLista();
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    public void cargarRegisterPeliculas(View w)
    {
        try {
            Intent intent = new Intent(this, RegisterPeliculaActivity.class);
            startActivity(intent);
        }
        catch (Exception ex)
        {
            Toast.makeText(this,ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void mostrarLista(){
        try{
            rvPeliculas.setLayoutManager(new GridLayoutManager(this, 1));
            peliculaController = new PeliculaController(ViewPeliculaActivity.this);
            listaDatos = peliculaController.listaDePeliculas();
            AdaptadorPeliculasDatos adaptadorDatos = new AdaptadorPeliculasDatos(listaDatos);
            rvPeliculas.setAdapter(adaptadorDatos);
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),ex.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRestart() {
        super.onRestart();
        mostrarLista();
    }
}