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
            AccountsDAO dao = new AccountsDAO();
            Account account = dao.findByUserId(userId);

            if (account == null) {
                res.sendRedirect("LoginServlet");
                return;
            }

            req.setAttribute("userId", account.getUserId());
            req.setAttribute("pass",)
            req.setAttribute("name", account.getName());
            req.setAttribute("profile", account.getProfile());

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMsgs", java.util.List.of("プロフィールの取得中にエラーが発生しました。"));
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/userEdit.jsp");
        dispatcher.forward(req, res);
    }
}
