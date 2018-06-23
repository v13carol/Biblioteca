package ds6.dpc.fisc.utp.biblioteca;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.Year;
import java.util.ArrayList;

import ds6.dpc.fisc.utp.biblioteca.data.BaseDatos;

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

/**agregar un libro */
public class AgregarActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
     Spinner sp_area;
     String[] area;
     ImageButton button_agregar;
     EditText titulo, autor, editorial, año;
     String area_libro, isbn;

     BaseDatos cnn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        cnn = new BaseDatos(this, DATABASE_NAME,null,DATABASE_VERSION);

        titulo = (EditText) findViewById(R.id.et_titulo);
        autor = (EditText) findViewById(R.id.et_autor);
        editorial = (EditText) findViewById(R.id.et_editorial);
        año = (EditText) findViewById(R.id.et_year);
        area = getResources().getStringArray(R.array.areas);

        sp_area = (Spinner) findViewById(R.id.sp_area);
        sp_area.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,area));
        sp_area.setOnItemSelectedListener(this);

        button_agregar = (ImageButton) findViewById(R.id.agregar_Button);
        button_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar_libro();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position)
        {
            case 0:
                area_libro = "Historia";
                    isbn = "HS00";
            break;

            case 1:
                area_libro = "Matemática";
                isbn = "MT00";
            break;

            case 2:
                area_libro = "Español";
                isbn =  "ES00";
            break;

            case 3:
                area_libro = "Inglés";
                isbn = "IN00";
            break;
        }
       metodo_area_libro(area_libro, isbn);
    }

    private void metodo_area_libro(String area_libro, String isbn) {
        this.area_libro = area_libro;
        this.isbn = isbn ;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // METODO DONDE SE HACE LA SENTENCIA DE INSERCCION A LA BASE DE DATOS
    private void  insertar_libro() {
        long Idres;
        SQLiteDatabase db = cnn.getWritableDatabase();
        ContentValues values = new ContentValues();
        try{
             db.execSQL("INSERT INTO "+TABLE_NAME+"("+ISBN+","+TITULO+","+AUTOR
                    +","+AREA+","+EDITORIAL+","+AÑO+" ) VALUES ( '"+isbn+"' , '"+titulo.getText().toString()+"', '"
                     +autor.getText().toString()+"' , '"+area_libro+"' , '"+editorial.getText().toString()+"' , '"+año.getText().toString()+"' ) ");

            Toast.makeText(this, "Libro insertado"+isbn+titulo.getText().toString()+autor.getText().toString()+area_libro+editorial.getText().toString()+año.getText().toString(),Toast.LENGTH_LONG).show();
            db.close();


       }catch(Exception e){
           Toast.makeText(this, "no se pudo insertar el libro",Toast.LENGTH_SHORT).show();
       }



    }

}
