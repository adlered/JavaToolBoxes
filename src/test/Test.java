package test;

import pers.adlered.java_tool_boxes.inputstream_tostring.main.InputStreamToString;
import pers.adlered.java_tool_boxes.post_http_or_https.main.PostHttpOrHttps;

import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * <h3>JavaToolBoxes</h3>
 * <p>测试类</p>
 *
 * @author : https://github.com/AdlerED
 * @date : 2019-11-16 23:58
 **/
public class Test {
    public static void main(String[] args) {
        try {
            BufferedInputStream bufferedInputStream = PostHttpOrHttps.post("https://blog.stackoverflow.wiki", "", null);
            System.out.print(InputStreamToString.convert(bufferedInputStream));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
