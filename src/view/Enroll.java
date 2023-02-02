package view;

import model.UserInfo;

import java.util.Scanner;

public class Enroll {
    public static void enrollUserInfo() {
        Scanner scannerStr = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = scannerStr.nextLine();
        System.out.println("请输入密码：");
        String password = scannerStr.nextLine();
        new UserInfo(username, password).save();
    }
}
