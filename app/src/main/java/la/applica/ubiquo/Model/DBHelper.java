package la.applica.ubiquo.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by adrianayala on 18/05/15.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "salome";
    private static final int VERSION = 1;


    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {db.execSQL(DBManagerMensajes.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
