import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main2{
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/practice";
        String username = "postgres";
        String password = "12345";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database successfully!");

            String sql="INSERT INTO person5(name,email)values('John','john@northernarc.com'),('Abraham','abraham@northernaec.com')";
            PreparedStatement stmt = conn.prepareStatement(sql);
            System.out.println("Excecuting query"+ sql);
            int rowsUpdated=stmt.executeUpdate();

            System.out.println(rowsUpdated);
            System.out.println("insert done successfully");
        } catch (SQLException e) {

            System.err.println("Failed to connect");
            e.printStackTrace();
        }
    }
}