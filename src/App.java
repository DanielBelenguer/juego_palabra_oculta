import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class App {
    /*
    * posible opcion de menu: como jugar
    *
    * */
    static public void menu (){
        System.out.println("""
                
                1.- Nuevo Juego
                2.- Cargar Juego
                3.- Consultar puntuaciones
                4.- Salir
                
                Elija una opción:
                 
                """);
    }
    static public String buscarPalabra (ArrayList<String> listaNomOcultos) {
        Random random = new Random();
        int num = random.nextInt(listaNomOcultos.size());
        String palabraOculta = listaNomOcultos.get(num);
        System.out.println(palabraOculta);
        return palabraOculta;
    }
    static public void iniciarJuego(ArrayList<String> listaNomOcultos, int contaPalabra,Scanner lector) {
//  Generar la palabra oculta
    String palabraOculta = buscarPalabra(listaNomOcultos);
//    Variables
        ArrayList<String> listaIntentos = new ArrayList<>();
        int contadorIntentos = 6,puntuacion=0;
//  Comienzo de juego
        System.out.println("Vamos a comenzar el juego. Escribe una palabra.");
        while (contadorIntentos > 0){
            System.out.println("Introduce una palabra de 5 letras");
                String palabraIntento = lector.next();
            System.out.println(palabraIntento);
            System.out.println(palabraOculta);
            if (palabraIntento.length() == 5){
                if (palabraIntento.equalsIgnoreCase(palabraOculta)){
                    System.out.println("ENHORABUENA! PALABRA ACERTADA");
                    puntuacion=puntuacion+(contadorIntentos*100);
                    System.out.println("Has obtenido un total de "+puntuacion+" puntos");
                    contadorIntentos=6;
                    palabraOculta= buscarPalabra(listaNomOcultos);
                }else {
                    contadorIntentos--;
                    System.out.println("Te quedan "+contadorIntentos+" intentos");
                }
            }else {
                System.out.println("La palabra introducida no tiene 5 letras.");
            }
        }
    }
    public static void main(String[] args) {
        FileReader fr = null;
        BufferedReader br = null;
        Scanner lector = new Scanner(System.in);
        Scanner lectorpalabras = null;
        ArrayList<String> listaNomOcultos = null;

//        Variables
        int opt = 0,contaPalabra = 0;
        boolean salirjuego = false;

//      Cargar palabras en RAM
        try {
            fr = new FileReader("/home/daniel/Documentos/IdeaProjects/juego_palabra_oculta/src/Palabras5L.txt");
            br = new BufferedReader(fr);
            listaNomOcultos = new ArrayList<>();

            String linea = null,palabra = null;

            while ((linea = br.readLine()) != null){
                lectorpalabras = new Scanner(linea);
                lectorpalabras.useDelimiter(",");
                while (lectorpalabras.hasNext()){
                    palabra = lectorpalabras.next();
                    listaNomOcultos.add(palabra);
                    contaPalabra++;
                }
            }
        }catch (IOException e){
            e.getMessage();
        }catch (Exception e) {
            e.getMessage();
        }finally {
            try{
                br.close();
                fr.close();
                lectorpalabras.close();
            }catch (IOException e){
                e.getMessage();
            }
        }

//        Menu del juego
       while (!salirjuego){
           menu();
           try {
               opt = lector.nextInt();
           }catch (InputMismatchException e){
               e.getMessage();
           }catch (Exception e){
               e.getMessage();
           }

           switch (opt){
               case 1:
//              Llamada a la función para iniciar un juego nuevo.
                   iniciarJuego(listaNomOcultos,contaPalabra,lector);
                   break;
               case 2:
//              Llamada para continuar un juego guardado
//                cargarJuego();
                   break;
               case 3:
//              Llamada a listado de puntuaciones
//                listarPuntuaciones();
                   break;
               case 4:
//              Salir del menu
                   System.out.println("Saliendo del juego");
                   salirjuego = true;
                   break;
               default:
                   System.out.println("elija una opción válida");
           }
       }

    }
}
