package ds6.dpc.fisc.utp.biblioteca.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.time.Year;

import ds6.dpc.fisc.utp.biblioteca.MainActivity;

import static ds6.dpc.fisc.utp.biblioteca.data.Esquema.Libro.AREA;
import static ds6.dpc.fisc.utp.biblioteca.data.Esquema.Libro.AUTOR;
import static ds6.dpc.fisc.utp.biblioteca.data.Esquema.Libro.AÑO;
import static ds6.dpc.fisc.utp.biblioteca.data.Esquema.Libro.CREATE_TABLE;
import static ds6.dpc.fisc.utp.biblioteca.data.Esquema.Libro.DATABASE_NAME;
import static ds6.dpc.fisc.utp.biblioteca.data.Esquema.Libro.DATABASE_VERSION;
import static ds6.dpc.fisc.utp.biblioteca.data.Esquema.Libro.EDITORIAL;
import static ds6.dpc.fisc.utp.biblioteca.data.Esquema.Libro.ID;
import static ds6.dpc.fisc.utp.biblioteca.data.Esquema.Libro.ISBN;
import static ds6.dpc.fisc.utp.biblioteca.data.Esquema.Libro.TABLE_NAME;
import static ds6.dpc.fisc.utp.biblioteca.data.Esquema.Libro.TITULO;

/** Manejador base de datos Biblioteca.db*/

public class BaseDatos extends SQLiteOpenHelper {


    public BaseDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

        //INSERTAR LOS DATOS POR DEFECTO A LA TABLA LIBROS PARA QUE LA BASE DE DATOS NO ESTE VACIA
          Tabla_Libros(db);

    }

    private void Tabla_Libros(SQLiteDatabase db) {
        long idRes;

        ContentValues values = new ContentValues();

        values.put(ISBN,"HS00");
        values.put(TITULO,"Historia 1");
        values.put(AUTOR,"Carolina Valdés");
        values.put(AREA,"Historia");
        values.put(EDITORIAL,"Santillana");
        values.put(AÑO,"2000");

        idRes = db.insert(TABLE_NAME,null, values);

        values.put(ISBN,"MT00");
        values.put(TITULO,"Matematica 1");
        values.put(AUTOR,"Carolina Valdés");
        values.put(AREA,"Matematica");
        values.put(EDITORIAL,"Santillana");
        values.put(AÑO,"2001");

        idRes = db.insert(TABLE_NAME,null, values);

        values.put(ISBN,"ES00");
        values.put(TITULO,"Español 1");
        values.put(AUTOR,"Carolina Valdés");
        values.put(AREA,"Español");
        values.put(EDITORIAL,"Santillana");
        values.put(AÑO,"2002");

        idRes = db.insert(TABLE_NAME,null, values);

        values.put(ISBN,"IN00");
        values.put(TITULO,"Ingles 1");
        values.put(AUTOR,"Carolina Valdés");
        values.put(AREA,"Ingles");
        values.put(EDITORIAL,"Santillana");
        values.put(AÑO,"2003");

        idRes = db.insert(TABLE_NAME,null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);

          onCreate(db);
    }
}
