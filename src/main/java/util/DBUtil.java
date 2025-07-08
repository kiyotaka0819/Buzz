package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	private static final String URL = "jdbc:postgresql://172.31.98.112:5432/buzz";
    private static final String USER = "postgres";
    private static final String PASS = "root";

public static Connection getConnection() throws Exception {
    Class.forName("org.postgresql.Driver");
    return DriverManager.getConnection(URL, USER, PASS);
	}
}