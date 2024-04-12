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
    static public void menu (){
        System.out.println("""
                1.- Nuevo Juego
                2.- Cargar Juego
                3.- Consultar puntuaciones
                4.- Salir
                
                Elija una opción:
                """);
    }
    static public void menuTurno (){
        System.out.println("""
                1.- Continuar
                2.- Guardar juego
                Introduce 1 o 2:
                """);
    }

    static public void iniciarJuego(ArrayList<String> listaNomOcultos, int contaPalabra,Scanner lector) {
//  Generar la palabra oculta
    String palabraOculta = buscarPalabra(listaNomOcultos);
//  Variables
        ArrayList<String> listaIntentos = new ArrayList<>(); //declarar de manera global
        ArrayList<String> listaIntentosPalabras = new ArrayList<>();  //declarar de manera global
        int contadorIntentos = 6,puntuacion=0;

//  Comienzo de juego
        System.out.println("Vamos a comenzar el juego. Escribe una palabra.");

        while (contadorIntentos > 0){
            System.out.println("Introduce una palabra de 5 letras");
            String palabraIntento = lector.next();
            HashMap charpalabrabuscada = new HashMap();
            HashMap charpalabraOculta = new HashMap();
            for (int i = 0; i < 5; i++) {
                charpalabrabuscada.put(i,palabraIntento.charAt(i));
                charpalabraOculta.put(i,palabraOculta.charAt(i));
            }

            if(charpalabrabuscada.get(4).equals(charpalabraOculta.get(4))){

            }

            System.out.println(palabraIntento);
            System.out.println(palabraOculta);

            if (palabraIntento.length() == 5){
                listaIntentosPalabras.add(palabraIntento);

                if (palabraIntento.equalsIgnoreCase(palabraOculta)){
                    System.out.println("ENHORABUENA! PALABRA ACERTADA");
                    puntuacion=puntuacion+(contadorIntentos*100);
                    System.out.println("Has obtenido un total de "+puntuacion+" puntos");
                    contadorIntentos=6; // Reiniciamos el contador de los intentos
                    listaIntentosPalabras.clear(); // Vaciar la ArrayList de las palabras intentadas
                    palabraOculta= buscarPalabra(listaNomOcultos);
                }else {
                    contadorIntentos--;
                    System.out.println("Te quedan "+contadorIntentos+" intentos");
                }

            }else {
                System.out.println("La palabra introducida no tiene 5 letras.");
            }

            menuTurno();

            int opt = 0;

            try {
                opt = lector.nextInt();
            }catch (InputMismatchException e){
                e.getMessage();
            }catch (Exception e){
                e.getMessage();
            }

            switch (opt){
                case 1:
                    System.out.println("Continuamos.....");
                    break;
                case 2:
                    System.out.println("Vamos a guardar la partida");
                    System.out.println("Introduce el nombre para guardar la partida:");
                    lector.nextLine(); //Vaciamos el buffer del lector
                    String nombreJuegoGuardado = lector.nextLine();
                    System.out.println(nombreJuegoGuardado);
                    JuegoGuardado guardado = new JuegoGuardado(nombreJuegoGuardado,palabraOculta,contadorIntentos,listaIntentosPalabras,puntuacion);
//                  Implementar el guardado del juego
//
//
                    break;
                default:
                    System.out.println("Introduce una opcion valida 1 - 2");
            }
        }
    }

    public static void main(String[] args) {

        Scanner lector = new Scanner(System.in);
        Mecanica me = new Mecanica();
        me.



//        Variables
        int opt = 0,contaPalabra = 0;
        boolean salirjuego = false;



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
                   System.out.println(puntuaciones.get("Daniel"));

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
