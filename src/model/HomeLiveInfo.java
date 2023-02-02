package model;

import controller.FileOperator;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeLiveInfo {
    private static final ArrayList<String> INIT_TABLE = new ArrayList<>(Arrays.asList(
            "--------------------------当前收支明细记录--------------------------",
            "                 收支\t账户金额\t\t收支金额\t\t\t\t说  明",
            "                 收入\t10000\t\t10000\t\t\t\t初始金额"
    ));
    private static final String LIVE_TABLE_NAME = "src/model/db/liveInfo/HomeLiveInfo";

    public HomeLiveInfo(UserInfo userInfo) {
        if (null == userInfo) {
            System.out.println("UserInfo cannot be null.");
            return;
        }
        String homeLiveDBName = LIVE_TABLE_NAME + userInfo.getUsername().hashCode();
        File file = new File(homeLiveDBName);
        if (!file.exists()) {
            FileOperator.writeFileWithException(INIT_TABLE, homeLiveDBName, "Table init failed.", false);
        }
    }

    public void writeInfo(int usernameHashCode, String infoType, int accountMoney, int impMoney, String detailInfo) {
        String info = "                 " + infoType + "\t" + accountMoney + "\t\t" + impMoney + "\t\t\t\t" + detailInfo;
        String homeLiveDBName = LIVE_TABLE_NAME + usernameHashCode;
        ArrayList<String> infoArray = new ArrayList<>(List.of(info));
        FileOperator.writeFileWithException(infoArray, homeLiveDBName, "Writing information into table failed.", true);
    }

    public void readInfo(int usernameHashCode) {
        String homeLiveDBName = LIVE_TABLE_NAME + usernameHashCode;
        ArrayList<String> readContent = new ArrayList<>();
        FileOperator.readFileWithException(readContent, homeLiveDBName, "Reading information of table failed.");
        for (String infoLine : readContent) {
            System.out.println(infoLine);
        }
    }
}
