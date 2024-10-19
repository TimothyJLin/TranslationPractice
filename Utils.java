import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Utils {
    public static void main(String[] args) {
        Utils util=new Utils();
        util.addNumbersThrough99();
    }

    public void addNumbersThrough99() {
        String[] unitDigit={"", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve"};
        String[] tensDigit={"treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa"};
        int increment=30;
        try {
            File numberText=new File("./SpanishNumbers2.txt");
            FileWriter fw=new FileWriter(numberText);
            BufferedWriter bw=new BufferedWriter(fw);
            for (String tens:tensDigit) {
                for (String unit:unitDigit) {
                    if (!unit.equals("")) {
                        bw.write(tens+" y "+unit+":"+increment+"\n");
                    } else {
                        bw.write(tens+":"+increment+"\n");
                    }
                    increment++;
                }
            }
            bw.close();
        } catch(Exception e) {
            System.out.println("exception");
            e.printStackTrace();
        }
    }
}
