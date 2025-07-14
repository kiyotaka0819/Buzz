package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.BuzzInfo;
import model.PostInfo;
import util.DBUtil;

public class BuzzDAO {
	//データベース接続に使用する情報
			
			private  Connection conn = null;
		
	public BuzzDAO() {
		
		try {//データベース接続
			 conn = DBUtil.getConnection();
		}catch( Exception e) {
			throw new IllegalStateException("JDBCドライバーを読み込めませんでした");
		}
	}
	
	//バズボタンを押したユーザーを登録する
	public boolean insert(BuzzInfo buzz) {
		//sql文の準備
		String sql = 
			"INSERT INTO buzzbutton (posts_id,user_id) " +
		        "VALUES (?, ?)";
				
		try (PreparedStatement stmt = conn.prepareStatement(sql);){
		    stmt.setInt(1, buzz.postId());
		    stmt.setString(2,buzz.userId());
		   
				    
		    //sql文の実行と判定
		    int result =stmt.executeUpdate();
		    return result ==1;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	// バズを削除
	public boolean delete(int postId , String userId)  {
		String sql = "DELETE FROM buzzbutton WHERE posts_id = ? AND user_id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, postId);
			stmt.setString(2, userId);
		
			int result = stmt.executeUpdate();
	        return result ==1;  // 成功したら true を返す
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; // 失敗したら false を返す
	    }
	}
	//バズ数を表示する
	public int countBuzz(int postId) {
		String sql = "SELECT COUNT(*) FROM buzzbutton WHERE posts_id = ?";
		int count = 0;
		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, postId);
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
	            count = rs.getInt(1);
	        }
		}catch (SQLException e) {
	        e.printStackTrace();
		}
		return count;
		
	}
	
	//バズボタンを押しているかの確認
	public boolean exists(int postId, String userId) {
	    String sql = "SELECT 1 FROM buzzbutton WHERE posts_id = ? AND user_id = ?";

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, postId);
	        stmt.setString(2, userId);

	        ResultSet rs = stmt.executeQuery();
	        return rs.next();  // レコードがあれば true（すでにBuzz済み）
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	//バズした投稿一覧を表示するメソッド
	public List<BuzzInfo> findBuzzPost(String userId) {
	    List<BuzzInfo> buzzList = new ArrayList<>();

	    String sql = "SELECT buzz_id, posts_id, user_id FROM buzzbutton WHERE user_id = ?";

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, userId);

	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            int buzzId = rs.getInt("buzz_id");
	            int postId = rs.getInt("post_id");
	            String uId = rs.getString("user_id");

	            BuzzInfo buzz = new BuzzInfo(buzzId, postId, uId);
	            buzzList.add(buzz);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return buzzList;
	}
	
	public List<PostInfo> rankingComment(String shop) {
	    List<PostInfo> result = new ArrayList<>();

	    String sql = """
	        SELECT p.posts_id, p.user_id, p.comment, p.pictures, p.shop, p.postTime, COUNT(b.buzz_id) AS buzz_count
	        FROM posts p
	        LEFT JOIN buzzbutton b ON p.posts_id = b.post_id
	        WHERE p.shop = ?
	        GROUP BY p.posts_id, p.user_id, p.comment, p.pictures, p.shop, p.postTime
	        ORDER BY buzz_count DESC
	        LIMIT 3
	    """;

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, shop);

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
	                result.add(post);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return result;
	}

	public void close() {
	    try {
	        if (conn != null && !conn.isClosed()) {
	            conn.close();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
