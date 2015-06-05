package la.applica.ubiquo.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by adrianayala on 18/05/15.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "ubiquo_app.sqlite";
    private static final int VERSION = 1;


    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {db.execSQL(DBManagerMensajes.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //if(oldVersion == 1 && newVersion == 2){
          //  db.execSQL(DBManagerMensajes.SQLUpdateV2);
        //}
        //if(oldVersion >= 1 && newVersion >= 2){
          //  db.execSQL(DBManagerMensajes.SQLUpdateV3);
        //}
    }
}
