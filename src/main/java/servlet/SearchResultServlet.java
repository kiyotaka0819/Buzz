package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SearchResultServlet")
public class SearchResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 検索ワードを変数に代入
		String SearchWord = request.getParameter("searchWord");
		
		// 検索ワードをJSPに渡すためにリクエストスコープに設定
		request.setAttribute("searchWord", SearchWord);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/mainMenu.jsp");
		dispatcher.forward(request, response);
	}

}
