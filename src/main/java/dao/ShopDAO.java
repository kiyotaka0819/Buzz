package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList; // 検索結果管理用
import java.util.List; // 検索結果管理用

import model.ShopInfo;
import util.DBUtil;

public class ShopDAO {

	// 全件取得用メソッド
	public List<ShopInfo> findAll() throws Exception {
		List<ShopInfo> list = new ArrayList<>();
		String sql = "SELECT * FROM shop ORDER BY NAME";

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

	// 店舗情報検索用メソッド
	
	/* 使い方
	 * shopDAO.searchShopsByName(keywords);
	 * keywordsはList<String>に代入するので
	 * keywords.add(検索キーワード);
	 * このように入力する。
	 */
	
	public List<ShopInfo> searchShopsByName(List<String> keywords) throws Exception {
		List<ShopInfo> list = new ArrayList<>();

		// キーワードが空の場合は空で返す
		if (keywords == null || keywords.isEmpty()) {
			return list;
		}

		StringBuilder sqlBuilder = new StringBuilder("SELECT name, URL, address, TEL FROM SHOP WHERE ");
		for (int i = 0; i < keywords.size(); i++) {
			if (i > 0) {
				sqlBuilder.append(" OR "); // 2つ以上のキーワードの場合『OR』を追加
			}
			sqlBuilder.append("LOWER(name) LIKE ?");
		}

		String sql = sqlBuilder.toString(); // SQL文を保管する場所
		
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			// (?)に検索ワードを入力
			for (int i = 0; i < keywords.size(); i++) { // Listのsize文ループ
				ps.setString(i + 1, "%" + keywords.get(i).toLowerCase() + "%"); // 部分一致検索
			}

			try (ResultSet rs = ps.executeQuery()) {
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
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}

	// 店舗登録用メソッド
	public void shopRegister(ShopInfo shop) throws Exception {
		String sql = "INSERT INTO shop (name,url, address, tel) VALUES (?, ?, ?,?)";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, shop.getShopName());
			ps.setString(2, shop.getShopURL());
			ps.setString(3, shop.getShopAddress());
			ps.setString(4, shop.getShopTEL());
			ps.executeUpdate();
		}
	}

	// 店舗更新用メソッド
	public void shopEdit(ShopInfo shopInfo) throws Exception {
		String sql = "UPDATE shop SET name = ?, url = ?, address = ? WHERE NAME = ?";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, shopInfo.getShopName());
			ps.setString(2, shopInfo.getShopAddress());
			ps.setString(3, shopInfo.getShopURL());
			ps.setString(4, shopInfo.getShopTEL());
			ps.executeUpdate();
		}
	}

	// 店舗削除用メソッド
	public void shopDelete(ShopInfo shopInfo) throws Exception {
		String sql = "DELETE FROM shop WHERE NAME = ?";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, shopInfo.getShopName());
			ps.executeUpdate();
		}
	}
}
