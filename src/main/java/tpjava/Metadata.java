package tpjava;

import java.sql.*;
import java.util.Scanner;

public class Metadata {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/tp-jdbc?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String login = "root";
        String pwd = "";

        Connection connection = DriverManager.getConnection(url, login, pwd);

        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getTables(connection.getCatalog(), null, null, null);

        while (resultSet.next()) {
            System.out.println(resultSet.getString("TABLE_NAME"));
        }

        String nomTable;
        do {
            System.out.println("Entrez un nom de table ou exit :");
            nomTable = scanner.nextLine();

            if (nomTable.equals("exit")) {
                break;
            }

            String query = "SELECT * FROM " + nomTable;
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet result;
            try {
                result = statement.executeQuery();

                ResultSetMetaData resultSetMetaData = result.getMetaData();
                int count = resultSetMetaData.getColumnCount();

                System.out.println("Informations de " + nomTable + " :");

                for (int i = 1; i <= count; i++) {
                    System.out.printf("%-30s", resultSetMetaData.getColumnLabel(i) + "[" + resultSetMetaData.getColumnTypeName(i) + "]");
                }

                System.out.println();
                while (result.next()) {
                    for (int i = 1; i <= count; i++) {
                        System.out.printf("%-25s", result.getString(i));
                    }
                    System.out.println();
                }

                result.close();
                statement.close();

            } catch (SQLException e) {
                System.out.println("Nom de table inconnu !");
            }

        } while (true);
        resultSet.close();
        connection.close();
        scanner.close();

    }
}
