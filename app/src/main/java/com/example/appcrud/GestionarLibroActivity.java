package com.example.appcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appcrud.controladores.LibroBD;
import com.example.appcrud.modelos.Libro;

public class GestionarLibroActivity extends AppCompatActivity implements View.OnClickListener {

    Context contexto;
    EditText txttilulo, txtsubtitulo, txtisbn, txtautor, txtaniopublicacion, txtprecio;
    int id;
    LibroBD libroBD;

    Button btnguardar, btnactualizar, btnborrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_libro);
        init();
    }
    public void init(){
        contexto = getApplicationContext();
        txttilulo = findViewById(R.id.ges_txttitulo);
        txtsubtitulo = findViewById(R.id.ges_txtsubtitulo);
        txtautor = findViewById(R.id.ges_txtautor);
        txtisbn = findViewById(R.id.ges_txtisbn);
        txtaniopublicacion = findViewById(R.id.ges_txtaniopublicacion);
        txtprecio = findViewById(R.id.ges_txtprecio);
        btnguardar = findViewById(R.id.ges_btnguardar);
        btnactualizar = findViewById(R.id.ges_btnactualizar);
        btnborrar = findViewById(R.id.ges_btnborrar);

        Intent i = getIntent();
        Bundle bolsa = i.getExtras();
        id = bolsa.getInt("id");
        if (id != 0) {
            txttilulo.setText(bolsa.getString("titulo"));
            txtsubtitulo.setText(bolsa.getString("subtitulo"));
            txtautor.setText(bolsa.getString("autor"));
            txtisbn.setText(bolsa.getString("isbn"));
            txtaniopublicacion.setText(bolsa.getInt("anio_publicacion"));
            txtprecio.setText(bolsa.getDouble("precio") + "");
            btnguardar.setEnabled(false);
        }else{
            btnactualizar.setEnabled(false);
            btnborrar.setEnabled(false);
        }
    }
    private void LimpiarCampos(){
        id = 0;
        txttilulo.setText("");
        txtsubtitulo.setText("");
        txtautor.setText("");
        txtisbn.setText("");
        txtaniopublicacion.setText("");
        txtprecio.setText("");
    }
    private Libro llenarDatosLibro(){
        Libro libro = new Libro();
        String t = txttilulo.getText().toString();
        String s = txtsubtitulo.getText().toString();
        String a = txtautor.getText().toString();
        String i = txtisbn.getText().toString();
        String anio = txtaniopublicacion.getText().toString();
        String precio = txtprecio.getText().toString();

        libro.setID(id);
        libro.setTitulo(t);
        libro.setSubtitulo(s);
        libro.setAutor(a);
        libro.setIsbn(i);
        libro.setAnioPublicados(Integer.parseInt(anio));
        libro.setPrecio(Double.parseDouble(precio));

        return libro;
    }
    private void guardar(){
        libroBD = new LibroBD(contexto,"LibrosBD.db",null,1);
        Libro libro = llenarDatosLibro();
        if (id == 0) {
            libroBD.agregar(libro);
            Toast.makeText(contexto,"Guardado nuevo OK", Toast.LENGTH_LONG).show();
        }else {
            libroBD.actualizar(id, libro);
            btnactualizar.setEnabled(false);
            btnborrar.setEnabled(false);
            Toast.makeText(contexto,"Actualizado Existente", Toast.LENGTH_LONG).show();
        }

    }
    private void borrar(){
        libroBD = new LibroBD(contexto,"LibrosBD.db",null,1);
        Libro libro = llenarDatosLibro();
        if (id == 0) {
            Toast.makeText(contexto,"No es posible borrar", Toast.LENGTH_LONG).show();
        }else {
            libroBD.borrar(id);
            btnguardar.setEnabled(true);
            btnactualizar.setEnabled(false);
            btnborrar.setEnabled(false);
            Toast.makeText(contexto,"Se borro wl registro", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View viem) {
        if (viem.getId() == R.id.ges_btnguardar) {
            guardar();
        }
        if (viem.getId() == R.id.ges_btnactualizar) {
            guardar();
        }
        if (viem.getId() == R.id.ges_btnborrar) {
            borrar();
        }
    }
}