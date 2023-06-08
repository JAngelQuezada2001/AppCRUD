package com.example.appcrud.controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.appcrud.modelos.Libro;

import java.util.ArrayList;
import java.util.List;

public class LibroBD extends SQLiteOpenHelper implements ILibroBD{
    Context contexto;
    private List<Libro> librosList = new ArrayList<>();

    public LibroBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.contexto = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE libros ("+
                "_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "titulo TEXT, "+
                "subtitulo TEXT, "+
                "isbn TEXT, "+
                "autor TEXT, "+
                "anyo INTEGER, "+
                "precio REAL) ";
        sqLiteDatabase.execSQL(sql);
        String insert = "INSERT INTO libros VALUES (null, " +
                "'Como programar en Java', " +
                "'Mas de 88 ejemplos', "+
                "'987654321', " +
                "'Deiter & deiter', 2008, 145000)";
        sqLiteDatabase.execSQL(insert);
        insert = "INSERT INTO libros VALUES (null, " +
                "'El gran libro de Android', " +
                "'Android con ejemplos', " +
                "'123456789', " +
                "'Jesus Tomas G.', 2020, 135000)";
        sqLiteDatabase.execSQL(insert);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    @Override
    public Libro elemento(int ID) {
        SQLiteDatabase datebase = getReadableDatabase();
        String sql = "SELECT * FROM libros WHERE _id=" + ID;
        Cursor cursor = datebase.rawQuery(sql, null);
        try {
            if (cursor.moveToNext()) {
                return extraerlibro(cursor);
            }else {
                return null;
            }
        }catch (Exception e){
            Log.d("TAG","Error elemento(id) Libro80" + e.getMessage());
            throw e;
        }finally {
            if (cursor != null ) cursor.close();
        }
    }

    private Libro extraerlibro(Cursor cursor) {
        Libro libro = new Libro();
        libro.setID(cursor.getInt(0));
        libro.setTitulo(cursor.getString(1));
        libro.setSubtitulo(cursor.getString(2));
        libro.setIsbn(cursor.getString(3));
        libro.setAutor(cursor.getString(4));
        libro.setAnioPublicados(cursor.getInt(5));
        libro.setPrecio(cursor.getDouble(6));
        return libro;
    }

    @Override
    public Libro elemento(String title) {
        SQLiteDatabase datebase = getReadableDatabase();
        String sql = "SELECT * FROM libros WHERE titulo=" + title + "''";
        Cursor cursor = datebase.rawQuery(sql, null);
        try {
            if (cursor.moveToNext()) {
                return extraerlibro(cursor);
            }else {
                return null;
            }
        }catch (Exception e){
            Log.d("TAG","Error elemento(title) Libro80" + e.getMessage());
            throw e;
        }finally {
            if (cursor != null ) cursor.close();
        }
    }

    @Override
    public List<Libro> lista() {
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM libros ORDER BY titulo ASC";
        Cursor cursor = database.rawQuery(sql,null);
        if (cursor.moveToFirst()) {
            do {
                librosList.add(
                        new Libro(cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getInt(5),
                                cursor.getDouble(6))
                );
            }while (cursor.moveToNext());
        }
        cursor.close();
        return librosList;
    }

    @Override
    public void agregar(Libro book) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titulo",book.getTitulo());
        values.put("subtitulo",book.getSubtitulo());
        values.put("autor",book.getAutor());
        values.put("isbn",book.getIsbn());
        values.put("anyo",book.getAnioPublicados());
        values.put("precio",book.getPrecio());
        database.insert("libros",null,values);


    }

    @Override
    public void actualizar(int id, Libro book) {
        SQLiteDatabase database = getWritableDatabase();
        String[] parametros = {String.valueOf(id)};
        ContentValues values = new ContentValues();
        values.put("titulo",book.getTitulo());
        values.put("subtitulo",book.getSubtitulo());
        values.put("autor",book.getAutor());
        values.put("isbn",book.getIsbn());
        values.put("anyo",book.getAnioPublicados());
        values.put("precio",book.getPrecio());
        database.update("libros",values,"_id=?",parametros);
    }

    @Override
    public void borrar(int ID) {
        SQLiteDatabase database = getWritableDatabase();
        String[] parametros = {String.valueOf(ID)};
        database.delete("libros","_id=?",parametros);
    }
}
