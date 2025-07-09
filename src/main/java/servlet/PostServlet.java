package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.PostDAO;
import model.PostInfo;

/**
 * Servlet implementation class PostServlet
 */
@MultipartConfig
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

	    // 店舗選択だけの場合
	    String selectedShopName = request.getParameter("shopName");
	    if (selectedShopName != null) {
	        HttpSession session = request.getSession();
	        session.setAttribute("selectedShopForPost", selectedShopName);
	        response.sendRedirect(request.getContextPath() + "/PostServlet");
	        return; // ← ここで終了する
	    }

	    // 投稿処理（フォームからの入力を処理）
	    HttpSession session = request.getSession();
	    String shopName = (String) session.getAttribute("selectedShopForPost");
	    String userId = "takahashi"; // 本来はセッションから取得

	    String comment = request.getParameter("comment");
	    byte[] pictureBytes = null;

	    var part = request.getPart("picture");
	    if (part != null && part.getSize() > 0) {
	        pictureBytes = part.getInputStream().readAllBytes();
	    }

	    PostInfo post = new PostInfo(0, userId, comment, pictureBytes, shopName, null);
	    PostDAO dao = new PostDAO();
	    boolean result = dao.postInsert(post);

	    if (result) {
	        response.sendRedirect("MainServlet");
	    } else {
	        request.setAttribute("error", "投稿に失敗しました。");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/post.jsp");
	        dispatcher.forward(request, response);
	    }
	

		 
		//(String) session.getAttribute("userId");  
		/*
		// 選択した店舗名をパラメータから受け取る
		String selectedShopName = request.getParameter("shopName"); 
		// パラメータをセッションに入れる
		HttpSession session = request.getSession();
		session.setAttribute("selectedShopForPost", selectedShopName);
		response.sendRedirect(request.getContextPath() + "/PostServlet");
		*/
	}

}
