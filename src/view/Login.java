package view;

import java.util.Scanner;

import controller.HomeLive;
import controller.UserAccess;
import model.UserInfo;

public class Login {
    private static final int MAX_ATTEMPT_COUNT = 5;
    private static boolean isNewUser = true;
    private static int checkCount = MAX_ATTEMPT_COUNT;

    public static void newUserCheck() throws Exception {
        Scanner scannerStr = new Scanner(System.in);
        System.out.println("是否新用户注册？是输入“是”，否输入“否”：");
        String checkNewUser = scannerStr.nextLine();
        while (!"是".equals(checkNewUser) && !"否".equals(checkNewUser)) {
            if (checkCount <= 0) {
                checkCount = MAX_ATTEMPT_COUNT;
                throw new Exception("Error: 注册阶段请勿输入无关内容");
            }
            System.out.println("请输入“是”或者“否”，不能输入无关内容。您还有 " + checkCount + " 次输入机会。");
            --checkCount;
            newUserCheck();
        }
        isNewUser = "是".equals(checkNewUser);
    }
    public static void login() {
        try {
            newUserCheck();
            if (isNewUser) {
                Enroll.enrollUserInfo();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        UserInfo userInfo = null;
        while (null == userInfo) {
            Scanner scannerStr = new Scanner(System.in);
            System.out.println("请输入用户名：");
            String username = scannerStr.nextLine();
            System.out.println("请输入密码：");
            String password = scannerStr.nextLine();
            userInfo = UserAccess.checkUserAccess(username, password);
            if (null == userInfo) {
                System.out.println("用户不存在，您是否是新用户？请输入：“是”或者“否”：");
                String checkNewUser = scannerStr.nextLine();
                if ("是".equals(checkNewUser)) {
                    Enroll.enrollUserInfo();
                    isNewUser = true;
                }
                else if ("否".equals(checkNewUser)) {
                    isNewUser = false;
                }
                else {
                    System.out.println("请输入“是”或者“否”，不能输入无关内容。");
                }
            }
        }
        HomeLive homeLive = null;
        if (isNewUser) {
            homeLive = new HomeLive(userInfo, 10000);
        }
        else {
            homeLive = new HomeLive(userInfo);
        }
        new Menu().readCmd(userInfo, homeLive);
    }
}
