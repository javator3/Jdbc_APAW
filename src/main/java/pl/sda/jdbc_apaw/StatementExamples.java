package pl.sda.jdbc_apaw;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatementExamples {



    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksiegarnia?serverTimezone=UTC&logger=com.mysql.cj.log.Slf4JLogger&profileSQL=true", "root", "dupa23");
//        createTable(connection);
        insert5newEmployee2(connection);
//            delete1Employee(connection, 1000);
//        dropTable(connection);
//        selectNameAndSalaryFromTableNameAsc(connection);
//        selectNameWhereSalaryOver2000(connection);
//        selectNameStartsWithAnA(connection);
//        List<Employee> employees = selectEmployee(connection);
//        System.out.println(employees);;

    }

    static void createTable(Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        statement.execute("create table employee3( \n" +
                "id smallint not null auto_increment, \n" +
                "name varchar(50), \n" +
                "salary int, \n" +
                "primary key (id))");

    }

    static void insert5newEmployee2 (Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into employee3(name, salary) values (?, ?)");

        for (int i = 0; i < 5; i++) {
            preparedStatement.setString(1, "imie"+i);
            preparedStatement.setInt(2, new Random().nextInt(2000));
            preparedStatement.executeUpdate();

        }
    }

    static void delete1Employee (Connection connection ,int salary) throws SQLException{


            PreparedStatement preparedStatement = connection.prepareStatement("delete from employee3 where salary < ?");
            preparedStatement.setInt(1, salary);
            preparedStatement.executeUpdate();
    }

    static void selectNameAndSalaryFromTableNameAsc (Connection connection) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select name, salary from employee3 order by name asc");

        while(resultSet.next()){
            String name = resultSet.getString("name");
            int salary = resultSet.getInt(2);
            System.out.println(name + " " + salary);
        }
    }

    static void selectNameWhereSalaryOver2000 (Connection connection) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select name, salary from employee3 where salary > 2000");

        while(resultSet.next()){
            String name = resultSet.getString("name");
            int salary = resultSet.getInt(2);
            System.out.println(name + " " + salary);
        }
    }

    static void selectNameStartsWithAnA (Connection connection) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select name, salary from employee3 where name like 'a%'");

        while(resultSet.next()){
            String name = resultSet.getString("name");
            int salary = resultSet.getInt(2);
            System.out.println(name + " " + salary);
        }
    }


    static void insert5newEmployees(Connection connection) throws SQLException {

        Statement statement = connection.createStatement();

        for (int i = 0; i < 5; i++) {
            String formatedString = String.format("insert into employee3(name, salary) values ('name%s', 1234)", i);
            statement.executeUpdate(formatedString);

        }
    }

    static void dropTable (Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("drop table employee3");
    }

    static List<Employee> selectEmployee(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select id, name, salary from employee3");
        List<Employee> result = new ArrayList<Employee>();
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int salary = resultSet.getInt("salary");
            Employee employee = new Employee(id, name, salary);
            result.add(employee);
        }
        return result;

    }

}
