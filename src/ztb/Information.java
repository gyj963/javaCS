package ztb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Information {
	static String sex;
	static String name;
	static String birth;
	static String year;
	static String month;
	static String academic;
	static String territory;
	static String education;
	static String self;
	static int flag=0;
	static int score;
	
	public static void getInformation(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs=null;
		String sql = "select xingming,xingbie,shengri,xueli,zhuanyelingyu,jiaoyubeijing,gerenshuoming,beixuanfou,pingjia from expert where yonghuming=?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL",Constants.USERNAME,Constants.PASSWORD);
		    stmt = conn.prepareStatement(sql);
		    stmt.setString(1,StaticInfo.USER_NAME);
		    rs=stmt.executeQuery();
		    
		    while(rs.next()){
		    	if(rs.getString(1)==null){
		    		Information.name=null;
		    	}else{
		    		Information.name=rs.getString(1);
		    	}
		    	if(rs.getString(2)==null){
		    		Information.sex=null;
		    	}else{
		    		Information.sex=rs.getString(2);
		    	}
		    	if(rs.getString(3)==null){
		    		Information.birth=null;
		    		Information.year=null;
		    		Information.month=null;
		    	}else{
		    		Information.birth=rs.getString(3);	
		    		Information.year=Information.birth.substring(0, 4);
		    		System.out.println(Information.year+Information.month);
		    		Information.month=Information.birth.substring(6, 8);
		    		System.out.println(Information.year+Information.month);
		    	}
		    	
		    	if(rs.getString(4)==null){
		    		Information.academic=null;
		    	}else{
		    		Information.academic=rs.getString(4);
		    	}
		    	if(rs.getString(5)==null){
		    		Information.territory=null;
		    	}else{
		    		Information.territory=rs.getString(5);
		    	}
		    	if(rs.getString(6)==null){
		    		Information.education=null;
		    	}else{
		    		Information.education=rs.getString(6);
		    	}
		    	if(rs.getString(7)==null){
		    		Information.self=null;
		    	}else{
		    		Information.self=rs.getString(7);
		    	}
		    	if(rs.getInt(8)==1){
		    		Information.flag=1;
		    	}
		    	if(rs.getInt(9)!=0){
		    		Information.score=rs.getInt(9);
		    	}
		    }
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(stmt!=null){
					stmt.close();
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