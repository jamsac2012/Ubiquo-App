package la.applica.ubiquo.Gcm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import la.applica.ubiquo.MainActivity;
import la.applica.ubiquo.Model.DBManagerMensajes;
import la.applica.ubiquo.R;

/**
 * Created by adrianayala on 1/05/15.
 */
public class GcmIntentService extends IntentService {

    //Instancias de variables y objetos necesarios para la manipulacion de la NOTIFICACION
    private NotificationManager mNotificationManager;

    public GcmIntentService() {
        super("GcmIntentService");
    }

    public static final String TAG = "GCM Demo";
    private int notificationId;
    long[] pattern = new long[]{1000, 500, 1000}; // Patron de intermitencia del led
    private Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    private int numNotificaciones = 0;
    final static String GROUP_KEY = "ubiquo-group";

    // Instancia de objetos para guardar los mensajes en la BD SqLite
    private DBManagerMensajes manager;

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        String titulo = extras.getString("titulo");
        String cuerpo = extras.getString("mensaje");
        String remite = extras.getString("sender");
        String avatar = extras.getString("avatar");
        String fecha = extras.getString("date");
        String url = extras.getString("url");
        String msgId = extras.getString("msgId");
        String estado = extras.getString("msgState");


        notificationId = Integer.parseInt(msgId);
        Log.d(TAG, extras.toString());

        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        String messageType = gcm.getMessageType(intent);

        if (!extras.isEmpty()) {  // has effect of unparcelling Bundle
            /*
             * Filter messages based on message type. Since it is likely that GCM will be
             * extended in the future with new message types, just ignore any message types you're
             * not interested in, or that you don't recognize.
             */
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                insertarDB(titulo, cuerpo, remite, avatar, fecha, url, msgId, estado);
                sendNotification(titulo, cuerpo);
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                insertarDB(titulo, cuerpo, remite, avatar, fecha, url, msgId, estado);
                sendNotification("Deleted messages on server: " + titulo, cuerpo);
                // If it's a regular GCM message, do some work.
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                // This loop represents the service doing some work.
                Log.i(TAG, "Working... ");

                Log.i(TAG, "Completed work @ " + SystemClock.elapsedRealtime());

                insertarDB(titulo, cuerpo, remite, avatar, fecha, url, msgId, estado);
                // Post notification of received message.
                sendNotification(titulo, cuerpo);
                Log.i(TAG, "Received: " + extras.toString());
                // Metodo para insertar los datos en la DBSqlite

            }
        }
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    // Put the message into a notification and post it.
    // This is just one simple example of what you might choose to do with
    // a GCM message.
    private void sendNotification(String title, String body) {

        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.icon_36_ldpi)
                        .setLargeIcon((((BitmapDrawable) getResources()
                                .getDrawable(R.mipmap.icon_36_ldpi)).getBitmap()))
                        .setContentTitle(title)
                        .setContentText(body)
                        .setTicker("Mensaje Ubiquo")
                        .setStyle(new NotificationCompat.InboxStyle()
                                .addLine(title + ": " + body)
                                .setBigContentTitle("Notificaciones Ubiquo")
                                .setSummaryText(numNotificaciones + " Notificaciones"))
                        .setGroup(GROUP_KEY)
                        .setGroupSummary(true)
                        .setVibrate(pattern)
                        .setSound(alarmSound)
                        .setNumber(numNotificaciones++)
                        .setLights(Color.GRAY, 1, 500)
                        .setContentIntent(contentIntent)
                        .setAutoCancel(true);

        mNotificationManager.notify(notificationId, mBuilder.build());
        numNotificaciones++;
    }

    private void insertarDB(String titulo,
                            String body,
                            String sender,
                            String avatar,
                            String date,
                            String url,
                            String msgId,
                            String state) {

        manager = new DBManagerMensajes(this);

        manager.insertar(titulo, body, sender, avatar, date, url, msgId, state);
        Log.d(TAG, "REGISTRO EXITOSO EN LA DB");
    }
}