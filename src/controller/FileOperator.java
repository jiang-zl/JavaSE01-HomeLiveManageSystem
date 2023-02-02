package controller;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileOperator {
    private static final int DEFAULT_CONTENT_SIZE = 64;

    public static void writeFileWithException(ArrayList<String> writeContent, String filePath, String exceptionInfo, boolean isAppended) {
        FileWriter fileWriter = null;
        File file = new File(filePath);
        if (file.exists() && !isAppended) {
            return;
        }
        try {
            fileWriter = new FileWriter(filePath, isAppended);
            StringBuilder content = new StringBuilder(DEFAULT_CONTENT_SIZE);
            for (String curLine : writeContent) {
                content.append(curLine);
                content.append('\n');
            }
            fileWriter.append(content.toString());
            fileWriter.flush();
        }
        catch (Exception e) {
            System.out.println(exceptionInfo);
            e.printStackTrace();
        }
        finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("File Writer is null.");
                    e.printStackTrace();
                }
            }
        }
    }

    public static void readFileWithException(ArrayList<String> readContent, String filePath, String exceptionInfo) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
            int curCh;
            StringBuilder curLine = new StringBuilder(DEFAULT_CONTENT_SIZE);
            while ((curCh = fileReader.read()) != -1) {
                if ('\n' != (char) curCh) {
                    curLine.append((char) curCh);
                }
                else {
                    readContent.add(curLine.toString());
                    curLine.setLength(0);
                }
            }
        }
        catch (Exception e) {
            System.out.println(exceptionInfo);
            e.printStackTrace();
        }
        finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                }
                catch (Exception e) {
                    System.out.println("File Reader is null.");
                    e.printStackTrace();
                }
            }
        }
    }
}
