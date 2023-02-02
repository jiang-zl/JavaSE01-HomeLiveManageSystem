package controller;
import model.HomeLiveInfo;
import model.UserInfo;

public class HomeLive {
    private int curMoney;
    private int usernameHashCode;
    private HomeLiveInfo homeLiveInfo;

    public HomeLive(UserInfo userInfo) {
        this.homeLiveInfo = new HomeLiveInfo(userInfo);
        this.usernameHashCode = userInfo.getUsername().hashCode();
    }

    public HomeLive(UserInfo userInfo, int initMoney) {
        this.curMoney = initMoney;
        this.homeLiveInfo = new HomeLiveInfo(userInfo);
        this.usernameHashCode = userInfo.getUsername().hashCode();
    }

    public void importMoneyInfo(int impMoney, String detailInfo) {
        if (impMoney < 0) {
            System.out.println("一项错误的记录，输入了小于0的收入，收入金额必须大于等于0！");
            return;
        }
        this.curMoney += impMoney;
        this.homeLiveInfo.writeInfo(usernameHashCode, "收入", curMoney, impMoney, detailInfo);
    }

    public void exportMoneyInfo(int expMoney, String detailInfo) {
        if (this.curMoney < expMoney) {
            System.out.println("无法承受本次支出，支出失败！请合理理财！");
            return;
        }
        this.curMoney -= expMoney;
        this.homeLiveInfo.writeInfo(usernameHashCode, "支出", curMoney, expMoney, detailInfo);
    }

    public void printHomeLiveInfo() {
        this.homeLiveInfo.readInfo(usernameHashCode);
    }
}
