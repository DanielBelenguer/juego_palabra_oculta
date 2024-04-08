import java.util.ArrayList;

// Tenemos que ver como se hace para guardar objetos de esta clase dentro de un archivo Binario
public class JuegoGuardado {
    private String palabraSecreta;
    private int vidasNoGastadas;
    private ArrayList<String> listaIntentos = new ArrayList<>();

    JuegoGuardado(String palabraSecreta, int vidasNoGastadas,ArrayList listaIntentosPalabras) {
        this.palabraSecreta = palabraSecreta;
        this.vidasNoGastadas = vidasNoGastadas;
        listaIntentos = listaIntentosPalabras;
    }

}
