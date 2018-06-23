package ds6.dpc.fisc.utp.biblioteca;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ds6.dpc.fisc.utp.biblioteca.data.BaseDatos;
import ds6.dpc.fisc.utp.biblioteca.data.Entidad;

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
/**Buscar un libro*/
public class BuscarActivity extends AppCompatActivity {
      EditText buscar_libro;
      ImageButton boton_buscar;
      TextView libro;
      BaseDatos cnn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        cnn = new BaseDatos(this,DATABASE_NAME, null, DATABASE_VERSION);

        buscar_libro = (EditText) findViewById(R.id.et_id3);
        libro = (TextView) findViewById(R.id.libro_buscado);


        boton_buscar = (ImageButton) findViewById(R.id.buscar_button2);

        boton_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BuscandoLibro();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    /**METODO DONDE SE BUSCA EL LIBRO QUE EL USUARIO QUIERE  ESTE METODO ES USADO EN LAS CLASES EDITAR Y ELIMINAR
     * LOS DATOS SON MOSTRADOS EN UN TEXTVIEW **/
    private void BuscandoLibro() {
        SQLiteDatabase db = cnn.getReadableDatabase();
        String[] parametros = {buscar_libro.getText().toString()};
        String[] campos = {TITULO,AUTOR,AREA,EDITORIAL,AÑO};

        try {
            Cursor cursor = db.query(TABLE_NAME,campos,ISBN+"||"+ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();

            libro.setText("TITULO:  "+cursor.getString(0) + "\n" +
                    "AUTOR:  "+cursor.getString(1) + "\n" +
                    "AREA:  "+cursor.getString(2) + "\n" +
                    "EDITORIAL:  "+cursor.getString(3) + "\n" +
                    "AÑO: "+cursor.getString(4));

                if(buscar_libro != null)
                {
                    libro.setVisibility(View.VISIBLE);
                }
                
            cursor.close();
        }
        catch(Exception e)
        {
            Toast.makeText(this," código no encontrado.",Toast.LENGTH_SHORT).show();

        }


    }
}
