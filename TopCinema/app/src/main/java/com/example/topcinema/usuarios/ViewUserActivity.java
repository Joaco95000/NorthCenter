package com.example.topcinema.usuarios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.topcinema.controllers.AyudanteBaseDeDatos;
import com.example.topcinema.R;
import com.example.topcinema.controllers.UsuarioController;
import com.example.topcinema.modelos.Usuario;
import com.example.topcinema.adapters.UsuarioAdapter;

import java.util.ArrayList;

public class ViewUserActivity extends AppCompatActivity {
    ArrayList<Usuario> listUsuarios = new ArrayList<Usuario>();
    RecyclerView recyclerView;
    UsuarioController usuarioController;
    EditText etFiltrarUsuario;

    AyudanteBaseDeDatos ayudanteBaseDeDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user_view);
            recyclerView =findViewById(R.id.ListUsers);
            etFiltrarUsuario = findViewById(R.id.etFiltrarUsuario);
            cargarUsuarios();

            etFiltrarUsuario.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(count == 0) cargarUsuarios();
                    else cargarUsuarios(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    private void cargarUsuarios()
    {
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        usuarioController = new UsuarioController(ViewUserActivity.this);
        listUsuarios = usuarioController.listaDeUsuarios();
        UsuarioAdapter usuarioAdapter = new UsuarioAdapter(listUsuarios);
        recyclerView.setAdapter(usuarioAdapter);
    }

    private void cargarUsuarios(String buscar)
    {
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        usuarioController = new UsuarioController(ViewUserActivity.this);
        listUsuarios = usuarioController.listaDeUsuarios(buscar);
        UsuarioAdapter usuarioAdapter = new UsuarioAdapter(listUsuarios);
        recyclerView.setAdapter(usuarioAdapter);
    }

    public void cargarRegister(View w)
    {
        try {
            Intent intent = new Intent(this, RegisterUserActivity.class);
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
        cargarUsuarios();
    }
}
