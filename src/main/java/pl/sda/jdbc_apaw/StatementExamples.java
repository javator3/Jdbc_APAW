package pl.sda.jdbc_apaw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementExamples {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksiegarnia?serverTimezone=UTC", "root", "dupa23");
//        createTable(connection);
        insert5newEmployees(connection);

    }

    static void createTable(Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        statement.execute("create table employee3( \n" +
                "id smallint not null auto_increment, \n" +
                "name varchar(50), \n" +
                "salary int, \n" +
                "primary key (id))");

    }

    static void insert5newEmployees(Connection connection) throws SQLException {

        Statement statement = connection.createStatement();

        for (int i = 0; i < 5; i++) {
            statement.executeUpdate("insert into employee3(name, salary) values ('name', 1234)");

        }
    }
}
