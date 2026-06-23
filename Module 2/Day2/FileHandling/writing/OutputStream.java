package outputStream;

import java.io.FileOutputStream;

public class OutputStream {
    public static void main(String[] args) {
        try (java.io.OutputStream os = new FileOutputStream("output.txt")) {
            String data = "Hello, World!";
            os.write(data.getBytes());
            System.out.println("Data written to file successfully.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

