package la.applica.ubiquo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Msg_Activity extends AppCompatActivity {

    public TextView titulo, cuerpo, remitente, fecha, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        toolbar.setTitle("Notificación");
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();

        titulo = (TextView)findViewById(R.id.tv_msg_titulo);
        cuerpo = (TextView) findViewById(R.id.tv_msg_cuerpo);
        remitente = (TextView) findViewById(R.id.tv_msg_remitente);
        fecha = (TextView) findViewById(R.id.tv_msg_fecha);
        url = (TextView) findViewById(R.id.tv_msg_url);

        titulo.setText(bundle.getString("titulo"));
        cuerpo.setText(bundle.getString("cuerpo"));
        remitente.setText(bundle.getString("remite"));
        fecha.setText(bundle.getString("fecha"));
        url.setText(bundle.getString("url"));
    }

    public void navegarUrl(View v){
        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("url");
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
        Log.d(MainActivity.TAG,"Navegacion");
        finish();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_msg_, menu);
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
