package java_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBControl {
	private static final String CLOUDDB_DRIVER = "com.informix.jdbc.IfxDriver";

	public static Connection connect() throws SQLException, ClassNotFoundException {
		Class.forName(CLOUDDB_DRIVER);
		String jdbcURL = "jdbc:informix-sqli://crl.ptopenlab.com:9088/d_1460373370850858:INFORMIXSERVER=ifxserver1;USER=pgmjkubf;PASSWORD=aEqms3fD8B;DB_LOCALE=en_us.utf8";
		// System.out.println("@@@@@@@@@@@@@@ jdbc url =" + jdbcURL);
		return DriverManager.getConnection(jdbcURL);
	}
}
