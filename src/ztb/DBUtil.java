package ztb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBUtil {
	/*
	 * 获取数据库连接。
	 */
	public static Connection getConnection() throws SQLException {
		try {
			// 指定驱动程序
			Class.forName(Constants.DRIVER);
			// 建立数据库连结
			Connection conn = DriverManager.getConnection(Constants.URL,
					Constants.USERNAME, Constants.PASSWORD);
			return conn;
		} catch (Exception e) {
			// 如果连接过程出现异常，抛出异常信息
			e.printStackTrace();
			throw new SQLException("驱动错误或连接失败！");
		}
	}

	/*
	 * 关闭 Statement 对象。
	 */
	public static void closeStatement(Statement stmt) throws SQLException {
		try {
			// 关闭stmt
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			throw new SQLException("关闭Statement失败！");
		}
	}

	/*
	 * 关闭 ResultSet 对象。
	 */
	public static void closeResultSet(ResultSet rs) throws SQLException {
		try {
			// 关闭 rs
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			throw new SQLException("关闭ResultSet失败！");
		}
	}

	/*
	 * 关闭Connection 对象。
	 */
	public static void closeConnection(Connection conn) throws SQLException {
		try {
			// 关闭 rs
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			throw new SQLException("关闭Connection失败！");
		}
	}
}
