package model;

import dao.AccountsDAO;

public class RegisterLogic {
    public boolean execute(Account account) {
        AccountsDAO dao = new AccountsDAO();
        return dao.createAccount(account);
    }
}