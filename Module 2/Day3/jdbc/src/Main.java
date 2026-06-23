import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main{
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/practice";
        String username = "postgres";
        String password = "12345";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database successfully!");

            String sql="Create table if not exists person5(id serial primary key, name VARCHAR(255),email varchar(255))";
            PreparedStatement stmt = conn.prepareStatement(sql);
            System.out.println("Excecuting query"+ sql);
            stmt.execute();
            System.out.println("Table created successfully");
        } catch (SQLException e)
        {
            System.err.println("Failed to connect");
            e.printStackTrace();
        }
    }
}