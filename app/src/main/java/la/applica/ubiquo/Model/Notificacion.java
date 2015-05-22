package la.applica.ubiquo.Model;

/**
 * Created by adrianayala on 21/05/15.
 */
public class Notificacion {

    int id;
    String titulo;
    String cuerpo;

    public Notificacion(int id, String titulo, String cuerpo) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
