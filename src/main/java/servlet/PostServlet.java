package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class PostServlet
 */
@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); // セッションを取得(選択店舗取得)

		String clearShopParam = request.getParameter("clearShop");
		if ("true".equals(clearShopParam)) {
			session.removeAttribute("selectedShopForPost"); // セッションから店舗名を削除
		}

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/post.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 選択した店舗名をパラメータから受け取る
		String selectedShopName = request.getParameter("shopName"); 
		// パラメータをセッションに入れる
		HttpSession session = request.getSession();
		session.setAttribute("selectedShopForPost", selectedShopName);
		response.sendRedirect(request.getContextPath() + "/PostServlet");
	}

}
