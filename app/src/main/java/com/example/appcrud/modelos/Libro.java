package com.example.appcrud.modelos;

public class Libro {
    private int ID;
    private String titulo, subtitulo, isbn, autor;
    private int anioPublicados;
    private double precio;

    public Libro() {
    }

    public Libro(int ID, String titulo, String subtitulo, String isbn, String autor, int anioPublicados, double precio) {
        this.ID = ID;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.isbn = isbn;
        this.autor = autor;
        this.anioPublicados = anioPublicados;
        this.precio = precio;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnioPublicados() {
        return anioPublicados;
    }

    public void setAnioPublicados(int anioPublicados) {
        this.anioPublicados = anioPublicados;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "ID=" + ID +
                ", titulo='" + titulo + '\'' +
                ", subtitulo='" + subtitulo + '\'' +
                ", isbn='" + isbn + '\'' +
                ", autor='" + autor + '\'' +
                ", anioPublicados=" + anioPublicados +
                ", precio=" + precio +
                '}';
    }
}
