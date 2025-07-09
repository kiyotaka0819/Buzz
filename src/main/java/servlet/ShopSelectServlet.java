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

import dao.ShopDAO;
import model.ShopInfo;

@WebServlet("/ShopSelectServlet")
public class ShopSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String searchName = request.getParameter("searchName");
		List<ShopInfo> shopList = new ArrayList<>(); // 検索結果を入れておく変数
		ShopDAO shopDAO = new ShopDAO(); // DAOのインスタンス生成
		
		try {
			if (searchName != null && !searchName.trim().isEmpty()) {
				// 検索キーワードがある場合、部分一致検索を実行
				List<String> keywords = new ArrayList<>();
				keywords.add(searchName);
				shopList = shopDAO.searchShopsByName(keywords); // DAOの検索メソッドで検索
			} else {
				// 検索キーワードがない場合、全件表示
				shopList = shopDAO.findAll(); // 全件取得メソッドを呼び出し
			}
			// 検索結果をリクエストスコープに保存する
			request.setAttribute("shopList", shopList);
			// 検索キーワードもJSPで表示するためにリクエストスコープに保存する
			request.setAttribute("searchKeyword", searchName != null ? searchName : ""); // nullの場合は空文字にする

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "店舗情報の取得中にエラーが発生しました。");
			// エラー時にはエラーページに遷移させる予定
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
			return; // エラーページにフォワードしたら処理を終える
		}

		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/shopSelect.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String newShopName = request.getParameter("newShopName");
		String newShopAddress = request.getParameter("newShopAddress");	
		String newShopURL = request.getParameter("newShopURL");
		String newShopTEL = request.getParameter("newShopTEL");

		// 各種入力値のバリデーション (空文字チェック、隙間埋め)
		if (newShopName != null && !newShopName.trim().isEmpty() &&
				newShopAddress != null && !newShopAddress.trim().isEmpty() &&
				newShopURL != null && !newShopURL.trim().isEmpty() &&
				newShopTEL != null && !newShopTEL.trim().isEmpty()) {

			ShopInfo newShop = new ShopInfo(newShopName, newShopURL, newShopAddress, newShopTEL);
			ShopDAO shopDAO = new ShopDAO();

			try {
				shopDAO.shopRegister(newShop); // 店舗登録メソッド
				request.setAttribute("message", "店舗「" + newShopName + "」が登録されました！");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "店舗の登録中にエラーが発生しました。");
			}
		} else {
			request.setAttribute("errorMessage", "全ての店舗情報を入力してください。");
		}
		doGet(request, response); // 登録後に再度検索、表示を実行
	}
}
