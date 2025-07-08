package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
	
	//つぶやきを投稿
	public void insert(PostInfo postInfo) {
		//sql文の準備
		String sql = 
				"INSERT INTO posts (user_id,comment, picture, shop, postTime) " +
		                 "VALUES (?, ?, ?, ?,NOW())";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql);){
			
			
			stmt.setString(1, postInfo.userId());
		    stmt.setString(2, postInfo.comment());
		    stmt.setBytes(3, postInfo.picture());
		    stmt.setString(4, postInfo.shopName());
		    
		    //sql文の実行
		    stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	//つぶやきを編集
	public void edit(PostInfo postId) {
		//SELECT文を準備
		String spl = "select user_id,comment,picture,shop,postTime from posts where post_id = ? ";
		try(PreparedStatement pStmt = conn.prepareStatement(spl);){
		pStmt.setString(1,postInfo.postId());
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public List<PostInfo> findAll(){
		List<PostInfo> mutterList = new ArrayList<>();
		//JDBCドライバーを読み込む
		
			//select文の準備
			String sql = "select post_id, user_id,comment,picture,shop,postTime from posts order by postTime desc";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//select文を実行
			ResultSet rs = pStmt.executeQuery();
			
			//select文の結果をArrayListに格納
			while(rs.next()) {
				int postId = rs.getInt("post_id");
				String userId = rs.getString("user_id");
				String comment = rs.getString("comment");
				Byte picture = rs.getByte("picture");
				Timestamp postTime = rs.getTimestamp("postTime");
				
				PostInfo mutter = new PostInfo(id,userName,text);
				mutterList.add(mutter);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
			//return null;
		}
		return mutterList;
	}
	
	
	
	
	
	
	
	
	
	
}
