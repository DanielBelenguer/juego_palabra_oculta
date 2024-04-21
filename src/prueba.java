import java.util.HashMap;
import java.util.Map;

public class prueba {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
        String nombre = "Daniel";
        int puntu = 1000;
        map.put(nombre,puntu);
        map.put("Miguel", 5000);

        for (Map.Entry<String, Integer> en : map.entrySet()) {
            String nombre1 = en.getKey();
            int puntu1 = (int) en.getValue();
            System.out.println(nombre1 + " " + puntu1);
        }

        System.out.println(map.get("Miguel"));
    }
}
