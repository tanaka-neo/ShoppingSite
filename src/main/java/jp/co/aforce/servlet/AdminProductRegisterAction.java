package jp.co.aforce.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import tool.Action;

public class AdminProductRegisterAction extends Action {

    @Override
    public String execute(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        // 管理者が新規商品情報を入力するJSP画面へフォワード
        return "/views/admin-product-register.jsp";
    }
}