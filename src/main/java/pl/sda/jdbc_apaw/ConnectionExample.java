package pl.sda.jdbc_apaw;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionExample {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        DriverManager.getConnection("jdbc:mysql://localhost:3306/ksiegarnia?serverTimezone=UTC","root", "dupa23");

    }
}
