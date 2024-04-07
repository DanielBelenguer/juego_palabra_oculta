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
                """);
    }
    static public void seleccion (int opt) {
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
    public static void main(String[] args) {
        FileReader fr = null;
        BufferedReader br = null;
        int opt = 0;
        Scanner lector = new Scanner(System.in);
//        Creacion de Collección
        ArrayList<Palabra> lista = new ArrayList<Palabra>();
        menu();
        System.out.println("Elija una opción: ");

        try {
            opt = lector.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Lo introducido por teclado, no es correcto tiene que ser un numero del 1 al 4");
        }

        try {
            fr = new FileReader("/home/daniel/Documentos/IdeaProjects/juego_palabra_oculta/src/Palabras5L.txt");
            br = new BufferedReader(fr);

//            Cargar el archivo de palabran en memoria RAM en una coleccion.

        }catch (IOException e){
            e.getMessage();
        }

    }
}
