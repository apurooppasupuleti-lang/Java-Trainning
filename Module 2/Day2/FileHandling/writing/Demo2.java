package writing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Demo2 {
    public static void main(String[] args) {
        try (Reader fr = new FileReader("myfirstfile.txt")) {
            int value;
            do {
                value = fr.read();
                if (value != -1) {
                    System.out.print((char) value);
                }
            } while (value != -1);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}