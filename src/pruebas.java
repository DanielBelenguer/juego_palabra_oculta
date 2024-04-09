import java.util.ArrayList;
import java.util.Random;

public class pruebas {
    static public String buscarPalabra (ArrayList<String> listaNomOcultos, int contaPalabra) {
        Random random = new Random();
        int num = random.nextInt(contaPalabra);
        String palabraOculta = listaNomOcultos.get(num);
        return palabraOculta;
    }
    public static void main(String[] args) {
     ArrayList<String> listaNomOcultos = new ArrayList<>();
     int contador = 0;
     listaNomOcultos.add("Hola");
     contador++;
     listaNomOcultos.add("como");
     contador++;
     listaNomOcultos.add("si");
     contador++;
     System.out.println(buscarPalabra(listaNomOcultos,contador));

    }
}
