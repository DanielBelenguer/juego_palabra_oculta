import java.io.*;
import java.util.*;

public class App {
    /*
    *
    *
    * */

    public static void main(String[] args) {
//        Cargamos las palabras y las puntuaciones en el juego
        Mecanica.cargarPalabras();
        Mecanica.cargarPuntuacion();

//        Variables
        int opt = 0;
        boolean salirjuego = false;

//        Menu del juego
       while (!salirjuego){
           Mecanica.menu();
           try {
               opt = Mecanica.lector.nextInt();
           }catch (InputMismatchException e){
               e.getMessage();
           }catch (Exception e){
               e.getMessage();
           }
           switch (opt){
               case 1:
//                  Llamada a como se juega
                   Mecanica.comoSeJuega();
                   break;
               case 2:
//                  Llamada a la función para iniciar un juego nuevo.
                   Mecanica.iniciarJuego();
                   break;
               case 3:
//                  Llamada para continuar un juego guardado
                   System.out.println("¿Que juego quieres cargar?");
                   String nombrejuego = Mecanica.lector.next();
                   Mecanica.cargarJuego(nombrejuego);
                   break;
               case 4:
//                  Llamada al listado de puntuaciones
                   System.out.println("Esta es la lista de puntuaciones.");
                   System.out.println(Mecanica.puntuaciones.entrySet());
                   break;
               case 5:
//                 Salir del menu
                   System.out.println("Saliendo del juego");
                   salirjuego = true;
                   break;
               default:
                   System.out.println("elija una opción válida");
           }
       }
       Mecanica.guardarPuntuaciones();
    }
}
