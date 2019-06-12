package tpjava;

import java.sql.*;
import java.util.Scanner;

public class Optimisation {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/tp-jdbc?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String login = "root";
        String pwd = "";


        try (Connection connection = DriverManager.getConnection(url, login, pwd);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM t_contact_con")) {

            while(resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
