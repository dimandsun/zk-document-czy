package cn.lxt6.document.www.util;

import java.io.*;

/**
 * @author chenzy
 * @description
 * @since 2020-03-20
 */
public class FileUtil {
    private FileUtil() {
    }

    public static String readFile(File file) {
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }

    public static String readFile(InputStream in){
        try {
            Integer count = in.available();
            byte[] b = new byte[count];
            in.read(b);
            return b.toString();
        } catch (IOException e) {
            return null;
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
