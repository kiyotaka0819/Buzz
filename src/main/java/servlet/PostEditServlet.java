package servlet;

import java.io.IOException;
import java.io.InputStream;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import dao.PostDAO;
import model.PostInfo;

/**
 * Servlet implementation class PostEditServlet
 */
@WebServlet("/PostEditServlet")
public class PostEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String loginUserId = (String) session.getAttribute("user_id");

		// 投稿IDを受け取る
		String postIdStr = "5";//request.getParameter("post_id");
		if (postIdStr == null || postIdStr.isEmpty()) {
			response.sendRedirect("MypageServlet"); // 不正アクセス防止
			return;
		}

		int postId = Integer.parseInt(postIdStr);

		// DAOで投稿を取得
		PostDAO dao = new PostDAO();
		PostInfo post = dao.findById(postId);

		// 投稿が存在しない、または自分の投稿でない場合はリダイレクト
		if (post == null || !loginUserId.equals(post.userId())) {
			response.sendRedirect("MypageServlet");
			return;
		}

		// 投稿情報をリクエストスコープに保存
		request.setAttribute("post", post);
		request.setAttribute("hasPicture", post.pic() != null);

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/postEdit.jsp");
		dispatcher.forward(request, response);
	}
    

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    HttpSession session = request.getSession();

	    // 投稿ID（hiddenから送られてくる）
	    int postId = Integer.parseInt(request.getParameter("posts_id"));

	    // コメントと店舗名
	    String comment = request.getParameter("comment");
	    String shopName = request.getParameter("selectedShopForPost");

	    // 画像削除のチェック
	    String deletePicture = request.getParameter("deletePicture");
	    boolean shouldDeletePic = "true".equals(deletePicture);

	    // 画像ファイルの取得
	    Part picturePart = request.getPart("pictures");
	    byte[] pictureData = null;

	    // 画像を削除しない && 新しく画像がアップロードされた場合だけ読み込む
	    if (!shouldDeletePic && picturePart != null && picturePart.getSize() > 0) {
	        try (InputStream is = picturePart.getInputStream()) {
	            pictureData = is.readAllBytes();
	        }
	    }

	    // 削除が選択されたら画像データをnullにして上書き（PostDAOの処理に合わせる）
	    if (shouldDeletePic) {
	        pictureData = null;
	    }

	    // セッションから user_id を取得（ログイン済み前提）
	    String userId = (String) session.getAttribute("user_id");

	    // PostInfo オブジェクトに詰める
	    PostInfo postInfo = new PostInfo(postId, userId, comment, pictureData, shopName, null); // postTime は不要

	    // DAOで更新
	    PostDAO dao = new PostDAO();
	    boolean result = dao.postEdit(postInfo, postId);

	    if (result) {
	        // 成功 → マイページにリダイレクト
	        response.sendRedirect("MypageServlet");
	    } else {
	        // 失敗 → エラーメッセージをセットして再表示
	        request.setAttribute("errorMessage", "投稿の更新に失敗しました");
	        request.getRequestDispatcher("/postEdit.jsp").forward(request, response);
	    }
	}
}
