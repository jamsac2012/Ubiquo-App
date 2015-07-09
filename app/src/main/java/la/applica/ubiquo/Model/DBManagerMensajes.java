package la.applica.ubiquo.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by adrianayala on 18/05/15.
 */
public class DBManagerMensajes {

    public static final String TABLE_NAME = "tabla_msg";

    public static final String CN_ID = "_id";
    public static final String CN_TITULO = "titulo";
    public static final String CN_BODY = "cuerpo";
    public static final String CN_SENDER = "remite";
    public static final String CN_AVATAR_URL = "avatar";
    public static final String CN_DATE = "fecha";
    public static final String CN_URL = "url";
    public static final String CN_MSG_ID = "msgId";
    public static final String CN_MSG_STATE = "msgState";
    //public static final String SQLUpdateV2 = "ALTER TABLE "+TABLE_NAME+" ADD COLUMN "+CN_AVATAR_URL+" TEXT, ADD COLUMN " + CN_MSG_STATE + " INT;";


    public static final String CREATE_TABLE = "create table " + TABLE_NAME + " ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_TITULO + " text not null,"
            + CN_BODY + " text not null,"
            + CN_SENDER + " text not null,"
            + CN_AVATAR_URL + " text not null,"
            + CN_DATE + " text not null,"
            + CN_URL + " text not null,"
            + CN_MSG_ID + " text not null,"
            + CN_MSG_STATE + " text not null);";


    private DBHelper helper;
    private SQLiteDatabase db;

    private String[] registros = new String[]{CN_ID,
                                            CN_TITULO,
                                            CN_BODY,
                                            CN_SENDER,
                                            CN_AVATAR_URL,
                                            CN_DATE,
                                            CN_URL,
                                            CN_MSG_ID,
                                            CN_MSG_STATE};

    public DBManagerMensajes(Context context) {

        helper = new DBHelper(context);
        db = helper.getWritableDatabase();

    }


    //Generar Valores para la Tabla
    private ContentValues genValores(String titulo,
                                     String mensaje,
                                     String sender,
                                     String avatar,
                                     String date,
                                     String url,
                                     String msgId,
                                     String state) {
        ContentValues valores = new ContentValues();
        valores.put(CN_TITULO, titulo);
        valores.put(CN_BODY, mensaje);
        valores.put(CN_SENDER, sender);
        valores.put(CN_AVATAR_URL, avatar);
        valores.put(CN_DATE, date);
        valores.put(CN_URL, url);
        valores.put(CN_MSG_ID, msgId);
        valores.put(CN_MSG_STATE, state);
        return valores;

    }

    //Funcion Insertar
    public void insertar(String titulo,
                         String mensaje,
                         String sender,
                         String avatar,
                         String date,
                         String url,
                         String msgId,
                         String state) {
        //db.insert( TABLA, NullColumnHack, ContentValues)
        db.insert(TABLE_NAME, null, genValores(titulo, mensaje, sender, avatar, date, url, msgId, state));
        db.close();
    }

    //Funcion Eliminacion
    public void eliminar(String titulo) {
        //db.delete(TABLA, Clausula Where, Argumento Where)
        db.delete(TABLE_NAME, CN_TITULO + "=?", new String[]{titulo});
        db.close();

    }

    //Cargar Cursor con datos
    public Cursor cargarCursor() {
        //db.query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
        return db.query(TABLE_NAME, registros, null, null, null, null, CN_ID + " DESC");
    }

    //Funcion Buscar
    public Cursor buscarMsg(String titulo) {

        return db.query(TABLE_NAME, registros, CN_TITULO + "=?", new String[]{titulo}, null, null, null, null);

    }

}
