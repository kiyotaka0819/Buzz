package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ShopSelectActionServlet")
public class ShopSelectActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String shopName = request.getParameter("shopName");
        HttpSession session = request.getSession();
		
		// 選択した店舗名をセッションに保存
        session.setAttribute("selectedShopForPost", shopName);

        // 編集か投稿かを示すセッション情報を取得
        String redirectBackTo = (String) session.getAttribute("redirectBackTo");
        
        // セッションから取得して文字列に変換
        String editingPostId = String.valueOf(session.getAttribute("editingPostId")); 
        
        // 使い終わったセッションは削除する。
        session.removeAttribute("editingPostId");
        // セッション情報を削除(redirectBackToだけにする)
        session.removeAttribute("redirectBackTo");

        if ("PostEditServlet".equals(redirectBackTo)) {
            // 編集画面から来た場合、編集画面にリダイレクト
            response.sendRedirect(request.getContextPath() + "/PostEditServlet?postId=" + editingPostId);
        } else {
            // 通常の投稿画面から来た場合、投稿画面にリダイレクト
            response.sendRedirect(request.getContextPath() + "/PostServlet");
        }
    }
}