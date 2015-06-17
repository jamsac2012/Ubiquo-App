package la.applica.ubiquo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Timer;
import java.util.TimerTask;


public class Splash_Screen extends Activity {

    private int splashDelay= 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        if (MainActivity.sw==0){
            Intent i= new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }else {

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    pasar();
                }
            };

            Timer timer = new Timer();
            timer.schedule(task, splashDelay);//Pasado los 5 segundos dispara la tarea
        }
    }

    private void pasar(){
        Intent splasInt= new Intent(this,Login.class);
        startActivity(splasInt);
        finish();
    }

}


