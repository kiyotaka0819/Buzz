package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.ShopInfo;
import util.DBUtil;

public class ShopDAOTEST {

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
	}
}
