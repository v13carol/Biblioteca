package ds6.dpc.fisc.utp.biblioteca;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ds6.dpc.fisc.utp.biblioteca.data.BaseDatos;
import ds6.dpc.fisc.utp.biblioteca.data.Entidad;
import ds6.dpc.fisc.utp.biblioteca.data.Esquema;

import static ds6.dpc.fisc.utp.biblioteca.data.Esquema.Libro.AREA;
import static ds6.dpc.fisc.utp.biblioteca.data.Esquema.Libro.AUTOR;
import static ds6.dpc.fisc.utp.biblioteca.data.Esquema.Libro.AÑO;
import static ds6.dpc.fisc.utp.biblioteca.data.Esquema.Libro.DATABASE_NAME;
import static ds6.dpc.fisc.utp.biblioteca.data.Esquema.Libro.DATABASE_VERSION;
import static ds6.dpc.fisc.utp.biblioteca.data.Esquema.Libro.EDITORIAL;
import static ds6.dpc.fisc.utp.biblioteca.data.Esquema.Libro.ID;
import static ds6.dpc.fisc.utp.biblioteca.data.Esquema.Libro.ISBN;
import static ds6.dpc.fisc.utp.biblioteca.data.Esquema.Libro.TABLE_NAME;
import static ds6.dpc.fisc.utp.biblioteca.data.Esquema.Libro.TITULO;
/**Consulta todos los libros*/
public class TodosLibrosActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<Entidad> libros;
    ArrayList<String> lista_info;

    BaseDatos cnn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_libros);

        cnn = new BaseDatos(this,DATABASE_NAME,null,DATABASE_VERSION);

        listView = (ListView) findViewById(R.id.lista_libros);
        consultar_lista();

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,lista_info);
        listView.setAdapter(adapter);


    }

    /** PARA HACER LA CONSULTA DE TODOS LOS LIBROS SE UTILIZAN 2 METODOS
     * 1. CONSULTAR_LISTA EN ESTE SE BUSCAN LOS DATOS DE LA BASE DE DATOS PARA SER GUARDADOS EN UNA LISTA
     * 3. DONDE LUEGO EN EL METODO OBTENER_LISTA SE OBTIENEN LOS DATOS DONDE SE GUARDAN EN OTRA LISTA EN LA CUAL
     * ES DONDE SE MUESTRAN LOS DATOS EN LA LISTVIEW CREADA EN EL XML*/
    private void consultar_lista() {

           SQLiteDatabase db = cnn.getReadableDatabase();

           Cursor cursor = null;
           Entidad en = null;

           libros = new ArrayList<Entidad>();


           cursor = db.rawQuery(" SELECT * FROM "+TABLE_NAME,null);

           while (cursor.moveToNext())
           {    en = new Entidad();

               en.setId(cursor.getInt(0));
               en.setIsbn(cursor.getString(1));
               en.setTitulo(cursor.getString(2));
               en.setAutor(cursor.getString(3));
               en.setArea(cursor.getString(4));
               en.setEditorial(cursor.getString(5));
               en.setYear(cursor.getString(6));


               Log.i("ID ",en.getId().toString());
               Log.i("ISBN ",en.getIsbn().toString());
               Log.i("TITULO ",en.getTitulo().toString());
               Log.i("AUTOR ",en.getAutor().toString());
               Log.i("AREA ",en.getArea().toString());
               Log.i("EDITORIAL ",en.getEditorial().toString());
               Log.i("AÑO ",en.getYear().toString());


               libros.add(en);

           }

           obtener_lista();

    }
    private void obtener_lista() {
        lista_info = new ArrayList<>();
        for(int i=0; i < libros.size(); i++)
        {
              lista_info.add("\n"+
                      "CODIGO:  "+libros.get(i).getIsbn()
                      +libros.get(i).getId()+"\n"
                      +"LIBRO:  "+libros.get(i).getTitulo()+"\n"
                      +"AUTOR:  "+libros.get(i).getAutor()+"\n"
                      +"AREA:  "+libros.get(i).getArea()+"\n"
                      +"EDITORIAL:  "+libros.get(i).getEditorial()+"\n"
                      +"AÑO:  "+libros.get(i).getYear()+"\n"
                            );
        }
    }
}
