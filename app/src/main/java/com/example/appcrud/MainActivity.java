package com.example.appcrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelStoreOwner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    Button btnListar, btnRegistrar, btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        context = getApplicationContext();
        btnRegistrar = findViewById(R.id.btnregistrar);
        btnBuscar = findViewById(R.id.btnbuscar);
        btnListar = findViewById(R.id.btnlista);
    }


    @Override
    public void onClick(View viem) {
        if (viem.getId() == R.id.btnregistrar) {
            Toast.makeText(context,"Registrar", Toast.LENGTH_LONG).show();
            Intent i = new Intent(context, GestionarLibroActivity.class);
            Bundle bolsa = new Bundle();
            bolsa.putInt("id",0);
            i.putExtras(bolsa);
            startActivity(i);
        }
        if (viem.getId() == R.id.btnlista) {
            Toast.makeText(context,"Listar", Toast.LENGTH_LONG).show();
            Intent i2 = new Intent(context, ListadoLibrosActivity.class);
            startActivity(i2);
        }
        if (viem.getId() == R.id.btnbuscar) {
            Toast.makeText(context,"Buscar", Toast.LENGTH_LONG).show();
            Intent i3 = new Intent(context, BuscarLibroActivity.class);
            startActivity(i3);
        }

    }
}