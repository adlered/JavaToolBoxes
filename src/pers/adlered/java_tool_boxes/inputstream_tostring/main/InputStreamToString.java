package pers.adlered.java_tool_boxes.inputstream_tostring.main;

import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * <h3>InputStreamToString</h3>
 * <p>将InputStream转换为字符串并返回，支持BufferedInputStream与InputStream</p>
 *
 * <h3>使用方法：</h3>
 * <pre>
 * InputStreamToString.convert(bufferedInputStream);
 * </pre>
 *
 * @author : https://github.com/AdlerED
 * @date : 2019-11-17 00:11
 **/
public class InputStreamToString {
    public static String convert(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        try {
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(bytes)) != -1) {
                sb.append(new String(bytes, 0 , len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String convert(BufferedInputStream bufferedInputStream) {
        StringBuilder sb = new StringBuilder();
        try {
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = bufferedInputStream.read(bytes)) != -1) {
                sb.append(new String(bytes, 0 , len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
