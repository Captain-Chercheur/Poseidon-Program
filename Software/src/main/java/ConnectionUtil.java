import java.sql.*;
import javax.swing.*;
public class ConnectionUtil {
    Connection conn = null;
    public static Connection connectdb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://152.228.217.228/wordpress_db", "poseidon", "Poseidon");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
        public static Connection connectdbPoseidon()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://152.228.217.228/Poseidon","poseidon","Poseidon");
            return conn;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}