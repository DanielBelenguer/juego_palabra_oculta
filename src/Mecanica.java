import java.io.*;
import java.util.*;

public class Mecanica {
    // Color Constants
    private static final String YELLOW_COLOR = "\u001B[33m";
    private static final String GREEN_COLOR = "\u001B[32m";
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
            fr = new FileReader("C:\\Users\\danie\\Documents\\DAW\\java\\juego_palabra_oculta\\src\\archivosNecesarios\\Puntuaciones.csv");
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
            fr = new FileReader("C:\\Users\\danie\\Documents\\DAW\\java\\juego_palabra_oculta\\src\\archivosNecesarios\\Puntuaciones.csv");
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
            fr = new FileReader("C:\\Users\\danie\\Documents\\DAW\\java\\juego_palabra_oculta\\src\\archivosNecesarios\\Palabras.txt");
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
    public static void comoSeJuega(){
        FileReader fr = null;
        BufferedReader br = null;
        try{
            fr = new FileReader("C:\\Users\\danie\\Documents\\DAW\\java\\juego_palabra_oculta\\src\\archivosNecesarios\\Comosejuega.txt");
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        }catch (IOException e){
            e.getMessage();
        }catch (Exception e){
            e.getMessage();
        }finally {
            try{
                fr.close();
                br.close();
            }catch (IOException e){
                e.getMessage();
            }
        }

    }
    public static void guardarJuego(JuegoGuardado jue) {
        /*
        * Mirar si podemos implementar contraseña para el juego guardado
        * guardar passs en la clase juegoGuardado*/
        FileOutputStream fo = null;
        ObjectOutputStream os = null;

        try {
            fo = new FileOutputStream("C:\\Users\\danie\\Documents\\DAW\\java\\juego_palabra_oculta\\src\\partidasGuardadas\\"+ jue.getNombreJuegoGuardado() +".bin");
            os = new ObjectOutputStream(fo);

            os.writeObject(jue);
            System.out.println("Escritura finalizada");

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
            fi = new FileInputStream("C:\\Users\\danie\\Documents\\DAW\\java\\juego_palabra_oculta\\src\\partidasGuardadas\\" + nombreJuego + ".bin");
            is = new ObjectInputStream(fi);

            JuegoGuardado juegoGuardado= (JuegoGuardado) is.readObject();;

//            Iniciar juego cargado


            //      Declaracion de variables Internas de CargarJuedo
            String palabraOculta = juegoGuardado.getPalabraSecreta();
            int contadorIntentos = juegoGuardado.getIntentos(), puntuacion=juegoGuardado.getPuntuacion();
            ArrayList<String> palabrasIntentadas = juegoGuardado.getpalabrasIntentadas();

//  Comienzo de juego
            System.out.println("Vamos a continuar el juego.");
            System.out.println("Tienes estas palabras como intentos: ");

            while (contadorIntentos < INTENTOS ){
                System.out.println(palabrasIntentadas.toString());
                System.out.println("Introduce una palabra de 5 letras");
                String palabraIntento = lector.next();

                ArrayList<Character> charpalabrabuscada = new ArrayList<>();
                ArrayList<Character> charpalabraOculta = new ArrayList<>();

                for (int i = 0; i < 5; i++) {

//               Controlar entrada que sea 5 letras no menos-----------------------------------------------------------------------------------------------------
                    charpalabrabuscada.add(palabraIntento.charAt(i));
                    charpalabraOculta.add(palabraOculta.charAt(i));
                }

                // Comprobar que la palabra introducida tiene 5 letras
                if (palabraIntento.length() == 5){
                    // Comprobar que existe la palabra
                    if (palabrasOcultas.contains(palabraIntento)) {
                        palabrasIntentadas.add(palabraIntento);

                        for (int i = 0; i < 5; i++) {
                            if (palabraIntento.charAt(i) == palabraOculta.charAt(i)){
                                System.out.println("La letra " + palabraOculta.charAt(i) +" Esta en la posicion correcta");
                            } else if (charpalabraOculta.contains(charpalabrabuscada.get(i))) {
                                System.out.println("La letra "+ charpalabrabuscada.get(i)+" si esta en la palabra");
                            }else {
                                System.out.println("La letra "+ charpalabrabuscada.get(i)+ " No esta en la palabra");
                            }
                        }

                        if (palabraIntento.equalsIgnoreCase(palabraOculta)){
                            System.out.println("ENHORABUENA! PALABRA ACERTADA");
                            puntuacion=puntuacion+((6-contadorIntentos)*100);
                            System.out.println("Has obtenido un total de "+puntuacion+" puntos");
                            contadorIntentos=0; // Reiniciamos el contador de los intentos
                            palabrasIntentadas.clear(); // Vaciar la ArrayList de las palabras intentadas
                            palabraOculta = Mecanica.buscarPalabra();

                        }else {
                            contadorIntentos++;
                            System.out.println("Llevas: "+contadorIntentos+" Intentos de 6");

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
                        JuegoGuardado jue = new JuegoGuardado(nombreJuegoGuardado,palabraOculta,contadorIntentos,palabrasIntentadas,puntuacion);
                        Mecanica.guardarJuego(jue);
                        contadorIntentos=6;
                        break;
                    default:
                        System.out.println("Introduce una opción válida 1 - 2");
                }
            }
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
        ArrayList<String> palabrasIntentadas = new ArrayList<>();

//  Comienzo de juego
        System.out.println("Vamos a iniciar el juego.");

        while (contadorIntentos < INTENTOS ){
            System.out.println(palabrasIntentadas.toString());
            System.out.println("Introduce una palabra de 5 letras");
            String palabraIntento = lector.next();

            ArrayList<Character> charpalabrabuscada = new ArrayList<>();
            ArrayList<Character> charpalabraOculta = new ArrayList<>();

            for (int i = 0; i < 5; i++) {

//               Controlar entrada que sea 5 letras no menos-----------------------------------------------------------------------------------------------------
                charpalabrabuscada.add(palabraIntento.charAt(i));
                charpalabraOculta.add(palabraOculta.charAt(i));
            }

            // Comprobar que la palabra introducida tiene 5 letras
            if (palabraIntento.length() == 5){
                // Comprobar que existe la palabra
                if (palabrasOcultas.contains(palabraIntento)) {
                    palabrasIntentadas.add(palabraIntento);
                    for (int i = 0; i < 5; i++) {
                        if (palabraIntento.charAt(i) == palabraOculta.charAt(i)){
                            System.out.println("La letra " + palabraOculta.charAt(i) +" Esta en la posicion correcta");
                        } else if (charpalabraOculta.contains(charpalabrabuscada.get(i))) {
                            System.out.println("La letra "+ charpalabrabuscada.get(i)+" si esta en la palabra");
                        }else {
                            System.out.println("La letra "+ charpalabrabuscada.get(i)+ " No esta en la palabra");
                        }
                    }

                    if (palabraIntento.equalsIgnoreCase(palabraOculta)){
                        System.out.println("ENHORABUENA! PALABRA ACERTADA");
                        puntuacion=puntuacion+((6-contadorIntentos)*100);
                        System.out.println("Has obtenido un total de "+puntuacion+" puntos");
                        contadorIntentos=0; // Reiniciamos el contador de los intentos
                        palabrasIntentadas.clear(); // Vaciar la ArrayList de las palabras intentadas
                        palabraOculta = Mecanica.buscarPalabra();

                    }else {
                        contadorIntentos++;
                        System.out.println("Llevas: "+contadorIntentos+" Intentos de 6");

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
                    JuegoGuardado jue = new JuegoGuardado(nombreJuegoGuardado,palabraOculta,contadorIntentos,palabrasIntentadas,puntuacion);
                    Mecanica.guardarJuego(jue);
                    contadorIntentos=6;
                    break;
                default:
                    System.out.println("Introduce una opcion valida 1 - 2");
            }
        }
    }



}
