package model;

import controller.FileOperator;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {
    private static final String USER_TABLE_NAME = "src/model/db/userInfo";

    private String username;
    private String password;

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void save() {
        ArrayList<String> readContent = new ArrayList<>();
        FileOperator.readFileWithException(readContent, USER_TABLE_NAME, "User information reading failed.");
        boolean isExisted = false;
        for (int i = 0; i < readContent.size(); ++i) {
            String[] items = readContent.get(i).split("\t");
            if (username.equals(items[0])) {
                isExisted = true;
                items[1] = password;
                items[2] = String.valueOf(username.hashCode());
                String modifyUserInfo = String.join(items[0], "\t", items[1], "\t", items[2]);
                readContent.set(i, modifyUserInfo);
            }
        }
        if (isExisted) {
            FileOperator.writeFileWithException(readContent, USER_TABLE_NAME, "User information writing failed.", false);
        }
        else {
            String addInfo = username + "\t" + password + "\t" + username.hashCode();
            ArrayList<String> infoArray = new ArrayList<>(List.of(addInfo));
            FileOperator.writeFileWithException(infoArray, USER_TABLE_NAME, "User information writing failed.", true);
        }
    }
}
