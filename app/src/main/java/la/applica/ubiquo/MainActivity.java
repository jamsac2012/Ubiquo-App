package la.applica.ubiquo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import java.io.IOException;
import la.applica.ubiquo.Connections.ConnServer;
import la.applica.ubiquo.Gcm.ConnectionGcm;


public class MainActivity extends AppCompatActivity{


    public static final String PROPERTY_REG_ID = "registration_id";
    public static final String PROPERTY_APP_VERSION = "appVersion";
    public static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;


    /**
     * Tag used on log messages.
     */
    static final String TAG = "GCM main";

    public TextView mDisplay;
    ConnectionGcm connGcm;
    Context context;
    GoogleCloudMessaging gcm;
    String regid;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        toolbar.setTitle("Ubiquo App");
        setSupportActionBar(toolbar);

        mDisplay = (TextView) findViewById(R.id.display);
        context = getApplicationContext();

        // Check device for Play Services APK. If check succeeds, proceed with GCM registration.
        if (connGcm.checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(this);
            regid = connGcm.getRegistrationId(context);
            mDisplay.setText(regid);

            if (regid.isEmpty()) {
                connGcm.registerInBackground();
            }
        } else {
            Log.i(TAG, "No valid Google Play Services APK found.");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Check device for Play Services APK.
        connGcm.checkPlayServices();
    }



    /**
     * Stores the registration ID and the app versionCode in the application's
     * {@code SharedPreferences}.
     *  @param context application's context.
     * @param regId registration ID
     */
    public void storeRegistrationId(Context context, String regId) {
        final SharedPreferences prefs = connGcm.getGcmPreferences(context);
        int appVersion = connGcm.getAppVersion(context);
        Log.i(TAG, "Saving regId on app version " + appVersion);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_REG_ID, regId);
        editor.putInt(PROPERTY_APP_VERSION, appVersion);
        editor.commit();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}