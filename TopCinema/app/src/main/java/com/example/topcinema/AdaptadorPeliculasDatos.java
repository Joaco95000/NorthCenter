package com.example.topcinema;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.topcinema.modelos.Pelicula;
import java.util.ArrayList;

public class AdaptadorPeliculasDatos extends RecyclerView.Adapter<AdaptadorPeliculasDatos.ViewHolderDatos> {
    //Arreglo Dinamico:
    ArrayList<Pelicula> listaDatos;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onUpdateClick(int id);
        void onDeleteClick(int id);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    //Vamos a crear la clase
    public static class ViewHolderDatos extends RecyclerView.ViewHolder { //MAYBE: static
        public TextView tvNombre, tvCompania, tvID;
        public ImageView ivDelete, ivUpdate, ivPeliculaPoster;
        public ViewHolderDatos(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            ivPeliculaPoster = itemView.findViewById(R.id.ivPeliculaPoster);
            tvID = itemView.findViewById(R.id.tvID);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvCompania = itemView.findViewById(R.id.tvCompania);
            ivUpdate = itemView.findViewById(R.id.ivUpdate);
            ivDelete = itemView.findViewById(R.id.ivDelete);
            ivUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int id = Integer.parseInt(tvID.getText().toString());
                        listener.onUpdateClick(id);
                    }
                }
            });
            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int id = Integer.parseInt(tvID.getText().toString());
                        listener.onDeleteClick(id);
                    }
                }
            });
        }
        //vamos a cargar la cadena de caracteres en el TextView
        public void asignarDatos(Pelicula s) {
            ivPeliculaPoster.setImageBitmap(s.getFoto());
            tvID.setText(s.getId()+"");
            tvNombre.setText("Nombre: " + s.getNombre());
            tvCompania.setText("Compañía: " + s.getCompania());
        }
    }

    //Constructor:
    public AdaptadorPeliculasDatos(ArrayList<Pelicula> listaDatos){ this.listaDatos = listaDatos; }

    //Debemos enlazar este adapatador con el archivo de elementos de la lista en XML
    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.peliculas_lista, parent, false); //ACA ESTA ELEMENTOS_LISTA parent, false
        return new ViewHolderDatos(view, mListener);
    }

    //Permitirá la comunicación entre el adaptador y la clase ViewHolderDatos
    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listaDatos.get(position));
    }

    //Como vamos a usar una colección del tipo ArrayList, debemos de retornar el tamaño de la misma
    @Override
    public int getItemCount() { return listaDatos.size(); }
}
