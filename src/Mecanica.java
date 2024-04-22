import java.io.*;
import java.util.*;

public class Mecanica {
    // Color Constants
    private static final String YELLOW_COLOR = "\u001B[33m";
    private static final String GREEN_COLOR = "\u001B[32m";
    private static final String BLUE_COLOR = "\033[34m";
    private static final String RED_COLOR = "\033[31m";
    private static final String RESET = "\u001B[0m";
    // Constantes Globales
   private static Scanner lectorpalabras = null;
   public static Scanner lector = new Scanner(System.in);
   public static ArrayList<String> palabrasOcultas = new ArrayList<>();
   public static ArrayList<String> listaIntentosPalabras = new ArrayList<>(); // No se utiliza nunca
   public static HashMap<String,Integer> puntuaciones = new HashMap<>();
   private static final int INTENTOS = 6;
   private static int puntuacion = 0;
    public static void cargarPuntuacion (){
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader("src\\archivosNecesarios\\Puntuaciones.csv");
            br = new BufferedReader(fr);
            puntuaciones = new HashMap<>();
            String linea = null,nombre = null;
            int puntuacion;
            while ((linea = br.readLine()) != null){
                lectorpalabras = new Scanner(linea);
                lectorpalabras.useDelimiter(",");
                while (lectorpalabras.hasNext()){
                    nombre = lectorpalabras.next();
                    // Hacemos la conversion de un String que recibimos del lector a un integer
                    puntuacion = Integer.parseInt(lectorpalabras.next());
                    puntuaciones.put(nombre,puntuacion);
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
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter("src\\archivosNecesarios\\Puntuaciones.csv");
            bw = new BufferedWriter(fw);
            for (Map.Entry<String, Integer> en : puntuaciones.entrySet()) {
                String nombre = en.getKey();
                int puntu = (int) en.getValue();
                bw.write(nombre);
                bw.write(",");
                bw.write(String.valueOf(puntu));
                bw.newLine();
            }
        }catch (IOException e) {
            e.getMessage();
        }finally {
            try {
                bw.close();
                fw.close();
            }catch (IOException e) {
                e.getMessage();
            }
        }
    }
    public static void cargarPalabras(){
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader("src\\archivosNecesarios\\Palabras.txt");
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
            fr = new FileReader("src\\archivosNecesarios\\Comosejuega.txt");
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
        FileOutputStream fo = null;
        ObjectOutputStream os = null;
        try {
            fo = new FileOutputStream("src\\partidasGuardadas\\"+ jue.getNombreJuegoGuardado() +".bin");
            os = new ObjectOutputStream(fo);
            os.writeObject(jue);
            os.flush();
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
            fi = new FileInputStream("src\\partidasGuardadas\\" + nombreJuego + ".bin");
            is = new ObjectInputStream(fi);
            JuegoGuardado juegoGuardado= (JuegoGuardado) is.readObject();;
//            Iniciar juego cargado
            //      Declaracion de variables Internas de CargarJuedo
            String palabraOculta = juegoGuardado.getPalabraSecreta();
            int contadorIntentos = juegoGuardado.getIntentos(), puntuacion=juegoGuardado.getPuntuacion();
            ArrayList<String> palabrasIntentadas = juegoGuardado.getpalabrasIntentadas();
            System.out.println("Vamos a continuar el juego.");
            System.out.println("Con una puntuación de: " + puntuacion);
            System.out.println("Tienes estas palabras como intentos: ");
            while (contadorIntentos < INTENTOS ){
                if (!palabrasIntentadas.isEmpty()){
                    System.out.println(palabrasIntentadas.toString());
                }else {
                    System.out.println(" ");
                }
                System.out.println("Introduce una palabra de 5 letras");
                String palabraIntento = lector.next();
                ArrayList<Character> charpalabrabuscada = new ArrayList<>();
                ArrayList<Character> charpalabraOculta = new ArrayList<>();
                try{
                    for (int i = 0; i < 5; i++) {
                        charpalabrabuscada.add(palabraIntento.charAt(i));
                        charpalabraOculta.add(palabraOculta.charAt(i));
                    }
                }catch (StringIndexOutOfBoundsException e){
                    e.getMessage();
                }
                // Comprobar que la palabra introducida tiene 5 letras
                if (palabraIntento.length() == 5){
                    // Comprobar que existe la palabra
                    if (palabrasOcultas.contains(palabraIntento)) {
                        palabrasIntentadas.add(palabraIntento);
                        for (int i = 0; i < 5; i++) {
                            if (palabraIntento.charAt(i) == palabraOculta.charAt(i)){
                                System.out.print(" " + GREEN_COLOR + palabraOculta.charAt(i)+ RESET); // Correcta
                            } else if (charpalabraOculta.contains(charpalabrabuscada.get(i))) {
                                System.out.print(" "+ YELLOW_COLOR + charpalabrabuscada.get(i)+ RESET); // Esta pero no posición
                            }else {
                                System.out.print(" " + charpalabrabuscada.get(i)); // no pintar
                            }
                        }
                        System.out.println();
                        if (palabraIntento.equalsIgnoreCase(palabraOculta)){
                            System.out.println(BLUE_COLOR + "ENHORABUENA! PALABRA ACERTADA" + RESET);
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
                if (contadorIntentos == INTENTOS){
                    System.out.println(RED_COLOR + "Has perdido la partida!!" + RESET);
                    System.out.println("\ncon un total de: \n"+ puntuacion + " Puntos");
                    int opt = 0;
                    try {
                        System.out.println("""
                        ¿Quieres guardar tu puntuación?
                        1.- Si
                        2.- No
                        """);
                        opt = lector.nextInt();
                    }catch (InputMismatchException e){
                        e.getMessage();
                    }
                    lector.nextLine();
                    switch (opt){
                        case 1:
                            System.out.println("¿Con que nombre quieres guardar tu puntuación");
                            String nombre = lector.nextLine();
                            if (puntuaciones.containsKey(nombre) && puntuaciones.get(nombre) < puntuacion){
                                puntuaciones.put(nombre,puntuacion);
                                System.out.println("Puntuación actualizada");

                            } else if (puntuaciones.containsKey(nombre)) {
                                System.out.println("El nombre introducido ya existe.");
                            }else {
                                puntuaciones.put(nombre,puntuacion);
                                System.out.println("Puntuación registrada");
                            }
                            break;
                        case 2:
                            System.out.println("Saliendo al menu");
                            break;
                        default:
                            System.out.println("Introduce una opción correcta.");
                    }
                }else {
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
        System.out.println(palabraOculta); // Esto solo es para las pruebas, no debe mostrarse.-------------------------
        return palabraOculta;
    }
    static public void iniciarJuego() {
//      Declaración de variables Internas de IniciarJuego
        String palabraOculta = Mecanica.buscarPalabra();
        int contadorIntentos = 0;
        ArrayList<String> palabrasIntentadas = new ArrayList<>();
//  Comienzo de juego
        System.out.println("Vamos a iniciar el juego.");
        while (contadorIntentos < INTENTOS ){
            if (!palabrasIntentadas.isEmpty()){
                System.out.println(palabrasIntentadas.toString());
            }else {
                System.out.println(" ");
            }
            System.out.println("Introduce una palabra de 5 letras");
            String palabraIntento = lector.next();
            ArrayList<Character> charpalabrabuscada = new ArrayList<>();
            ArrayList<Character> charpalabraOculta = new ArrayList<>();
            try{
                for (int i = 0; i < 5; i++) {
                    charpalabrabuscada.add(palabraIntento.charAt(i));
                    charpalabraOculta.add(palabraOculta.charAt(i));
                }
            }catch (StringIndexOutOfBoundsException e){
                e.getMessage();
            }
            // Comprobar que la palabra introducida tiene 5 letras
            if (palabraIntento.length() == 5){
                // Comprobar que existe la palabra
                if (palabrasOcultas.contains(palabraIntento)) {
                    palabrasIntentadas.add(palabraIntento);
                    for (int i = 0; i < 5; i++) {
                        if (palabraIntento.charAt(i) == palabraOculta.charAt(i)){
                            System.out.print(" " + GREEN_COLOR + palabraOculta.charAt(i)+ RESET); // Correcta
                        } else if (charpalabraOculta.contains(charpalabrabuscada.get(i))) {
                            System.out.print(" "+ YELLOW_COLOR + charpalabrabuscada.get(i)+ RESET); // Esta pero no posición
                        }else {
                            System.out.print(" " + charpalabrabuscada.get(i)); // no pintar
                        }
                    }
                    System.out.println();
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
            if (contadorIntentos == INTENTOS){
                System.out.println(RED_COLOR + "Has perdido la partida!!" + RESET);
                System.out.println("\ncon un total de: \n"+ puntuacion + " Puntos");
                int opt = 0;
                try {
                    System.out.println("""
                        ¿Quieres guardar tu puntuación?
                        1.- Si
                        2.- No
                        """);
                    opt = lector.nextInt();
                }catch (InputMismatchException e){
                    e.getMessage();
                }
                lector.nextLine();
                switch (opt){
                    case 1:
                        boolean control = true;
                        while (control){
                            System.out.println("¿Con que nombre quieres guardar tu puntuación");
                            String nombre = lector.nextLine();
                            if (puntuaciones.containsKey(nombre) && puntuaciones.get(nombre) < puntuacion){
                                puntuaciones.put(nombre,puntuacion);
                                control = false;
                                System.out.println("Puntuación actualizada");

                            } else if (puntuaciones.containsKey(nombre)) {
                                control = true;
                                System.out.println("El nombre introducido ya existe.");
                            }else {
                                puntuaciones.put(nombre,puntuacion);
                                control = false;
                                System.out.println("Puntuación registrada");
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Saliendo al menu");
                        break;
                    default:
                        System.out.println("Introduce una opción correcta.");
                }
            }else {
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
}
