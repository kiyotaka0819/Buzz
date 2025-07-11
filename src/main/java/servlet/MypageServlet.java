package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.AccountsDAO;
import dao.PostDAO;
import model.Account;
import model.PostInfo;


@WebServlet("/MypageServlet")
public class MypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// セッションからログイン中のユーザーIDを取得
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        if (userId == null || userId.isEmpty()) {
            response.sendRedirect("login.jsp"); // 未ログインならログインページへ
            return;
        }

        // DAOでユーザー情報を取得
        AccountsDAO userDao = new AccountsDAO();
        Account user = null;
		try {
			user = userDao.findByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}

        // DAOでユーザーの投稿一覧を取得
        PostDAO postDao = new PostDAO();
        List<PostInfo> postList = postDao.findPostsByUserId(userId);

        // リクエスト属性にセット
        request.setAttribute("user", user);
        request.setAttribute("postList", postList);
        
        //check
        System.out.println("userId = " + userId);
        System.out.println("postList size = " + (postList != null ? postList.size() : "null"));

        // JSPへフォワード
        request.getRequestDispatcher("WEB-INF/jsp/mypage.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
