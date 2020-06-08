package com.example.topcinema.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.topcinema.R;
import com.example.topcinema.modelos.Usuario;

import java.util.ArrayList;
import java.util.Locale;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuariosViewHolder> {
    ArrayList<Usuario> listUsuarios;

    public class UsuariosViewHolder extends RecyclerView.ViewHolder
    {
        TextView nombre, correo, usuario;

        public UsuariosViewHolder(@NonNull View itemView){
            super(itemView);
            nombre = itemView.findViewById(R.id.tvNombreUsuario);
            usuario = itemView.findViewById(R.id.tvUs); //tvUsername
            correo = itemView.findViewById(R.id.tvCorreo);
        }

        public void asignarUsuarios (Usuario u){
            if(Locale.getDefault().getLanguage().equals("en")){
                usuario.setText("USERNAME: " + u.getUsuario());
                nombre.setText("Name: " + u.getNombre());
                correo.setText("Email: " + u.getCorreo());
            }
            else if(Locale.getDefault().getLanguage().equals("es")){
                usuario.setText("USUARIO: " + u.getUsuario());
                nombre.setText("Nombre: " + u.getNombre());
                correo.setText("Correo: " + u.getCorreo());
            }
            else{ //quechua
                usuario.setText("RUNA: " + u.getUsuario());
                nombre.setText("Sutiy: " + u.getNombre());
                correo.setText("Ch'aski: " + u.getCorreo());
            }
        }
    }

    public UsuarioAdapter(ArrayList<Usuario> listUsuarios){
        this.listUsuarios = listUsuarios;
    }

    @NonNull
    @Override
    public UsuariosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list, parent, false); //ACA ESTA ELEMENTOS_LISTA parent, false
        return new UsuariosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioAdapter.UsuariosViewHolder holder, int position) {
        holder.asignarUsuarios(listUsuarios.get(position));
    }

    @Override
    public int getItemCount() {
        return listUsuarios.size();
    }
}
