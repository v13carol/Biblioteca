package ds6.dpc.fisc.utp.biblioteca;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

import static ds6.dpc.fisc.utp.biblioteca.data.Esquema.Libro.CREATE_TABLE;
import static ds6.dpc.fisc.utp.biblioteca.data.Esquema.Libro.DATABASE_NAME;
/** pagina principal donde esta un menu de botones*/
public class MainActivity extends AppCompatActivity {
          ImageButton imB, imB2, imB3, imB4, imB5, imB6;
          Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imB = (ImageButton) findViewById(R.id.AgregarButton);
        imB2 = (ImageButton) findViewById(R.id.BuscarButton);
        imB3 = (ImageButton) findViewById(R.id.EditarButton);
        imB4 = (ImageButton) findViewById(R.id.EliminarButton);
        imB5 = (ImageButton) findViewById(R.id.BackUpButton);
        imB6 = (ImageButton) findViewById(R.id.TodosButton);

        // METODOS DE CLICK DE CADA IMAGEBUTTON PARA QUE VAYA A SU RESPECTIVA ACTIVIDAD
        imB.setOnClickListener(new View.OnClickListener()  {@Override public void onClick(View v) { boton_agregar();  }});
        imB2.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { boton_buscar();   }});
        imB3.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { boton_editar(); }});
        imB4.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {boton_eliminar();}});
        imB5.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {boton_backup();}});
        imB6.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { boton_todos();}});
    }

   // AQUI SE HACE EL INTENT PARA IR A LA ACTVIDAD DE AREGAR UN LIBRO
    private void boton_agregar() {
        intent = new Intent(this,AgregarActivity.class);
        startActivity(intent);
    }

    // AQUI SE HACE EL INTENT PARA IR A LA ACTVIDAD DE BUSCAR UN LIBRO
    private void boton_buscar() {
        intent = new Intent(this,BuscarActivity.class);
        startActivity(intent);
    }

    // AQUI SE HACE EL INTENT PARA IR A LA ACTVIDAD DE ELIMINAR UN LIBRO
    private void boton_eliminar() {
        intent = new Intent(this,EliminarActivity.class);
        startActivity(intent);
    }

    // AQUI SE HACE EL INTENT PARA IR A LA ACTVIDAD DE EDITAR UN LIBRO
    private void boton_editar() {
        intent = new Intent(this,EditarActivity.class);
        startActivity(intent);
    }


    // HACE UN ARCHIVO TXT DE LA BASE DE DATOS Y SE GUARDA EN LA CARPETA DE LA APK
    private void boton_backup() {
        String Archivo = "DBBackup.txt";

        try{
            File ruta = Environment.getDataDirectory();
            File f = new File(ruta+"//data//ds6.dpc.fisc.utp.biblioteca",Archivo);
            OutputStreamWriter fout = new OutputStreamWriter ( new FileOutputStream(f));

            fout.write("Backup de la base de datos: \n"+DATABASE_NAME+"\nTabla creada:\n"+CREATE_TABLE);
            fout.close();

        }catch (Exception ex)
        {
            Toast.makeText(this," ERROR al crear el Backup de  "+DATABASE_NAME,Toast.LENGTH_SHORT).show();
        }

        intent = new Intent(this,VerBackUpActivity.class);
        startActivity(intent);

    }

    // AQUI SE HACE EL INTENT PARA IR A LA ACTVIDAD DE CONSULTAR TODOS LOS LIBROS
    private void boton_todos() {
        intent = new Intent(this,TodosLibrosActivity.class);
        startActivity(intent);
    }



}
