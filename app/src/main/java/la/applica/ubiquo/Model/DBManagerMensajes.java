package la.applica.ubiquo.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by adrianayala on 18/05/15.
 */
public class DBManagerMensajes {

    public static final String TABLE_NAME= "tabla_msg";

    public static final String CN_ID="_id";
    public static final String CN_TITULO = "titulo";
    public static final String CN_MSG = "mensaje";


    public static final String CREATE_TABLE = "create table " +TABLE_NAME+ " ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_TITULO + " text not null,"
            + CN_MSG + " text not null);";



    private DBHelper helper;
    private SQLiteDatabase db;

    private String[] registros= new String[]{CN_ID,CN_TITULO,CN_MSG};

    public DBManagerMensajes(Context context) {

        helper= new DBHelper(context);
        db = helper.getWritableDatabase();

    }

    public DBManagerMensajes() {

    }


    //Generar Valores para la Tabla
    private ContentValues genValores(String titulo,String mensaje){
        ContentValues valores = new ContentValues();
        valores.put(CN_TITULO, titulo);
        valores.put(CN_MSG, mensaje);
        return valores;

    }

    //Funcion Insertar
    public void insertar (String titulo,String mensaje){
        //db.insert( TABLA, NullColumnHack, ContentValues)
        db.insert(TABLE_NAME, null, genValores(titulo, mensaje));

    }

    //Funcion Eliminacion
    public void eliminar (String titulo){
        //db.delete(TABLA, Clausula Where, Argumento Where)
        db.delete(TABLE_NAME,CN_TITULO+"=?", new String[]{titulo});

    }

    //Cargar Cursor con datos
    public Cursor cargarCursor(){
        //db.query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
        return db.query(TABLE_NAME, registros, null, null, null, null, null, null);
    }

    //Funcion Buscar
    public Cursor buscarMsg(String titulo){

        return db.query(TABLE_NAME, registros, CN_TITULO + "=?", new String[]{titulo}, null, null, null, null);

    }

}
