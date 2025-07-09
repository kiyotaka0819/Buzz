package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ShopInfo;
import util.DBUtil;

public class ShopDAOTEST {

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