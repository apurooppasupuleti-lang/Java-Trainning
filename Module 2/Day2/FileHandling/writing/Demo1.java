package writing;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Demo1{
    public static void main(String[] args) {
        try (Writer fw = new FileWriter("myfirstfile.txt",true);) {
            fw.write("Hello World");
            fw.write("\nWelcome to Java IO");
            fw.write("\nThis is my first file created using Java IO");
            System.out.println("File created successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

