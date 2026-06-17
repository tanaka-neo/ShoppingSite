package jp.co.aforce.servlet;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Users;
import tool.Action;

public class RegisterConfirmAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String memberId = request.getParameter("memberId");
        String password = request.getParameter("password");
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String address = request.getParameter("address");
        String mailAddress = request.getParameter("mailAddress");

        // 入力された値をビーンズに詰める
        Users inputUser = new Users();
        inputUser.setMemberId(memberId);
        inputUser.setPassword(password);
        inputUser.setLastName(lastName);
        inputUser.setFirstName(firstName);
        inputUser.setAddress(address);
        inputUser.setMailAddress(mailAddress);

        List<String> errorList = new ArrayList<>();

        // 1. 必須（未入力）チェック
        if (memberId == null || memberId.isBlank() || password == null || password.isBlank() ||
            lastName == null || lastName.isBlank() || firstName == null || firstName.isBlank() ||
            address == null || address.isBlank() || mailAddress == null || mailAddress.isBlank()) {
            errorList.add("未入力の項目があります。");
        }

        // 必須チェックがOKなら詳細チェック
        if (errorList.isEmpty()) {
            // 2. 形式チェック（半角英数字）
            if (!memberId.matches("^[a-zA-Z0-9]+$")) {
                errorList.add("会員IDは半角英数字で入力してください。");
            }
            if (!password.matches("^[a-zA-Z0-9]+$")) {
                errorList.add("パスワードは半角英数字で入力してください。");
            }
            
            // 3.文字数制限チェック
            if (memberId.length() > 10) {
                errorList.add("会員IDは10文字以内で入力してください。");
            }
            if (password.length() < 8 || password.length() > 32) {
                errorList.add("パスワードは8文字以上32文字以内で入力してください。");
            }
            if (lastName.length() > 32 || firstName.length() > 32) {
                errorList.add("お名前（名字・名前）はそれぞれ32文字以内で入力してください。");
            }
            if (address.length() > 128) {
                errorList.add("住所は128文字以内で入力してください。");
            }
            // メールアドレスの128文字制限チェックを追加
            if (mailAddress.length() > 128) {
                errorList.add("メールアドレスは128文字以内で入力してください。");
            }
            
            // 4. メールアドレス形式チェック（アットマークなどの構文）
            String emailPattern = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
            if (!mailAddress.matches(emailPattern)) {
                errorList.add("メールアドレスの形式が正しくありません。");
            }
        }

        // エラーがある場合は新規登録画面に入力内容を持たせて戻す
        if (!errorList.isEmpty()) {
            request.setAttribute("errors", errorList);
            request.setAttribute("formUser", inputUser); 
            return "/views/register.jsp"; 
        }

        // 正常なら登録確認画面へ進む
        request.getSession().setAttribute("registerUser", inputUser);
        return "/views/register-confirm.jsp"; 
    }
}