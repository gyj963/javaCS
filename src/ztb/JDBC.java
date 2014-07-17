package ztb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBC {
	public static void putIntoDB(){
		Connection conn = null;
		PreparedStatement pstmt = null;
//		ResultSet rs = null; 
//		艾玛，变量太多要仔细数
		String sql = "UPDATE expert SET xingming=?,xingbie=?,shengri=?,xueli=?," +
				"zhuanyelingyu=?,jiaoyubeijing=?,gerenshuoming=? WHERE yonghuming=?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","gyj","gyj963");
		    

		    pstmt = conn.prepareStatement(sql);

		    pstmt.setString(1, Information.name);
		    pstmt.setString(2, Information.sex);
		    pstmt.setString(3, Information.year+"年 "+Information.month+"月");
		    pstmt.setString(4, Information.academic);
		    pstmt.setString(5, Information.territory);
		    pstmt.setString(6, Information.education);
		    pstmt.setString(7, Information.self);				//	上面这些是用户注册后自己的数据。
		    pstmt.setString(8, StaticInfo.USER_NAME);  //	这里就是大哥要传递的参数。我先自己定义一个静态类用起。

		    pstmt.executeUpdate();

		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}