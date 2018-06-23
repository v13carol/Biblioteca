package ds6.dpc.fisc.utp.biblioteca;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
/** Eliminar un libro*/
public class EliminarActivity extends AppCompatActivity {
        Button eliminar_boton;
        ImageButton buscar_boton;
        EditText buscar, titulo;

    BaseDatos cnn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);
        cnn = new BaseDatos(this, DATABASE_NAME, null, DATABASE_VERSION);

        buscar = (EditText) findViewById(R.id.et_id2);
        titulo = (EditText) findViewById(R.id.et_titulo3);

        buscar_boton = (ImageButton) findViewById(R.id.buscar_button2);

        buscar_boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buscar_libro();
                titulo.setVisibility(View.VISIBLE);
                eliminar_boton.setVisibility(View.VISIBLE);
            }
        });


        eliminar_boton = (Button) findViewById(R.id.eliminar_button);

        eliminar_boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar_libro();
            }
        });

    }

    /** SE UTILIZAN TRES METODOS PARA ELIMINAR UN LIBRO
     * 1. EL METODO UNO BUSCAR_LIBRO, BUSCA EL LIBRO QUE EL USUARIO DESEA ELIMINAR
     * 2. ELIMINAR_LIBRO ES METODO ELIMINA EL LIBRO PERO ANTES DE HACERLO PREGUNTA AL USUARIO SI ESTA SEGURO DE ELIMINAR
     * 3. METODO LIMPIAR, LIMPIA LOS CAMPOS PARA PODER HACER OTRA CONSULTA*/
    private void buscar_libro() {
        SQLiteDatabase db = cnn.getReadableDatabase();
        String[] parametros = {buscar.getText().toString()};


        try{
            Cursor cursor = db.query(TABLE_NAME, new String[]{TITULO},ISBN+"||"+ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            titulo.setText(cursor.getString(0));

            cursor.close();

        }

        catch(Exception e)
        {
            Toast.makeText(this," código no encontrado.",Toast.LENGTH_SHORT).show();
            limpiar();
        }
    }
    private void eliminar_libro() {

        AlertDialog.Builder msg = new AlertDialog.Builder(this);
        msg.setMessage("¿Está seguro que quiere eliminar este libro?");
        msg.setTitle("Eliminando Libro...");
        msg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            SQLiteDatabase db = cnn.getReadableDatabase();
            String[] parametros = {buscar.getText().toString()};

            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.delete(TABLE_NAME, ISBN+" || "+ID+"=? ",parametros);
                limpiar();
                db.close();
            }
        });

        msg.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                  dialog.cancel();
            }
        });

        AlertDialog dialog = msg.create();
        dialog.show();


    }
    private void limpiar() {
        titulo.setText("");
        buscar.setText(" ");
    }

}
