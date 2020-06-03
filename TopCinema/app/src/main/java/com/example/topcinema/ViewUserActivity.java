package com.example.topcinema;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.topcinema.controllers.UsuarioController;
import com.example.topcinema.modelos.Usuario;
import com.example.topcinema.usuarioAdapter.UsuarioAdapter;

import java.util.ArrayList;

public class ViewUserActivity extends AppCompatActivity {
    ArrayList<Usuario> listUsuarios = new ArrayList<Usuario>();
    RecyclerView recyclerView;
    UsuarioController usuarioController;
    EditText etFiltrarUsuario;

    AyudanteBaseDeDatos ayudanteBaseDeDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
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
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }
        catch (Exception ex)
        {
            Toast.makeText(this,ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
