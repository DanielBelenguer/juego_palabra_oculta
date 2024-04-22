import java.io.Serializable;
import java.util.ArrayList;

// Tenemos que ver como se hace para guardar objetos de esta clase dentro de un archivo Binario
public class JuegoGuardado implements Serializable {
    private String nombreJuegoGuardado;
    private String palabraSecreta;
    private int intentos, puntuacion;
    private ArrayList<String> palabrasIntentadas;


    JuegoGuardado(String nombreJuegoGuardado,String palabraSecreta, int intentos,ArrayList palabrasIntentadas,int puntuacion) {
        this.nombreJuegoGuardado=nombreJuegoGuardado;
        this.palabraSecreta = palabraSecreta;
        this.intentos = intentos;
        this.palabrasIntentadas = palabrasIntentadas;
        this.puntuacion=puntuacion;
    }

    public String getNombreJuegoGuardado() {
        return nombreJuegoGuardado;
    }

    public String getPalabraSecreta() {
        return palabraSecreta;
    }

    public int getIntentos() {
        return intentos;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public ArrayList<String> getpalabrasIntentadas() {
        return palabrasIntentadas;
    }
}
