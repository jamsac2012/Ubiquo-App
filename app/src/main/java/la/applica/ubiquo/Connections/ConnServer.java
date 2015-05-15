package la.applica.ubiquo.Connections;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by adrianayala on 15/05/15.
 */
public class ConnServer extends Activity {

    private String TAG;
    Context context = getApplicationContext();

    /**
     * Sends the registration ID to your server over HTTP, so it can use GCM/HTTP or CCS to send
     * messages to your app. Not needed for this demo since the device sends upstream messages
     * to a server that echoes back the message using the 'from' address in the message.
     */
    public void sendRegistrationIdToBackend(String regID) {
        HttpURLConnection httpConnection = null;
        OutputStream oStream = null;
        BufferedReader bufferedReader = null;
        StringBuilder response = null;
        String URL_AUTHENTICATION = "http://192.168.1.2:8081/0002";

        try {

            JSONObject dato = new JSONObject();
            dato.put("usuario", "Marcos");
            dato.put("pass", "1234");
            dato.put("regId", regID);


            URL url = new URL(URL_AUTHENTICATION);
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("POST");
            httpConnection.setDoOutput(true);
            httpConnection.setDoInput(true);
            httpConnection.setRequestProperty("Content-Type", "application/json");
            httpConnection.connect();

            OutputStreamWriter stream = new OutputStreamWriter(httpConnection.getOutputStream());
            stream.write(dato.toString());
            stream.flush();
            stream.close();
            Log.d(TAG, "ENTREGA DE DATOS JSON");


            Log.d(TAG, "POST response code: " + String.valueOf(httpConnection.getResponseCode()));
            //Log.d(TAG, "JSON response: " + response.toString());


        } catch (Exception e) {
            Log.e(TAG, "POST error: " + Log.getStackTraceString(e));

        }finally{
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }

    }

}
