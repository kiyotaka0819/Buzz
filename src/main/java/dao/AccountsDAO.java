package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.Login;

public class AccountsDAO {
	private final String JDBC_URL = "jdbc:postgresql://172.31.98.112:5432/buzz";
    private final String DB_USER = "postgres";
    private final String DB_PASS = "root";

    public Account findByLogin(Login login) {
        Account account = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "select user_id, pass, username, profile from users where user_id = ? and pass = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, login.getUserId());
            pStmt.setString(2, login.getPass());

            ResultSet rs = pStmt.executeQuery();

            if (rs.next()) {
                String userId = rs.getString("user_id");
                String pass = rs.getString("pass");
                String name = rs.getString("username");
                String profile = rs.getString("profile");
                account = new Account(userId, pass, name, profile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return account;
    }
    
    public boolean createAccount(Account account) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            // INSERT文を準備
            String sql = "insert into users (user_id, pass, username, profile) values (?, ?, ?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, account.getUserId());
            pStmt.setString(2, account.getPass());
            pStmt.setString(3, account.getName());
            pStmt.setString(4, account.getProfile());

            // INSERT文を実行
            int result = pStmt.executeUpdate();

            // 1行以上挿入されたら成功
            return result == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean userIdSearch(String userId) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "select count(*) from users where user_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userId);

            ResultSet rs = pStmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}