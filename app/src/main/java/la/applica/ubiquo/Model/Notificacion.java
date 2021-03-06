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
    String remite;
    String avatar;
    String fecha;
    String url;
    String msgId;
    String estado;

    public Notificacion(String titulo, String cuerpo, String remite, String avatar, String fecha, String url, String msgId, String estado) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.remite = remite;
        this.avatar = avatar;
        this.fecha = fecha;
        this.url = url;
        this.msgId = msgId;
        this.estado = estado;

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

    public String getRemite() {
        return remite;
    }

    public void setRemite(String remite) {
        this.remite = remite;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


}
