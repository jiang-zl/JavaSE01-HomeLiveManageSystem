package view;

import controller.HomeLive;
import model.HomeLiveInfo;
import model.UserInfo;
import java.util.Scanner;

public class Menu {
    public void readCmd(UserInfo userInfo, HomeLive homeLive) {
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);
        System.out.println("欢迎用户 " + userInfo.getUsername() + " 使用我们的家庭收支记账软件！");

        while (true) {
            System.out.println("--------------------------家庭收支记账软件--------------------------\n");
            System.out.println("                            1 收支明细");
            System.out.println("                            2 登记收入");
            System.out.println("                            3 登记支出");
            System.out.println("                            4 退   出\n");
            System.out.print("                            请选择（1-4）：");
            int cmd = scannerInt.nextInt();
            switch (cmd) {
                case 1:
                    homeLive.printHomeLiveInfo();
                    break;
                case 2:
                    System.out.println("本次收入金额：");
                    int impMoney = scannerInt.nextInt();
                    System.out.println("本次收入说明：");
                    String impInfo = scannerStr.nextLine();
                    homeLive.importMoneyInfo(impMoney, impInfo);
                    break;
                case 3:
                    System.out.println("本次支出金额：");
                    int expMoney = scannerInt.nextInt();
                    System.out.println("本次支出说明：");
                    String expInfo = scannerStr.nextLine();
                    homeLive.exportMoneyInfo(expMoney, expInfo);
                    break;
                case 4:
                    System.out.println("感谢使用本系统，欢迎再次使用！");
                    Login.login();
                    break;
                default:
                    System.out.println("输入指令错误，请输入（1-4）");
                    break;
            }
        }
    }
}
