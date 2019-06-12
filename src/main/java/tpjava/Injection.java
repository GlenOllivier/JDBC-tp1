package tpjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Injection {

	public static void main(String[] args) throws SQLException {
		Scanner scanner = new Scanner(System.in);

		String url = "jdbc:mysql://localhost:3306/tp-jdbc?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String login = "root";
		String pwd = "";

		System.out.println("Entrez votre login :");
		String identifiant = scanner.nextLine();
		System.out.println("Entrez votre mdp :");
		String mdp = scanner.nextLine();

		Connection connection = DriverManager.getConnection(url, login, pwd);
		String query = "SELECT * FROM t_contact_con INNER JOIN t_address_add USING(add_id) WHERE con_first_name = ? AND con_last_name = ?;";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, identifiant);
		statement.setString(2, mdp);

		ResultSet result = statement.executeQuery();
		while (result.next()) {
			System.out.println(result.getString(4) + " " + result.getString(5) + " " + result.getString(6) + " "
					+ result.getString(7) + " " + result.getString(9) + " " + result.getString(8) + " "
					+ result.getString(10));
		}

		result.close();
		statement.close();
		connection.close();
		scanner.close();

	}

}
