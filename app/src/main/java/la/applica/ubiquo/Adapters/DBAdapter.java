package la.applica.ubiquo.Adapters;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import la.applica.ubiquo.Model.DBManagerMensajes;
import la.applica.ubiquo.Model.Notificacion;

/**
 * Created by adrianayala on 21/05/15.
 */
public class DBAdapter {
    public Notificacion getData(String title){

        DBManagerMensajes manager = new DBManagerMensajes();
        Cursor cursor = manager.cargarCursor();


        Notificacion notif = null;
        if (cursor.moveToFirst()) {
            int index1 = cursor.getColumnIndex(manager.CN_ID);
            int index2 = cursor.getColumnIndex(manager.CN_TITULO);
            int index3 = cursor.getColumnIndex(manager.CN_MSG);
            int id = cursor.getInt(index1);
            String titulo = cursor.getString(index2);
            String cuerpo = cursor.getString(index3);
            notif = new Notificacion(id, titulo, cuerpo);
        }
        return notif;
    }

    public List<Notificacion> getAllData() {
        List<Notificacion> list = new ArrayList<>();

        DBManagerMensajes manager = new DBManagerMensajes();
        Cursor cursor = manager.cargarCursor();

        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(manager.CN_ID);
            int index2 = cursor.getColumnIndex(manager.CN_TITULO);
            int index3 = cursor.getColumnIndex(manager.CN_MSG);
            int cid = cursor.getInt(index1);
            String titulo = cursor.getString(index2);
            String cuerpo = cursor.getString(index3);
            Notificacion notif = new Notificacion(cid, titulo, cuerpo);
            list.add(notif);
        }
        return list;
    }
}
