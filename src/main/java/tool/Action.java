package tool;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class Action {
	//	Actionを継承するクラスはexecute（）を作りなさいというルールを定義
	public abstract String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception;

}
