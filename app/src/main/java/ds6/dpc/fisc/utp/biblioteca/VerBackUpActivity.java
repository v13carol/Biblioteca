package ds6.dpc.fisc.utp.biblioteca;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Vector;
/** lectura del archivo txt */
public class VerBackUpActivity extends AppCompatActivity {
          TextView back_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verbackup);

        back_up = (TextView) findViewById(R.id.tv_backup);

        lectura();
    }
/** metodo de lectura del archivo para mostrar la lectura como esta en el archivo con los saltos de linea lo que  hicimos fue
 * crear un arreglo string e ingresando las lineas una por una para mostrar esas lineas en un textview
 * no sabemos si hay una manera más optima pero esta fue la que se nos ocurrio intentamos hacerlo con un ciclo repetitivo
 * pero no funciono*/

    @SuppressLint("SetTextI18n")
    private void lectura() {
         String Archivo = "DBBackup.txt";
        try
        {  File ruta = Environment.getDataDirectory();
           File f = new File(ruta+"//data//ds6.dpc.fisc.utp.biblioteca",Archivo);

            BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
             String texto[] = {fin.readLine(),fin.readLine(),fin.readLine(),fin.readLine()
             ,fin.readLine(),fin.readLine(),fin.readLine(),fin.readLine(),fin.readLine(),
             fin.readLine(),fin.readLine()};


                  back_up.setText(texto[0]+"\n"+texto[1]+"\n"+texto[2]+"\n"+texto[3]+"\n"+texto[4]+"\n"+
                          texto[5]+"\n"+texto[6]+"\n"+texto[7]+"\n"+texto[8]+"\n"+texto[9]+"\n"
                          +texto[10]);

            fin.close();

        }catch(Exception e)
        {
            Toast.makeText(this,"ERROR en buscar el Backup",Toast.LENGTH_LONG).show();
        }

    }
}
