package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.AccountsDAO;
import model.Account;
import model.Login;

@WebServlet("/UserEditServlet")
public class UserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("loginUser");

		String userId = "";
		String pass = ""; // 通常、パスワードは表示しないか、別途処理します
		String userName = "";
		String profile = "";

		if (login != null) {
			AccountsDAO accountsDAO = new AccountsDAO();
			Account userAccount = accountsDAO.findByLogin(login);

			if (userAccount != null) {
				userId = userAccount.getUserId();
				pass = userAccount.getPass(); // パスワードを表示する場合はここに設定
				userName = userAccount.getName();
				profile = userAccount.getProfile();
			}
		}

		// JSPに渡すデータをリクエストスコープに個別に設定
		request.setAttribute("userId", userId);
		request.setAttribute("pass", pass);
		request.setAttribute("userName", userName);
		request.setAttribute("profile", profile);

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userEdit.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}