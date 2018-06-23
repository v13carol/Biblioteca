package ds6.dpc.fisc.utp.biblioteca.data;

import android.provider.BaseColumns;

/** Esquema de la base de datos para la tabla Libros*/
public class Esquema {
    public static abstract class Libro implements BaseColumns
    {   public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "Biblioteca.db";
        public static final String TABLE_NAME = "Libros";
        public static final String ID = "id";
        public static final String ISBN = "isbn" ;
        public static final String TITULO = "titulo" ;
        public static final String AUTOR = "autor" ;
        public static final String AREA = "area" ;
        public static final String EDITORIAL = "editorial";
        public static final String AÑO = "year";


        public static final String CREATE_TABLE = "CREATE TABLE  "
                +TABLE_NAME
                +"\n ("+ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, \n"
                + ISBN + " TEXT NOT NULL, \n"
                + TITULO + " TEXT NOT NULL, \n"
                + AUTOR + " TEXT NOT NULL, \n"
                + AREA + " TEXT NOT NULL, \n"
                + EDITORIAL + " TEXT NOT NULL, \n"
                + AÑO + " TEXT NOT NULL);" ;

    }
}
