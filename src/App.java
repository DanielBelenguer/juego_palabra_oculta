import java.io.*;
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
    static public void menuTurno (){
        System.out.println("""
                1.- Continuar
                2.- Guardar juego
                Introduce 1 o 2:
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
//  Variables
        ArrayList<String> listaIntentos = new ArrayList<>();
        ArrayList<String> listaIntentosPalabras = new ArrayList<>();
        int contadorIntentos = 6,puntuacion=0;

//  Comienzo de juego
        System.out.println("Vamos a comenzar el juego. Escribe una palabra.");

        while (contadorIntentos > 0){
            System.out.println("Introduce una palabra de 5 letras");
            String palabraIntento = lector.next();
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
                    lector.nextLine();
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
    public static enum Colores {
        VERDER,AMARILLO
    }
    public static void main(String[] args) {
        FileReader fr = null;
        FileReader fr2 = null;
        FileWriter fw = null;
        BufferedReader br = null;
        BufferedReader br2 = null;
        FileWriter bw = null;
        Scanner lector = new Scanner(System.in);
        Scanner lectorpalabras = null;
        ArrayList<String> listaNomOcultos = null;
        HashMap puntuaciones = null;

//        Variables
        int opt = 0,contaPalabra = 0;
        boolean salirjuego = false;

//      Cargar puntuaciones en HasMap
        try {
            fr2 = new FileReader("C:\\Users\\danie\\Documents\\DAW\\java\\juego_palabra_oculta\\src\\Puntuaciones.csv");
            br2 = new BufferedReader(fr2);
            puntuaciones = new HashMap<>();

            String linea = null,nombre = null;
            int puntuacion = 0;

            while ((linea = br2.readLine()) != null){
                lectorpalabras = new Scanner(linea);
                lectorpalabras.useDelimiter(",");
                while (lectorpalabras.hasNext()){
                    nombre = lectorpalabras.next();
                    puntuacion = lectorpalabras.nextInt();
                    puntuaciones.put(nombre,puntuacion);
                }
            }
        }catch (IOException e){
            e.getMessage();
        }catch (Exception e){
            e.getMessage();
        }finally {
            try {
                br2.close();
                fr2.close();
            }catch (IOException e){
                e.getMessage();
            }
        }
//      Cargar palabras en ArrayList
        try {
            fr = new FileReader("C:\\Users\\danie\\Documents\\DAW\\java\\juego_palabra_oculta\\src\\Palabras5L.txt");
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
