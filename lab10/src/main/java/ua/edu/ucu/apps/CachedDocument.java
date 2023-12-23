package ua.edu.ucu.apps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
public class CachedDocument {
    private Connection connection;

    private Connection connectToDatabase() {
        String url = "jdbc:sqlite:data.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public boolean recordExist(String gcsPath) {
        String sql = "SELECT count(*) FROM your table WHERE path = ?";
        try (Connection connection = this.connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                 preparedStatement.setString(1, gcsPath);
                 ResultSet rs = preparedStatement.executeQuery();
                 return rs.getInt(1) > 0;
             } catch (SQLException e) {
                 System.out.println(e.getMessage());
             }
             return false;
    }

    public void injectText(String text, String gcsPath) {
        String sql = "SELECT count FROM your table WHERE path = ?";
        try (Connection con = this.connectToDatabase();
            PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, gcsPath);
                pst.setString(2, text);
                pst.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }
}
