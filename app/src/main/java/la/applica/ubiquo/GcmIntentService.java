package la.applica.ubiquo;

import android.app.IntentService;
import android.app.Notification;
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

import org.json.JSONObject;

/**
 * Created by adrianayala on 1/05/15.
 */
public class GcmIntentService extends IntentService {

    //public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;
    public GcmIntentService() {
        super("GcmIntentService");
    }
    public static final String TAG = "GCM Demo";
    private int notificationId;
    long[] pattern = new long[]{1000,500,1000};
    Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);




    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        String titulo = extras.getString("titulo");
        String cuerpo = extras.getString("mensaje");
        int msgID= Integer.parseInt(extras.getString("msgId"));
        notificationId = msgID;
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
                sendNotification(titulo, cuerpo);
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                sendNotification("Deleted messages on server: " + titulo, cuerpo);
                // If it's a regular GCM message, do some work.
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                // This loop represents the service doing some work.
                Log.i(TAG, "Working... ");

                Log.i(TAG, "Completed work @ " + SystemClock.elapsedRealtime());

                // Post notification of received message.
                sendNotification(titulo,cuerpo);
                Log.i(TAG, "Received: " + extras.toString());
            }
        }
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    // Put the message into a notification and post it.
    // This is just one simple example of what you might choose to do with
    // a GCM message.
    private void sendNotification(String title, String body) {
            int numMessages = 0;
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.icon_36_ldpi)
                        .setLargeIcon((((BitmapDrawable)getResources()
                                .getDrawable(R.mipmap.icon_36_ldpi)).getBitmap()))
                        .setContentTitle(title)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
                        .setContentText(body)
                        .setTicker("Mensaje Ubiquo!!!")
                        .setVibrate(pattern)
                        .setSound(alarmSound)
                        .setNumber(++numMessages)
                        .setLights(Color.GRAY, 1, 500)
                        .setContentIntent(contentIntent)
                        .setAutoCancel(true);

        mNotificationManager.notify(notificationId, mBuilder.build());
    }
}