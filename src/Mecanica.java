import java.io.*;
import java.util.*;

public class Mecanica {
    // Color Constants
    private static final String YELLOW_COLOR = "\u001B[33m";
    private static final String GREEN_COLOR = "\u001B[32m";
    private static final String RED_COLOR = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    // Constantes Globales
    private static Scanner lectorpalabras = null;
    public static Scanner lector = new Scanner(System.in);
    public static ArrayList<String> palabrasOcultas = new ArrayList<>();
    public static ArrayList<String> listaIntentosPalabras = new ArrayList<>();
    public static HashMap puntuaciones = new HashMap<>();

    private static final int INTENTOS = 6;


    public static void cargarPuntuacion (){
        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader("/home/daniel/Documentos/IdeaProjects/juego_palabra_oculta/src/archivosNecesarios/Puntuaciones.csv");
            br = new BufferedReader(fr);
            puntuaciones = new HashMap<>();

            String linea = null,nombre = null;
            String puntuacion = null;

            while ((linea = br.readLine()) != null){
                lectorpalabras = new Scanner(linea);
                lectorpalabras.useDelimiter(",");
                while (lectorpalabras.hasNext()){
                    nombre = lectorpalabras.next();
                    puntuacion = lectorpalabras.next();
                    puntuaciones.put(nombre,nombre+" "+puntuacion);
                }
            }
        }catch (IOException e){
            e.getMessage();
        }catch (Exception e){
            e.getMessage();
        }finally {
            try {
                br.close();
                fr.close();
            }catch (IOException e){
                e.getMessage();
            }
        }
    }
    public static void guardarPuntuaciones() {
        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader("/home/daniel/Documentos/IdeaProjects/juego_palabra_oculta/src/archivosNecesarios/Puntuaciones.csv");
            br = new BufferedReader(fr);
        }catch (IOException e) {
            e.getMessage();
        }finally {
            try {
                br.close();
                fr.close();
            }catch (IOException e) {
                e.getMessage();
            }
        }






    }
    public static void cargarPalabras(){
        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader("/home/daniel/Documentos/IdeaProjects/juego_palabra_oculta/src/archivosNecesarios/Palabras.txt");
            br = new BufferedReader(fr);
            palabrasOcultas = new ArrayList<>();
            String linea = null,palabra = null;
            while ((linea = br.readLine()) != null){
                lectorpalabras = new Scanner(linea);
                lectorpalabras.useDelimiter(",");
                while (lectorpalabras.hasNext()){
                    palabra = lectorpalabras.next();
                    palabrasOcultas.add(palabra);
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
    }
    public static void instructions(){
        File file = new File("/home/daniel/Documentos/IdeaProjects/juego_palabra_oculta/src/archivosNecesarios/Comosejuega.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("error");
        }
    }
    public static void guardarJuego(JuegoGuardado jue) {
        FileOutputStream fo = null;
        ObjectOutputStream os = null;

        try {
            fo = new FileOutputStream("/home/daniel/Documentos/IdeaProjects/juego_palabra_oculta/src/partidasGuardadas");
            os = new ObjectOutputStream(fo);

            // Creamos el Objeto para guardarlo

            os.writeObject(jue);

        }catch (IOException e){
            e.getMessage();
        }finally {
            try{
                os.close();
                fo.close();
            }catch (IOException e){
                e.getMessage();
            }
        }
    }
    public static void cargarJuego(String nombreJuego) {
        FileInputStream fi = null;
        ObjectInputStream is = null;

        try {
            fi = new FileInputStream("/home/daniel/Documentos/IdeaProjects/juego_palabra_oculta/src/partidasGuardadas" + nombreJuego + ".raton");
            is = new ObjectInputStream(fi);

            JuegoGuardado juegoGuardado = (JuegoGuardado) is.readObject();
        }catch (IOException e) {
            e.getMessage();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            try {
                is.close();
                fi.close();
            }catch (IOException e){
                e.getMessage();
            }
        }
    }
    static public void menu (){
        System.out.println("""
                1.- ¿Como Se juega?
                2.- Nuevo Juego
                3.- Cargar Juego
                4.- Consultar puntuaciones
                5.- Salir
                
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
    static public String buscarPalabra () {
        Random random = new Random();
        int num = random.nextInt(palabrasOcultas.size());
        String palabraOculta = palabrasOcultas.get(num);
        System.out.println(palabraOculta); // Esto solo es para la pruebas, no debe mostrarse.
        return palabraOculta;
    }
    static public void iniciarJuego() {
//      Declaracion de variables Internas de IniciarJuego
        String palabraOculta = Mecanica.buscarPalabra();
        int contadorIntentos = 0,puntuacion=0;

//  Comienzo de juego
        System.out.println("Vamos a comenzar el juego. Escribe una palabra.");

        while (contadorIntentos < INTENTOS ){
            System.out.println("Introduce una palabra de 5 letras");
            String palabraIntento = lector.next();
            HashMap charpalabrabuscada = new HashMap();
            HashMap charpalabraOculta = new HashMap();

            for (int i = 0; i < 5; i++) {
                charpalabrabuscada.put(i,palabraIntento.charAt(i));
                charpalabraOculta.put(i,palabraOculta.charAt(i));
            }

            // Comprobar que la palabra introducida tiene 5 letras
            if (palabraIntento.length() == 5){
                // Comprobar que existe la palabra
                if (listaIntentosPalabras.contains(palabraIntento)) {
                    listaIntentosPalabras.add(palabraIntento);

                    if (palabraIntento.equalsIgnoreCase(palabraOculta)){
                        System.out.println("ENHORABUENA! PALABRA ACERTADA");
                        puntuacion=puntuacion+(contadorIntentos*100);
                        System.out.println("Has obtenido un total de "+puntuacion+" puntos");
                        contadorIntentos=0; // Reiniciamos el contador de los intentos
                        listaIntentosPalabras.clear(); // Vaciar la ArrayList de las palabras intentadas
                        palabraOculta = Mecanica.buscarPalabra();
                    }else {
                        contadorIntentos--;
                        System.out.println("Te quedan "+contadorIntentos+" intentos");

                        // Comprobar letra por letra

//                        if (charpalabrabuscada.get(0).equals(charpalabraOculta.get(0))){
//                            charpalabrabuscada.get(0);
//                        }




                    }

                }else {
//                  Como la palabra no existe en el archivo..... vamos por aquí
                    System.out.println("La palabra introducida no existe en la base de datos......");
                }
            }else {
                // Como la palabra no tiene 5 letras, pues damos este mensaje.
                System.out.println("La palabra introducida no es de 5 letras.... Introduce otra!!");
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
                    JuegoGuardado jue = new JuegoGuardado(nombreJuegoGuardado,palabraOculta,contadorIntentos,listaIntentosPalabras,puntuacion);
                    Mecanica.guardarJuego(jue);
                    break;
                default:
                    System.out.println("Introduce una opcion valida 1 - 2");
            }
        }
    }



}
