package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.PostDAO;
import model.PostInfo;

@WebServlet("/SearchResultServlet")
public class SearchResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 検索ワードを変数に代入
		String searchWord = request.getParameter("searchWord");
		
		// 検索ワードをJSPに渡すためにリクエストスコープに設定
		request.setAttribute("searchWord", searchWord);
		
		// PostDAOのインスタンス生成
		PostDAO postDAO = new PostDAO();
		
		// Listの置き場を作っておく
		List<PostInfo> postResults = null;

		if (searchWord != null && !searchWord.isEmpty()) {
			postResults = postDAO.findByKeyword(searchWord); // つぶやきを検索
		} else {
			// 検索ワードが空の場合は、全件取得する
			postResults = postDAO.postFindAll();
		}

		// 検索結果をリクエストスコープに設定
		request.setAttribute("postResults", postResults);

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/searchResult.jsp");
		dispatcher.forward(request, response);
	}
}