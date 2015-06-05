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
    public static final String CN_SENDER = "remite";
    public static final String CN_DATE = "fecha";
    public static final String CN_URL = "url";
    //public static final String SQLUpdateV2 = "ALTER TABLE "+TABLE_NAME+" ADD COLUMN "+CN_DATE+" TEXT;";
    //public static final String SQLUpdateV3 = "ALTER TABLE "+TABLE_NAME+" ADD COLUMN "+CN_URL+" TEXT;";


    public static final String CREATE_TABLE = "create table " +TABLE_NAME+ " ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_TITULO + " text not null,"
            + CN_MSG + " text not null,"
            + CN_SENDER + " text not null,"
            + CN_DATE + " text not null,"
            + CN_URL + " text not null);";



    private DBHelper helper;
    private SQLiteDatabase db;

    private String[] registros= new String[]{CN_ID,CN_TITULO,CN_MSG,CN_SENDER,CN_DATE,CN_URL};

    public DBManagerMensajes(Context context) {

        helper= new DBHelper(context);
        db = helper.getWritableDatabase();

    }



    //Generar Valores para la Tabla
    private ContentValues genValores(String titulo,String mensaje, String sender, String date, String url){
        ContentValues valores = new ContentValues();
        valores.put(CN_TITULO, titulo);
        valores.put(CN_MSG, mensaje);
        valores.put(CN_SENDER, sender);
        valores.put(CN_DATE, date);
        valores.put(CN_URL, url);
        return valores;

    }

    //Funcion Insertar
    public void insertar (String titulo,String mensaje, String sender, String date, String url){
        //db.insert( TABLA, NullColumnHack, ContentValues)
        db.insert(TABLE_NAME, null, genValores(titulo, mensaje, sender, date, url));
        db.close();
    }

    //Funcion Eliminacion
    public void eliminar (String titulo){
        //db.delete(TABLA, Clausula Where, Argumento Where)
        db.delete(TABLE_NAME,CN_TITULO+"=?", new String[]{titulo});
        db.close();

    }

    //Cargar Cursor con datos
    public Cursor cargarCursor(){
        //db.query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
        return db.query(TABLE_NAME, registros, null, null, null, null, CN_ID+" DESC");
    }

    //Funcion Buscar
    public Cursor buscarMsg(String titulo){

        return db.query(TABLE_NAME, registros, CN_TITULO + "=?", new String[]{titulo}, null, null, null, null);

    }

}
