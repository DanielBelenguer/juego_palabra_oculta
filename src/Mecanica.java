import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Mecanica {
    // Color Constants
    private static final String YELLOW_COLOR = "\u001B[33m";
    private static final String GREEN_COLOR = "\u001B[32m";
    private static final String RED_COLOR = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    // Constantes Globales
    private static Scanner lectorpalabras = null;
    public static ArrayList listaNomOcultos = new ArrayList<>();


    public static void cargarPuntuacion (){
        FileReader fr = null;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("Puntuaciones.txt"));
            HashMap puntuaciones = new HashMap<>();

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
    public static void cargarPalabras(){
        FileReader fr = null;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("Palabras5L.txt"));
            listaNomOcultos = new ArrayList<>();

            String linea = null,palabra = null;

            while ((linea = br.readLine()) != null){
                lectorpalabras = new Scanner(linea);
                lectorpalabras.useDelimiter(",");
                while (lectorpalabras.hasNext()){
                    palabra = lectorpalabras.next();
                    listaNomOcultos.add(palabra);
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
//    static public String buscarPalabra () {
//        Random random = new Random();
//        int num = random.nextInt(listaNomOcultos.size());
//        String palabraOculta = listaNomOcultos.get(num);
//        System.out.println(palabraOculta);
//        return palabraOculta;
//    }



}
