package servlet;
import java.sql.Connection;
import java.sql.DriverManager;
public class DbHelper {
	private static String url = "jdbc:mysql://localhost:3306/datamake?characterEncoding=utf8&amp;useSSL=true"; // ???????
	private static String userName = "root"; // ??????????
	private static String passWord = "lin0613"; // ?????????
	private static Connection conn = null;
	private DbHelper() {

	}
	public static Connection getConnection() {
		if (null == conn) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, userName, passWord);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	public static void main(String[] args) { // ???????????????
		System.err.println(getConnection());
	}
}

