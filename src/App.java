import java.io.*;
import java.util.*;

public class App {
    /*
    * Antes de comenzar el juego introducir nombre de usuario y mirar si coincide en Puntuaciones.txt
    * posible opcion de menu: como jugar
    *
    * Ejemplo de colores
    * public static final String GREEN = "\u001B[32m";
    * public static final String YELLOW = "\u001B[33m";
    * public static final String RESET_COLOR = "\u001B[0m";
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
//
                   break;
               case 2:
//                  Llamada a la función para iniciar un juego nuevo.
                   Mecanica.iniciarJuego();

                   break;
               case 3:
//                  Llamada para continuar un juego guardado
//                  cargarJuego();

                   break;
               case 4:
//                  Llamada a listado de puntuaciones
                   System.out.println("Esta es la lista de puntuaciones.");
                   System.out.println(Mecanica.puntuaciones.values());

                   break;
               case 5:
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
