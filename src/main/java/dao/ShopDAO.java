package dao;

<<<<<<< HEAD
=======
import java.sql.*;
import java.util.*;
import model.ShopInfo;
import util.DBUtil;

public class ShopDAO {
>>>>>>> branch 'master' of https://github.com/kiyotaka0819/Buzz.git

<<<<<<< HEAD
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList; // 検索結果管理用
import java.util.List; // 検索結果管理用

import model.ShopInfo;

public class ShopDAO {
	private final String JDBC_URL = "jdbc:postgresql://172.31.98.112:5432/buzz";
	private final String DB_USER = "postgres";
	private final String DB_PASS = "root";
	
	public ShopDAO(){}
	
	// 店舗の検索用のメソッド
	public boolean shopSearch(ShopInfo shopInfo) {
		// JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした。");
		}
		// データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			// 検索用SELECT文を準備
			String searchSql ="SELECT name, url, adress, tel FROM SHOP WHERE name LIKE ?;";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// 検索キーワードをパラメーター等に保管してそれを引っ張る
			pStmt.setString(1, Seaech.getPrametter());

			// SERECT文を実行
			int result = pStmt.
			int result = pStmt.executeUpdate();
			
			return result == 1; // 成功ならtrue、失敗ならfalseを返す
			} catch (SQLException e) {
				e.printStackTrace();
				return false; // エラーが発生したら失敗
			}
	}
	
	
	// 店舗の登録用のメソッド
	public boolean shopRegister(ShopInfo shopInfo) {
		// JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした。");
		}
		// データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			//SELECT文を準備
			String sql = "INSERT INTO ACCOUNTS (NAME,URL,ADDRESS, TEL) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, shopInfo.getShopName());
			pStmt.setString(2, shopInfo.getShopAddress());
			pStmt.setString(3, shopInfo.getShopURL());
			pStmt.setString(4, shopInfo.getShopTEL());

			// INSERT文を実行
			int result = pStmt.executeUpdate();
			
			return result == 1; // 成功ならtrue、失敗ならfalseを返す
			} catch (SQLException e) {
				e.printStackTrace();
				return false; // エラーが発生したら失敗
			}
	}
	// 店舗の編集用メソッド
	public boolean shopEdit(ShopInfo shopInfo) {
		// JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした。");
		}
		// データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			//SELECT文を準備
			String sql = "INSERT INTO ACCOUNTS (NAME, ADDRESS,URL, TEL) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, shopInfo.getShopName());
			pStmt.setString(2, shopInfo.getShopAddress());
			pStmt.setString(3, shopInfo.getShopURL());
			pStmt.setString(4, shopInfo.getShopTEL());

			// INSERT文を実行
			int result = pStmt.executeUpdate();
			
			return result == 1; // 成功ならtrue、失敗ならfalseを返す
			} catch (SQLException e) {
				e.printStackTrace();
				return false; // エラーが発生したら失敗
			}
	}
	
	// 店舗の削除用メソッド
	public boolean shopDelete(ShopInfo shopInfo) {
		// JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした。");
		}
		// データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			//SELECT文を準備
			String sql = "INSERT INTO ACCOUNTS (NAME, ADDRESS,URL, TEL) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, shopInfo.getShopName());
			pStmt.setString(2, shopInfo.getShopAddress());
			pStmt.setString(3, shopInfo.getShopURL());
			pStmt.setString(4, shopInfo.getShopTEL());

			// INSERT文を実行
			int result = pStmt.executeUpdate();
			
			return result == 1; // 成功ならtrue、失敗ならfalseを返す
			} catch (SQLException e) {
				e.printStackTrace();
				return false; // エラーが発生したら失敗
			}
=======
	// 全件取得用メソッド
	public List<ShopInfo> findAll() throws Exception {
		List<ShopInfo> list = new ArrayList<>();
		String sql = "SELECT * FROM shop ORDER BY id";

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				ShopInfo shop = new ShopInfo(
						rs.getString("name"),
						rs.getString("URL"),
						rs.getString("address"),
						rs.getString("TEL")
						);
				list.add(shop);
			}
		}
		return list;
	}

	// 店舗登録用メソッド
	public void shopRegister(ShopInfo shop) throws Exception {
		String sql = "INSERT INTO shop (name, address, category) VALUES (?, ?, ?)";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, shop.getName());
			ps.setString(2, shop.getAddress());
			ps.setString(3, shop.getURL());
			ps.setString(4, shop.getTEL());
			ps.executeUpdate();
		}
	}

	// 店舗更新用メソッド
	public void shopEdit(ShopInfo shop) throws Exception {
		String sql = "UPDATE shop SET name = ?, address = ?, category = ? WHERE id = ?";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, shop.getName());
			ps.setString(2, shop.getAddress());
			ps.setString(3, shop.getURL());
			ps.setString(4, shop.getTEL());
			ps.executeUpdate();
		}
	}

	// 店舗削除用メソッド
	public void shopDelete(int id) throws Exception {
		String sql = "DELETE FROM shop WHERE id = ?";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);
			ps.executeUpdate();
		}
>>>>>>> branch 'master' of https://github.com/kiyotaka0819/Buzz.git
	}
}
