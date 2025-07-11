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

@WebServlet("/UserEditOKServlet")
public class UserEditOKServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);
        String userId = (session != null) ? (String) session.getAttribute("userId") : null;
        String action = req.getParameter("action");

        // 無効なアクセスなら編集画面にリダイレクト
        if (session == null || userId == null || !"confirm".equals(action)) {
            res.sendRedirect("UserEditServlet");
            return;
        }

        // セッションから Account オブジェクトを取得
        Account editAccount = (Account) session.getAttribute("editAccount");

        if (editAccount == null) {
            res.sendRedirect("UserEditServlet");
            return;
        }

        String name = editAccount.name();
        String profile = editAccount.profile();
        String pass = editAccount.pass();

        // パスワードが空欄なら変更なし → null を渡す
        if (pass == null || pass.isEmpty()) {
            pass = null;
        }

        // 更新用Accountオブジェクト作成
        Account account = new Account(userId, pass, name, profile);

        boolean updated = false;
        try {
            AccountsDAO dao = new AccountsDAO();
            updated = dao.editProfile(account);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (updated) {
            // 更新成功後、セッションの一時データを削除
            session.removeAttribute("editAccount");
            session.setAttribute("profile", profile); // 最新プロフィールをセッションに保存

            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/userEditOK.jsp");
            dispatcher.forward(req, res);
        } else {
            // 更新失敗
            List<String> errorMsgs = new ArrayList<>();
            errorMsgs.add("プロフィールの更新中にエラーが発生しました。");

            req.setAttribute("errorMsgs", errorMsgs);
            req.setAttribute("name", name);
            req.setAttribute("profile", profile);

            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/userEdit.jsp");
            dispatcher.forward(req, res);
        }
    }
}
