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
	public void postInsert(PostInfo postInfo) {
		//sql文の準備
		String sql = 
				"INSERT INTO posts (user_id,comment, picture, shop, postTime) " +
		                 "VALUES (?, ?, ?, ?,NOW())";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql);){
			
			
			stmt.setString(1, postInfo.userId());
		    stmt.setString(2, postInfo.comment());
		    stmt.setBytes(3, postInfo.pic());
		    stmt.setString(4, postInfo.shopName());
		    
		    //sql文の実行
		    stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//つぶやきを編集
	public void postEdit(PostInfo postInfo,int postId) {
		String sql;
		boolean updatePicture = postInfo.pic() != null;
		
		if(updatePicture) {
			//画像の変更がある場合
			sql = "UPDATE posts SET  comment = ?, picture = ?,shop = ?, postTime =NOW() WHERE post_id = ?";
		}else {
			//画像が貼り付けられてないとき
			sql = "UPDATE posts SET  comment = ?, shop = ?, postTime =NOW() WHERE post_id = ?";
		}
		
		
		try(PreparedStatement stmt = conn.prepareStatement(sql);){
			
			
			if(updatePicture) {
				stmt.setString(1, postInfo.comment());
			    stmt.setBytes(2, postInfo.pic());       // 画像（byte[]）
			    stmt.setString(3, postInfo.shopName());
			    stmt.setInt(4, postId);  // 編集対象のpost_idを指定
				 
			}else {
				stmt.setString(1, postInfo.comment());
				stmt.setString(2, postInfo.shopName());
				stmt.setInt(3, postId);  // 編集対象のpost_idを指定
			}
			 stmt.setString(1, postInfo.comment());
		        stmt.setBytes(2, postInfo.pic());       // 画像（byte[]）
		        stmt.setString(3, postInfo.shopName());
		       
		        stmt.setInt(5, postId);  // 編集対象のpost_idを指定
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// つぶやきを削除
	public void postDelete(int postId)  {
		String sql = "DELETE FROM posts WHERE post_id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, postId);
			//削除を実行
			stmt.executeUpdate();
		}catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	//指定されたIDのつぶやきを1件だけ取得する
	public PostInfo findById(int postId) {
	    PostInfo postInfo = null;
	    String sql = "SELECT post_id, user_id, comment, picture, shop, postTime FROM posts WHERE post_id = ?";

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, postId);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                int id = rs.getInt("post_id");
	                String userId = rs.getString("user_id");
	                String comment = rs.getString("comment");
	                byte[] pic = rs.getBytes("picture");
	                String shop = rs.getString("shop");
	                Timestamp postTime = rs.getTimestamp("postTime");

	                postInfo = new PostInfo(id, userId, comment, pic, shop, postTime);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return postInfo;
	}
	
	public List<PostInfo> postFindAll(){
		List<PostInfo> postList = new ArrayList<>();
		
		
			//select文の準備
			String sql = "select post_id, user_id,comment,picture,shop,postTime from posts order by postTime desc";
			try(PreparedStatement stmt = conn.prepareStatement(sql);){
			
			//select文を実行
			ResultSet rs = stmt.executeQuery();
			
			//select文の結果をArrayListに格納
			while(rs.next()) {
				int postId = rs.getInt("post_id");
				String userId = rs.getString("user_id");
				String comment = rs.getString("comment");
				byte[] pic = rs.getBytes("picture");
				String shop = rs.getString("shop");
				Timestamp postTime = rs.getTimestamp("postTime");
				
				PostInfo post = new PostInfo(postId, userId, comment, pic, shop, postTime);
				postList.add(post);
			}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return postList;
	}
	
	
}
