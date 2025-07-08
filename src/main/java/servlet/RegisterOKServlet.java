package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/RegisterOKServlet")
public class RegisterOKServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId"); // 登録成功時にセッションに保存したuserIdを取得

        if (userId == null) {
            // userIdがセッションにない場合は、不正なアクセスまたはセッション切れとしてログイン画面などにリダイレクト
            response.sendRedirect("LoginServlet"); // 例: ログインサーブレットへ
            return;
        }

        // 登録完了画面へフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/signUpOK.jsp");
        dispatcher.forward(request, response);
    }
}