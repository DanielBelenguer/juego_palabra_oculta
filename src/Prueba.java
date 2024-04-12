import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Prueba {
    public static void main(String[] args) {
        FileReader fr = null;
        BufferedReader br = null;
        ArrayList<String> palabrasOcultas = null;
        Scanner lectorpalabras = null;

        try {
            fr = new FileReader("/home/daniel/Documentos/IdeaProjects/juego_palabra_oculta/src/Palabras.txt");
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
        System.out.println(palabrasOcultas.get(0));
        System.out.println(palabrasOcultas.get(1));
        System.out.println(palabrasOcultas.get(2));
    }
}
