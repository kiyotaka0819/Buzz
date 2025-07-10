package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Account;

@WebServlet("/UserEditConfirmServlet")
public class UserEditConfirmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            res.sendRedirect("LoginServlet");
            return;
        }

        String userId = (String) session.getAttribute("userId");
        String pass = req.getParameter("pass");
        String name = req.getParameter("name");
        String profile = req.getParameter("profile");

        List<String> errorMsgs = new ArrayList<>();

        // パスワードのチェック（空欄は「変更なし」として許容）
        if (pass != null && !pass.isEmpty()) {
            if (pass.length() < 8) {
                errorMsgs.add("パスワードは8文字以上で入力してください。");
            }
            if (pass.length() > 40) {
                errorMsgs.add("パスワードは40文字以下で入力してください。");
            }
            String passPattern = "^[A-Za-z0-9!-/:-@\\[-`{-~]+$";
            String regex = "^(?=.*[A-Za-z])(?=.*\\d).*$";

            if (!Pattern.matches(passPattern, pass)) {
                errorMsgs.add("パスワードには半角英数字と一部の記号（!-/:-@[-`{-~など）のみ使用できます。");
            }
            if (!Pattern.matches(regex, pass)) {
                errorMsgs.add("パスワードには半角英字および半角数字を最低一つ含めてください。");
            }
        }
        
        // ユーザー名のチェック
        if (name == null || name.isEmpty()) {
            errorMsgs.add("ユーザー名が入力されていません。");
        } else if (name.length() > 40) {
            errorMsgs.add("ユーザー名は40文字以内で入力してください。");
        }

        // エラーがある場合、入力画面に戻す
        if (!errorMsgs.isEmpty()) {
            req.setAttribute("errorMsgs", errorMsgs);
            req.setAttribute("userId", userId);
            req.setAttribute("pass", errorMsgs)
            req.setAttribute("name", name);
            req.setAttribute("profile", profile);
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/userEdit.jsp");
            dispatcher.forward(req, res);
            return;
        }

        // Accountオブジェクトにまとめてセッションへ保存（確認・登録用）
        Account account = new Account(userId, pass, name, profile);
        session.setAttribute("editAccount", account);

        // 確認画面にフォワード
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/userEditConfirm.jsp");
        dispatcher.forward(req, res);
    }
}
