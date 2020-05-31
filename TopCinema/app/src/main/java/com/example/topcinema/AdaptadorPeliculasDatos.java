package com.example.topcinema;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.topcinema.modelos.Pelicula;

import java.util.ArrayList;

public class AdaptadorPeliculasDatos extends RecyclerView.Adapter<AdaptadorPeliculasDatos.ViewHolderDatos> {
    //Arreglo Dinamico:
    ArrayList<Pelicula> listaDatos;

    //Constructor:
    public AdaptadorPeliculasDatos(ArrayList<Pelicula> listaDatos){ this.listaDatos = listaDatos; }

    //Debemos enlazar este adapatador con el archivo de elementos de la lista en XML
    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.peliculas_lista, null, false); //ACA ESTA ELEMENTOS_LISTA
        return new ViewHolderDatos(view);
    }

    //Permitirá la comunicación entre el adaptador y la clase ViewHolderDatos
    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) { holder.asignarDatos(listaDatos.get(position)); }

    //Como vamos a usar una colección del tipo ArrayList, debemos de retornar el tamaño de la misma
    @Override
    public int getItemCount() { return listaDatos.size(); }

    //Vamos a crear la clase
    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView dato, dato1;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            dato = itemView.findViewById(R.id.tvNombre);
            dato1 = itemView.findViewById(R.id.tvCompania);
        }
        //vamos a cargar la cadena de caracteres en el TextView
        public void asignarDatos(Pelicula s) {
            dato.setText("Nombre: " + s.getNombre());
            dato1.setText("Compañía: " + s.getCompania());
        }
    }
}
