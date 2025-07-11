package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.PostInfo;
import util.DBUtil;

public class PostDAO {
	//データベース接続に使用する情報
		
		private  Connection conn = null;
	
	public PostDAO()  {
		//JDBCドライバーを読み込む
		try {
			//データベース接続
			 conn = DBUtil.getConnection();
		}catch(Exception  e) {
			throw new IllegalStateException("JDBCドライバーを読み込めませんでした");
		}
	}
	
	//つぶやきを投稿
	public boolean postInsert(PostInfo postInfo) {
		//sql文の準備
		String sql = 
				"INSERT INTO posts (user_id,comment, pictures, shop, postTime) " +
		                 "VALUES (?, ?, ?, ?,NOW())";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql);){
			
			
			stmt.setString(1, postInfo.userId());
		    stmt.setString(2, postInfo.comment());
		    stmt.setBytes(3, postInfo.pic());
		    stmt.setString(4, postInfo.shopName());
		    
		    int result = stmt.executeUpdate();
	        return result == 1;  // 成功したら true を返す
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; // 失敗したら false を返す
	    }
	}
	
	//つぶやきを編集
	public boolean postEdit(PostInfo postInfo,int postId) {
		String sql;
		boolean updatePicture = postInfo.pic() != null;
		
		if(updatePicture) {
			//画像の変更がある場合
			sql = "UPDATE posts SET  comment = ?, pictures = ?,shop = ?, postTime =NOW() WHERE post_id = ?";
		}else {
			//画像が貼り付けられてないとき
			sql = "UPDATE posts SET  comment = ?, shop = ?, postTime =NOW() WHERE post_id = ?";
		}
		
		
		try(PreparedStatement stmt = conn.prepareStatement(sql);){
			
			
			if(updatePicture) {
				stmt.setString(1, postInfo.comment());
			    stmt.setBytes(2, postInfo.pic());       // 画像（byte[]）
			    stmt.setString(3, postInfo.shopName());
			    stmt.setInt(4, postId);  // 編集対象のposts_idを指定
				 
			}else {
				stmt.setString(1, postInfo.comment());
				stmt.setString(2, postInfo.shopName());
				stmt.setInt(3, postId);  // 編集対象のposts_idを指定
			}
			int result = stmt.executeUpdate();
	        return result == 1;  // 成功したら true を返す
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; // 失敗したら false を返す
	    }
		
	}
	
	// つぶやきを削除
	public boolean postDelete(int postId)  {
		String sql = "DELETE FROM posts WHERE posts_id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, postId);
			
			int result = stmt.executeUpdate();
	        return result ==1;  // 成功したら true を返す
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; // 失敗したら false を返す
	    }
	}
	
	//指定されたIDのつぶやきを1件だけ取得する
	public PostInfo findById(int postId) {
	    PostInfo postInfo = null;
	    String sql = "SELECT posts_id, user_id, comment, pictures, shop, postTime FROM posts WHERE post_id = ?";

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, postId);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                int id = rs.getInt("posts_id");
	                String userId = rs.getString("user_id");
	                String comment = rs.getString("comment");
	                byte[] pic = rs.getBytes("pictures");
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
	
	//つぶやきを全件表示
	public List<PostInfo> postFindAll(){
		List<PostInfo> postList = new ArrayList<>();
		
		
			//select文の準備
			String sql = "select post_id, user_id,comment,pictures,shop,postTime from posts order by postTime desc";
			try(PreparedStatement stmt = conn.prepareStatement(sql);){
			
			//select文を実行
			ResultSet rs = stmt.executeQuery();
			
			//select文の結果をArrayListに格納
			while(rs.next()) {
				int postId = rs.getInt("posts_id");
				String userId = rs.getString("user_id");
				String comment = rs.getString("comment");
				byte[] pic = rs.getBytes("pictures");
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
	//ログインユーザーのつぶやきを全件表示
	public List<PostInfo> findPostsByUserId(String userId) {
	    List<PostInfo> postList = new ArrayList<>();
	    String sql = "SELECT posts_id, user_id, comment, pictures, shop, postTime FROM posts WHERE user_id = ? ORDER BY postTime DESC";
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, userId);
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                PostInfo post = new PostInfo(
	                    rs.getInt("posts_id"),
	                    rs.getString("user_id"),
	                    rs.getString("comment"),
	                    rs.getBytes("pictures"),
	                    rs.getString("shop"),
	                    rs.getTimestamp("postTime")
	                );
	                postList.add(post);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return postList;
	}
	
	public byte[] getPictureByPostId(int postId) {
	    String sql = "SELECT pictures FROM posts WHERE posts_id = ?";
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, postId);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getBytes("pictures");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	
	
	
	
}
