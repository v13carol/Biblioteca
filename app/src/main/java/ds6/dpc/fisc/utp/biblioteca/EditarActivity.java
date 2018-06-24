package ds6.dpc.fisc.utp.biblioteca;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

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
/** Editar un libro */
public class EditarActivity extends AppCompatActivity {
      EditText titulo, area, autor, editorial, año, isbn;
      Button editar_boton;
      ImageButton buscar_boton;
      BaseDatos cnn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        cnn = new BaseDatos(this,DATABASE_NAME, null, DATABASE_VERSION);

        isbn = (EditText) findViewById(R.id.et_id);
        titulo = (EditText) findViewById(R.id.et_titulo2);
        autor = (EditText) findViewById(R.id.et_autor2);
        area  = (EditText) findViewById(R.id.et_area2);
        editorial = (EditText) findViewById(R.id.et_editorial2);
        año = (EditText) findViewById(R.id.et_year2);

        editar_boton = (Button) findViewById(R.id.editar_button);

        buscar_boton = (ImageButton) findViewById(R.id.buscar_button);


        // buscando el libro que el usuario escribio
        buscar_boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buscando_libro();

            }
        });

        editar_boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar_datos();
            }
        });
    }

    /**SE UTILIZAN ESTOS TRES METODOS PARA EDITAR EL CODIGO QUE INGRESA EL USUARIO
     * 1. EN EL METODO BUSCAR_ LIBRO LUEGO APARECEN LOS OTROS CAMPOS LLENOS CON LOS DATOS EXTRAIDO PARA PODER SER EDITADOS
     * 2. EN ACTUALIZAR_DATOS UNA VEZ QUE SE PRECIONA EL BOTON ACTUALIZAR EL PROGRAMA PREGUNTA SI ESTA SEGURO DE EDITAR LOS CAMPOS
     * 3. SE USA EL METODO LIMPIAR PARA DEJAR LOS CAMPOS VACIOS
    */
    private void buscando_libro() {
        SQLiteDatabase db = cnn.getReadableDatabase();
        String[] parametros = {isbn.getText().toString()};
        String[] campos = {TITULO,AUTOR,AREA,EDITORIAL,AÑO};

        try{
            Cursor cursor = db.query(TABLE_NAME,campos,ISBN+"||"+ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            titulo.setText(cursor.getString(0));
            autor.setText(cursor.getString(1));
            area.setText(cursor.getString(2));
            editorial.setText(cursor.getString(3));
            año.setText(cursor.getString(4));
            cursor.close();
        }

        catch(Exception e)
        {
            Toast.makeText(this," código no encontrado.",Toast.LENGTH_SHORT).show();
            limpiar();
        }
    }
    private void actualizar_datos() {

        if(isbn != null)
        {
            titulo.setVisibility(View.VISIBLE);
            autor.setVisibility(View.VISIBLE);
            area.setVisibility(View.VISIBLE);
            editorial.setVisibility(View.VISIBLE);
            año.setVisibility(View.VISIBLE);
            editar_boton.setVisibility(View.VISIBLE);


            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setMessage("¿Está seguro que quiere editar este libro?");
            msg.setTitle("Editando Libro...");
            msg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {


                    SQLiteDatabase db = cnn.getWritableDatabase();
                    String[] parametros = {isbn.getText().toString()};
                    ContentValues values = new ContentValues();
                    values.put(TITULO, titulo.getText().toString());
                    values.put(AUTOR, autor.getText().toString());
                    values.put(AREA, area.getText().toString());
                    values.put(EDITORIAL, editorial.getText().toString());
                    values.put(AÑO,año.getText().toString());

                    db.update(TABLE_NAME,values,ISBN+"||"+ID+"=?",parametros);


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


        }else
            {
                Toast.makeText(this," Por favor ingrese el código del libro ",Toast.LENGTH_SHORT).show();
            }
    }
    private void limpiar() {
        isbn.setText("");
        titulo.setText("");
        autor.setText("");
        area.setText("");
        editorial.setText("");
        año.setText("");

    }
}
