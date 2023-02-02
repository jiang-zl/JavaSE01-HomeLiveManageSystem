package controller;

import model.UserInfo;

import java.util.ArrayList;

public class UserAccess {
    private static final String USER_TABLE_NAME = "src/model/db/userInfo";

    public static UserInfo checkUserAccess(String username, String password) {
        if (null == username || null == password || username.length() <= 0 || password.length() <= 0) {
            return null;
        }
        ArrayList<String> userInfoContent = new ArrayList<>();
        FileOperator.readFileWithException(userInfoContent, USER_TABLE_NAME, "User information reading failed.");
        for (String curUser : userInfoContent) {
            String[] items = curUser.split("\t");
            if (username.equals(items[0])) {
                if (password.equals(items[1])) {
                    return new UserInfo(username, password);
                }
                else {
                    return null;
                }
            }
        }
        return null;
    }
}
