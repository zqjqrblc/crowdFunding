package utils;

import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtils {
	private Connection conn = null;
	private Statement stmt = null;
	public ResultSet rs = null;
//	private static String propFileName = "../DBUtils.properties";
//	private static Properties prop = new Properties();

	private static String dbClassName = null;
	private static String dbUrl = null;
	private static String dbUser = null;
	private static String dbPwd = null;

	public static Connection getConnection() {
		dbClassName = "com.mysql.cj.jdbc.Driver";
		dbUrl = "jdbc:mysql://localhost:3306/crowdfunding?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone = GMT";
		dbUser = "root";
		dbPwd = "111111";
		Connection conn = null;
		try {
			Class.forName(dbClassName).newInstance();
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public ResultSet executeQuery(String sql) {
		try {
			this.conn = getConnection();
			this.stmt = this.conn.createStatement(1004, 1007);
			this.rs = this.stmt.executeQuery(sql);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return this.rs;
	}

	public int executeUpdate(String sql) {
		int result = 0;
		try {
			this.conn = getConnection();
			this.stmt = this.conn.createStatement(1004, 1007);
			result = this.stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			result = 0;
		}
		return result;
	}

	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
}