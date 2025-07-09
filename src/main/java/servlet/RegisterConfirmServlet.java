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

@WebServlet("/RegisterConfirmServlet")
public class RegisterConfirmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        // 確認画面からの「登録」リクエストの場合のみ処理
        if ("confirm".equals(action)) {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("account");

            if (account == null) {
                List<String> errorMsgs = new ArrayList<>();
                errorMsgs.add("セッション情報が無効です。再度入力してください。");
                request.setAttribute("errorMsgs", errorMsgs);
                // セッション切れの場合も入力フォームにデータを戻す
                if (request.getParameter("userId") != null) {
                     request.setAttribute("userId", request.getParameter("userId"));
                     request.setAttribute("name", request.getParameter("name"));
                     request.setAttribute("profile", request.getParameter("profile")); // profileをセット
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/register.jsp"); // register.jsp にフォワード
                dispatcher.forward(request, response);
                return;
            }

            AccountsDAO dao = new AccountsDAO();
            boolean result = dao.createAccount(account);

            if (result) {
                session.setAttribute("userId", account.getUserId());
                session.removeAttribute("account"); // 登録成功後にセッションからAccount情報を削除
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerOK.jsp"); // 登録完了画面へフォワード
                dispatcher.forward(request, response);
            } else {
                List<String> errorMsgs = new ArrayList<>();
                errorMsgs.add("登録処理中に予期せぬエラーが発生しました。");
                request.setAttribute("errorMsgs", errorMsgs);
                // 登録失敗時も入力値をフォームに戻す
                request.setAttribute("userId", account.getUserId());
                request.setAttribute("name", account.getName());
                request.setAttribute("profile", account.getProfile());
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/register.jsp"); // register.jsp にフォワード
                dispatcher.forward(request, response);
            }
        } else {
            // "confirm" アクション以外で直接 RegisterConfirmServlet にアクセスされた場合の処理
            // 例: エラーページにリダイレクト、または入力フォームに戻す
            response.sendRedirect("RegisterServlet"); // RegisterServlet にリダイレクト
        }
    }
}