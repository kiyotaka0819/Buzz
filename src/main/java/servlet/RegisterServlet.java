package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.AccountsDAO;
import model.Account;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // GETリクエスト時は、ユーザー登録画面へフォワード
        // リクエストスコープの属性をクリア
        request.removeAttribute("userId");
        request.removeAttribute("name");
        request.removeAttribute("profile");
        request.removeAttribute("errorMsgs"); // エラーメッセージもクリア

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/register.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        // 確認画面からの「修正」リクエストの場合
        if ("edit".equals(action)) {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("account");

            if (account != null) {
                // セッションから取得したアカウント情報をリクエストスコープにコピー
                request.setAttribute("userId", account.getUserId());
                request.setAttribute("name", account.getName());
                request.setAttribute("profile", account.getProfile()); // profileをセット
                // パスワードは再入力させるためセットしない
            } else {
                // セッション切れの場合などのエラー処理
                List<String> errorMsgs = new ArrayList<>();
                errorMsgs.add("セッション情報が無効です。再度入力してください。");
                request.setAttribute("errorMsgs", errorMsgs);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/register.jsp"); // register.jsp にフォワード
            dispatcher.forward(request, response);

        // 入力画面からの「入力内容を確認」リクエストの場合
        } else {
            String userId = request.getParameter("userId");
            String pass = request.getParameter("pass");
            String name = request.getParameter("name");
            String profile = request.getParameter("profile");

            // 入力値の保持 (エラー時や確認画面への表示用)
            request.setAttribute("userId", userId);
            request.setAttribute("name", name);
            request.setAttribute("profile", profile);

            List<String> errorMsgs = new ArrayList<>();
            AccountsDAO dao = new AccountsDAO();

            if (userId == null || userId.isEmpty()) {
                errorMsgs.add("ユーザーIDが入力されていません。");
            } else if (dao.userIdSearch(userId)) {
                errorMsgs.add("そのユーザーIDは既に使用されています。");
            }
            if (pass == null || pass.isEmpty()) {
                errorMsgs.add("パスワードが入力されていません。");
            }
            if (name == null || name.isEmpty()) {
                errorMsgs.add("名前が入力されていません。");
            }
            if (!errorMsgs.isEmpty()) {
                request.setAttribute("errorMsgs", errorMsgs);
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/register.jsp"); // register.jsp にフォワード
                dispatcher.forward(request, response);
                return;
            }

            Account account = new Account(userId, pass, name, profile);
            HttpSession session = request.getSession();
            session.setAttribute("account", account); // 確認画面へ渡すためのセッション保存

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerConfirm.jsp");
            dispatcher.forward(request, response);
        }
    }
}