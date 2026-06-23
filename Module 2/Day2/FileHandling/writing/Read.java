package writing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Read {
    public static void main(String[] args) {
        try (BufferedReader fr = new BufferedReader(new FileReader("myfirstfile.txt"))) {
            String value;
            do {
                value = fr.readLine();
                if (value != null) {
                    System.out.print(value);
                }
            } while (value != null);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
