package writing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Read2 {
    public static void main(String[] args) {
        try (Writer fw = new FileWriter("file.txt");
             BufferedWriter bw = new BufferedWriter(fw);) {
            bw.write("Hello World");
            bw.newLine();
            bw.write("Welcome to Java");
            bw.newLine();
            bw.write("File handling in Java");
            bw.newLine();
            System.out.println("Data written to file successfully.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
