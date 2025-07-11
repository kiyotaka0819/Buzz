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
		String loginUserId = (String) session.getAttribute("userId");

		// 投稿IDを受け取る
		String postIdStr = request.getParameter("postId");
		//check
		System.out.println("postsId = " + postIdStr);
		if (postIdStr == null || postIdStr.isEmpty()) {
			response.sendRedirect("MypageServlet"); // 不正アクセス防止
			return;
		}

		int postId = Integer.parseInt(postIdStr);

		// DAOで投稿を取得
		PostDAO dao = new PostDAO();
		PostInfo post = dao.findById(postId);

		//check
				System.out.println("postsId = " + postIdStr);
		// 自分の投稿でない場合はリダイレクト
		if (!loginUserId.equals(post.userId()) &&loginUserId != null ) {
			response.sendRedirect("MypageServlet");
			return;
		}
		System.out.println("post内容 " + post);
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
	    String postIdStr = request.getParameter("postId");
	    if (postIdStr == null || postIdStr.isEmpty()) {
	        // 適切なエラーハンドリング（リダイレクトやエラーメッセージ表示など）
	    	request.setAttribute("errorMessage", "つぶやきIDが指定されていません");
		    request.getRequestDispatcher("/WEB-INF/jsp/postEdit.jsp").forward(request, response);
		    return;
	    }
	    int postId = Integer.parseInt(postIdStr);
	    //check
	    System.out.println("doPostのpostId: " + postId);

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
	    String userId = (String) session.getAttribute("userId");

	    // PostInfo オブジェクトに詰める
	    PostInfo postInfo = new PostInfo(postId, userId, comment, pictureData, shopName, null); // postTime は不要

	    // DAOで更新
	    PostDAO dao = new PostDAO();
	    boolean result = dao.postEdit(postInfo, postId);
	    //check
	    System.out.println("postInfo:" + postInfo );
	    if (result) {
	        // 成功 → マイページにリダイレクト
	        response.sendRedirect("MypageServlet");
	    } else {
	    	//check
	    	System.out.println("更新失敗: postId = " + postId + ", userId = " + userId);
	    	// 再度DBから投稿情報を取得（失敗しても少なくともnullでなくなる）
	        PostInfo originalPost = dao.findById(postId);
	        // 失敗 → エラーメッセージをセットして再表示
	    	request.setAttribute("post", originalPost);
	        request.setAttribute("errorMessage", "投稿の更新に失敗しました");
	        request.getRequestDispatcher("/WEB-INF/jsp/postEdit.jsp").forward(request, response);
	    }
	}
}
