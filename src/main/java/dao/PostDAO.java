package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.PostInfo;

public class PostDAO {
	//データベース接続に使用する情報
		private final String JDBC_URL = "jdbc:postgresql://localhost:5432/buzz";
		private final String DB_USER = "postgres";
		private final String DB_PASS = "root";
		private  Connection conn = null;
	
	public PostDAO() {
		//JDBCドライバーを読み込む
		try {
			Class.forName("org.postgresql.Driver");
			//データベース接続
			 conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
		}catch(ClassNotFoundException  | SQLException e) {
			throw new IllegalStateException("JDBCドライバーを読み込めませんでした");
		}
	}
	
	public void insert(PostInfo postInfo) {
		String sql = 
				"INSERT INTO customers (user_id,comment, picture, shop, postTime) " +
		                 "VALUES (?, ?, ?, ?,NOW())";
		
		
		//データベース接続
		try (PreparedStatement stmt = conn.prepareStatement(sql);){
			
			
			stmt.setString(1, postInfo.userId());
		    stmt.setString(2, postInfo.comment());
		    stmt.setBytes(3, postInfo.pic());
		    stmt.setString(4, postInfo.shopName());

		    stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
}
