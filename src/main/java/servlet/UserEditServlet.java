package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.AccountsDAO;
import model.Account;

@WebServlet("/UserEditServlet")
public class UserEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String userId = (session != null) ? (String) session.getAttribute("userId") : null;

        if (userId == null) {
            res.sendRedirect("LoginServlet");
            return;
        }

        try {
            // セッションに編集内容があれば優先的に利用する
            Account editAccount = (session != null) ? (Account) session.getAttribute("editAccount") : null;

            if (editAccount != null) {
                // セッションから編集情報を取り出して画面にセット
                req.setAttribute("userId", editAccount.getUserId());
                req.setAttribute("name", editAccount.getName());
                req.setAttribute("profile", editAccount.getProfile());
                req.setAttribute("pass", editAccount.getPass()); // パスワードは空かもしれません

                // セッションから編集情報は削除（戻る時のみ使いたいので）
                session.removeAttribute("editAccount");
            } else {
                // 編集情報がなければDBから取得
                AccountsDAO dao = new AccountsDAO();
                Account account = dao.findByUserId(userId);

                if (account == null) {
                    res.sendRedirect("LoginServlet");
                    return;
                }

            req.setAttribute("userId", account.getUserId());
            req.setAttribute("pass","");
            req.setAttribute("name", account.getName());
            req.setAttribute("profile", account.getProfile());
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMsgs", java.util.List.of("プロフィールの取得中にエラーが発生しました。"));
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/userEdit.jsp");
        dispatcher.forward(req, res);
    }
}
