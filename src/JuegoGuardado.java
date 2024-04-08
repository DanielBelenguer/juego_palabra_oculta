import java.util.ArrayList;

public class JuegoGuardado {
    private String palabraSecreta;
    private int vidasNoGastadas;
    ArrayList<String> listaIntentos = new ArrayList<>();

    JuegoGuardado(String palabraSecreta, int vidasNoGastadas) {
        this.palabraSecreta = palabraSecreta;
        this.vidasNoGastadas = vidasNoGastadas;

    }
}
