package pers.adlered.java_tool_boxes.console_args.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h3>ConsoleArgs</h3>
 * <p>将用户在控制台传入的参数分解为键&值</p>
 *
 * <h3>用法：</h3>
 * <h4>将控制台参数解析：</h4>
 * <pre>
 * public static void main(String[] args) {
 *     Map<String, List<String>> map = ConsoleArgs.analyze(args);
 *     for (Map.Entry<String, List<String>> listEntry : map.entrySet()) {
 *         System.out.println("键：" + listEntry.getKey());
 *         System.out.println("值：");
 *         for (String value : listEntry.getValue()) {
 *             System.out.println(value);
 *         }
 *     }
 * }
 * </pre>
 *
 * <h4>你也可以直接定义一个数组被解析：</h4>
 * <pre>
 * String[] param = new String[] {
 *         "-p", "7426", "--ip=127.0.0.1"
 * };
 * Map<String, List<String>> map = ConsoleArgs.analyze(param);
 * </pre>
 *
 * @author : https://github.com/AdlerED
 * @date : 2019-11-17 00:41
 **/
public class ConsoleArgs {
    private static Map<String, List<String>> storage = new HashMap<>();

    public static Map<String, List<String>> analyze(String[] args) {
        String tempVar = "";
        String tempVal = "";
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--")) {
                List<String> values = new ArrayList<>();
                for (String j : tempVal.split("\n")) {
                    values.add(j);
                }
                storage.put(tempVar, values);
                tempVar = "";
                tempVal = "";
                if (args[i].contains("=")) {
                    String[] tempVal2 = args[i].replaceAll("^--", "").split("=");
                    tempVar = tempVal2[0];
                    for (int j = 1; j < tempVal2.length; j++) {
                        if (tempVal.length() != 0) {
                            tempVal += "\n" + tempVal2[j];
                        } else {
                            tempVal += tempVal2[j];
                        }
                    }
                    List<String> values2 = new ArrayList<>();
                    for (String j : tempVal.split("\n")) {
                        values2.add(j);
                    }
                    storage.put(tempVar, values2);
                    tempVar = "";
                    tempVal = "";
                }
            } else if (args[i].startsWith("-")) {
                if (!tempVar.isEmpty() || !tempVal.isEmpty()) {
                    List<String> values = new ArrayList<>();
                    for (String j : tempVal.split("\n")) {
                        values.add(j);
                    }
                    storage.put(tempVar, values);
                    tempVar = "";
                    tempVal = "";
                }
                tempVar = args[i].replaceAll("^-", "").substring(0, 1);
            } else {
                if (!args[i].startsWith("--") && !tempVar.isEmpty()) {
                    if (tempVal.length() != 0) {
                        tempVal += "\n" + args[i];
                    } else {
                        tempVal += args[i];
                    }
                }
            }
        }
        // 确保最后一个参数被保存
        if (!tempVar.isEmpty()) {
            // 如果键不为空，保存
            List<String> values = new ArrayList<>();
            for (String j : tempVal.split("\n")) {
                values.add(j);
            }
            storage.put(tempVar, values);
        }
        return storage;
    }
}
