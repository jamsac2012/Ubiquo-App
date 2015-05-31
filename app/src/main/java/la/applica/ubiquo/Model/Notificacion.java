package la.applica.ubiquo.Model;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by adrianayala on 21/05/15.
 */
public class Notificacion{


    String titulo;
    String cuerpo;

    public Notificacion(String titulo, String cuerpo) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;

    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }


}
