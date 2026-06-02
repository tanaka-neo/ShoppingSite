package tool;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "*.action" })
public class FrontController extends HttpServlet {

	public void doPost(
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		try {

			//jp/cp/aforce/servlet/Login.actionなど、を取得し/を.に変換Aを.aに変換jp.co.aforce.servlet.LoginActionやLogoutActionになり、URLからクラス名にしている
			String path = request.getServletPath().substring(1);
			String name = path.replace(".a", "A").replace('/', '.');

			//Action action =
			//new LoginAction();などと同じ状態。ActionオブジェクトLoginActionやLogoutActionなど、、を生成
			Action action = (Action) Class.forName(name).getDeclaredConstructor().newInstance();
			//executeを実行、戻り値として遷移先JSPを取得
			String url = action.execute(request, response);
			//戻り値を取得し、JSPへforword
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace(out);
		}
	}

	public void doGet(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}