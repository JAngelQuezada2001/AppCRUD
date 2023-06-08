package com.example.appcrud.controladores;

import com.example.appcrud.modelos.Libro;

import java.util.List;

public interface ILibroBD {
    Libro elemento(int ID);
    Libro elemento(String title);

    List<Libro> lista();

    void agregar(Libro book);
    void actualizar(int id, Libro Book);

    void borrar(int ID);
}
