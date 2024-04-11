import java.util.ArrayList;

// Tenemos que ver como se hace para guardar objetos de esta clase dentro de un archivo Binario
public class JuegoGuardado {
    private String nombreJuegoGuardado;
    private String palabraSecreta;
    private int vidasNoGastadas, puntuacion;
    private ArrayList<String> listaIntentosPalabras;

    JuegoGuardado(String nombreJuegoGuardado,String palabraSecreta, int vidasNoGastadas,ArrayList listaIntentosPalabras,int puntuacion) {
        this.nombreJuegoGuardado=nombreJuegoGuardado;
        this.palabraSecreta = palabraSecreta;
        this.vidasNoGastadas = vidasNoGastadas;
        this.listaIntentosPalabras = listaIntentosPalabras;
        this.puntuacion=puntuacion;
    }

}
