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

public class BuscarLibroActivity extends AppCompatActivity implements View.OnClickListener{

    Context contexto;
    EditText txttitulo;
    Button btnbuscar;
    LibroBD libroBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_libro);
        init();
    }
    private void init(){
        contexto = getApplicationContext();
        txttitulo = findViewById(R.id.bus_txttitulo);
        btnbuscar = findViewById(R.id.bus_btnbuscar);
    }

    @Override
    public void onClick(View viem) {
        if(viem.getId() == R.id.btnbuscar){
            String titulo = txttitulo.getText().toString();
            Libro libro = buscarLibro( titulo );
            if (libro != null) {
                Bundle bolsa = new Bundle();
                bolsa.putInt("id",libro.getID());
                bolsa.putString("titulo", libro.getTitulo());
                bolsa.putString("subtitulo", libro.getSubtitulo());
                bolsa.putString("autor", libro.getAutor());
                bolsa.putString("isbn", libro.getIsbn());
                bolsa.putInt("anio_publicacion", libro.getAnioPublicados());
                bolsa.putDouble("precio", libro.getPrecio());

                Intent i =new Intent(contexto, GestionarLibroActivity.class);
                i.putExtras(bolsa);
                startActivity( i );
            }else {
                Toast.makeText(contexto,"No existe el libro con ese titulo", Toast.LENGTH_LONG).show();
            }
        }

    }
    private Libro buscarLibro(String titulo) {
        libroBD = new LibroBD(contexto, "librosBD.db",null,1);
        Libro libro = libroBD.elemento(titulo);

        return libro;
    }
}