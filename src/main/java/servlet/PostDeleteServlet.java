package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.PostDAO;

/**
 * Servlet implementation class PostDeleteServlet
 */
@WebServlet("/PostDeleteServlet")
public class PostDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String loginUserId = (String) session.getAttribute("userId");
		// もしログインしていなければログイン画面へ強制送還
				if (loginUserId == null || loginUserId.isEmpty()) {
					response.sendRedirect("LoginServlet");
					return;
				}

		// 投稿IDを受け取る
		String postIdStr = request.getParameter("postId");
		//check
		System.out.println("postId:"+ postIdStr);
		
		if (postIdStr == null || postIdStr.isEmpty()) {
			//response.sendRedirect("MypageServlet"); // 不正アクセス防止
			request.getRequestDispatcher("WEB-INF/jsp/mypage.jsp").forward(request, response);
			return;
		}
		
		int postId = Integer.parseInt(postIdStr);
		

		// DAOで投稿を削除
		PostDAO dao = new PostDAO();
		boolean result = dao.postDelete(postId);
		
		String redirect = request.getParameter("redirect");
		
		
		if(!redirect.equals("MypageServlet")) {
			redirect = "MainMenuServlet";
		}
		
		if(result) {
			//check
			System.out.println("削除成功");
			response.sendRedirect(redirect); // 削除出来たらマイページにもどる。
		}else {
			//check
			System.out.println("削除失敗");
			request.setAttribute("errorMessage", "削除に失敗しました。もう一度お試しください。");
		    request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
