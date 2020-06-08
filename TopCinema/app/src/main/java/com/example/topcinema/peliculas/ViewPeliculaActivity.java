package com.example.topcinema.peliculas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;

import com.example.topcinema.R;
import com.example.topcinema.controllers.PeliculaController;
import com.example.topcinema.modelos.Pelicula;
import com.example.topcinema.adapters.PeliculaAdapter;

public class ViewPeliculaActivity extends AppCompatActivity {
    ArrayList<Pelicula> listaDatos = new ArrayList<Pelicula>();

    RecyclerView rvPeliculas;
    PeliculaAdapter adaptadorDatos;

    EditText etFiltrarPelicula;
    ImageView imgPe;

    PeliculaController peliculaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula_view);
        rvPeliculas = findViewById(R.id.rvPeliculas);
        etFiltrarPelicula= findViewById(R.id.etFiltrarPelicula);
        imgPe = findViewById(R.id.fotoP);
        rvPeliculas = findViewById(R.id.rvPeliculas);
        mostrarLista();

        etFiltrarPelicula.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0) mostrarLista();
                else mostrarLista(s.toString());
            }
        });
    }

    public void mostrarLista(){
        rvPeliculas.setLayoutManager(new GridLayoutManager(this, 1));
        peliculaController = new PeliculaController(ViewPeliculaActivity.this);
        listaDatos = peliculaController.listaDePeliculas();
        adaptadorDatos = new PeliculaAdapter(listaDatos);
        rvPeliculas.setAdapter(adaptadorDatos);

        adaptadorDatos.setOnItemClickListener(new PeliculaAdapter.OnItemClickListener() {
            @Override
            public void onUpdateClick(int id) {
                Intent intent = new Intent(getApplicationContext(), UpdatePeliculaActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int id) {
                deleteID(id);
            }
        });
    }

    public void mostrarLista(String buscar){
        rvPeliculas.setLayoutManager(new GridLayoutManager(this, 1));
        peliculaController = new PeliculaController(ViewPeliculaActivity.this);
        listaDatos = peliculaController.listaDePeliculas(buscar);
        adaptadorDatos = new PeliculaAdapter(listaDatos);
        rvPeliculas.setAdapter(adaptadorDatos);

        adaptadorDatos.setOnItemClickListener(new PeliculaAdapter.OnItemClickListener() {
            @Override
            public void onUpdateClick(int id) {
                Intent intent = new Intent(getApplicationContext(), UpdatePeliculaActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int id) {
                deleteID(id);
            }
        });
    }

    public void deleteID(final int id){
        final Pelicula p = peliculaController.peliculaEspecifica(id);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("¿Estás segur@ que quieres eliminar esta película: " + p.getNombre() + "\nYa no se podrá recuperar los datos cuando se elimina.")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(peliculaController.eliminarPelicula(id)>0) Toast.makeText(ViewPeliculaActivity.this, "Si se eliminó la película", Toast.LENGTH_LONG).show();
                        else Toast.makeText(ViewPeliculaActivity.this, "Se generó un error, intenta luego", Toast.LENGTH_LONG).show();
                        if(etFiltrarPelicula.length() == 0) mostrarLista();
                        else mostrarLista(etFiltrarPelicula.getText().toString());
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Toast.makeText(ViewPeliculaActivity.this, "No se eliminó la película", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ViewPeliculaActivity.this, "No se eliminó la película", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog alt = alert.create();
        alt.setTitle("Eliminando Película");
        alt.show();
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

    @Override
    public void onRestart() {
        super.onRestart();
        mostrarLista();
    }
}
