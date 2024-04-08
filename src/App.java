import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class App {
    static public void menu (){
        System.out.println("""
                1.- Nuevo Juego
                2.- Cargar Juego
                3.- Consultar puntuaciones
                4.- Salir
                
                Elija una opción:
                 
                """);
    }
    static public void inicioJuego() {
//        Llamada de metodos para el inicio del juego

    }
    public static void main(String[] args) {
        FileReader fr = null;
        BufferedReader br = null;
        Scanner lector = new Scanner(System.in);
        Scanner lectorpalabras = null;
        ArrayList<String> listaNomOcultos = null;
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
                }
            }
        }catch (IOException e){
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
        



//      Declaracio+n de variables del MAIN
        int opt = 0;

        menu();
        opt = lector.nextInt();
        switch (opt){
            case 1:
//              Llamada a la función para iniciar un juego nuevo.


                break;
            case 2:
//              Llamada para continuar un juego guardado

                break;
            case 3:
//              Llamada a listado de puntuaciones

                break;
            case 4:
//              Salir del menu

                break;
            default:
                System.out.println("elija una opción válida");
        }
    }
}
