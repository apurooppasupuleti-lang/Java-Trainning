import java.sql.*;

public class Main3{
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/practice";
        String username = "postgres";
        String password = "12345";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database successfully!");

            String sql="Select id,name from person5 order by name";
            PreparedStatement stmt = conn.prepareStatement(sql);
            System.out.println("Excecuting query"+ sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt("ID")+""+ rs.getString(2));
            }
            System.out.println("data read successfully");
        } catch (SQLException e) {

            System.err.println("Failed to connect");
            e.printStackTrace();
        }
    }
}