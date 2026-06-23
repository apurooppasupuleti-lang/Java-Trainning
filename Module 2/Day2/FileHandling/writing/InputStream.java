package InputStream;

import java.io.FileInputStream;

public class InputStream {
    public static void main(String[] args) {
        try (java.io.InputStream fis = new FileInputStream("Input.txt");) {
            int value;
            while((value=fis.read()) !=-1){
                System.out.print((char)value);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}