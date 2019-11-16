package test;

import pers.adlered.java_tool_boxes.console_args.main.ConsoleArgs;
import pers.adlered.java_tool_boxes.inputstream_tostring.main.InputStreamToString;
import pers.adlered.java_tool_boxes.post_http_or_https.main.PostHttpOrHttps;

import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.List;
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
        String[] param = new String[] {
                "-p", "7426", "--ip=127.0.0.1"
        };
        Map<String, List<String>> map = ConsoleArgs.analyze(param);
        for (Map.Entry<String, List<String>> listEntry : map.entrySet()) {
            System.out.println("键：" + listEntry.getKey());
            System.out.println("值：");
            for (String value : listEntry.getValue()) {
                System.out.println(value);
            }
        }
    }
}
