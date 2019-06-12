package tpjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws SQLException {

		String url = "jdbc:mysql://localhost:3306/tp-jdbc?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String login = "root";
		String pwd = "";

		Connection connection = DriverManager.getConnection(url, login, pwd);
		Statement statement = connection.createStatement();

		statement.executeUpdate(
				"INSERT INTO t_address_add(add_number, add_street, add_city, add_zipcode, add_country)"
						+ " VALUES ('24', 'rue des brancardiers', 'Plabennec', '29860', 'France')",
				Statement.RETURN_GENERATED_KEYS);

		ResultSet result = statement.getGeneratedKeys();

		if (result.next()) {
			statement.executeUpdate("INSERT INTO t_contact_con(con_email, con_first_name, con_last_name, add_id)"
					+ " VALUES ('yannig.lagadeg@stummdi.bzh','yann','lagadeg'," + result.getInt(1) + ")");
		}

		result.close();
		statement.close();
		connection.close();
	}
}
