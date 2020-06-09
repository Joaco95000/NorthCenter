package com.example.topcinema.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.topcinema.R;
import com.example.topcinema.modelos.Pelicula;
import java.util.ArrayList;
import java.util.Locale;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.ViewHolderDatos> {
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
        public ImageView ivDelete, ivUpdate, ivPeliculaPoster, star1, star2, star3, star4, star5;
        public ViewHolderDatos(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            ivPeliculaPoster = itemView.findViewById(R.id.ivPeliculaPoster);
            star1 = itemView.findViewById(R.id.star1);
            star2 = itemView.findViewById(R.id.star2);
            star3 = itemView.findViewById(R.id.star3);
            star4 = itemView.findViewById(R.id.star4);
            star5 = itemView.findViewById(R.id.star5);
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
            if(Locale.getDefault().getLanguage().equals("en")){
                tvID.setText(s.getId()+"");
                tvNombre.setText("Name: " + s.getNombre());
                tvCompania.setText("Company: " + s.getCompania());
            }
            else if(Locale.getDefault().getLanguage().equals("es")){
                tvID.setText(s.getId()+"");
                tvNombre.setText("Nombre: " + s.getNombre());
                tvCompania.setText("Compañía: " + s.getCompania());
            }
            else{ //quechua
                tvID.setText(s.getId()+"");
                tvNombre.setText("Sutiy: " + s.getNombre());
                tvCompania.setText("Jatun Wasi: " + s.getCompania());
            }
            if(s.getPuntuacion() < 5){
                star5.setImageResource(R.drawable.emptystar);
            }
            if(s.getPuntuacion() < 4){
                star4.setImageResource(R.drawable.emptystar);
            }
            if(s.getPuntuacion() < 3){
                star3.setImageResource(R.drawable.emptystar);
            }
            if(s.getPuntuacion() < 2){
                star2.setImageResource(R.drawable.emptystar);
            }
        }
    }

    //Constructor:
    public PeliculaAdapter(ArrayList<Pelicula> listaDatos){ this.listaDatos = listaDatos; }

    //Debemos enlazar este adapatador con el archivo de elementos de la lista en XML
    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_pelicula_card, parent, false); //ACA ESTA ELEMENTOS_LISTA parent, false
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
