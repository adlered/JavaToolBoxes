package test;

import pers.adlered.java_tool_boxes.console_args.main.ConsoleArgs;
import pers.adlered.java_tool_boxes.console_table.main.ConsoleTable;
import pers.adlered.java_tool_boxes.double_keys.main.DoubleKeys;
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

        ConsoleTable consoleTable = new ConsoleTable(2, true);
        consoleTable.appendRow();
        consoleTable.appendColum("Title 1");
        consoleTable.appendColum("Title 2");
        consoleTable.appendRow();
        consoleTable.appendColum("Text 1");
        consoleTable.appendColum("Text 2");
        System.out.println(consoleTable.toString());
        System.out.println(DoubleKeys.check("hello", "1"));
        System.out.println(DoubleKeys.check("hello", "2"));
        System.out.println(DoubleKeys.check("hello", "1"));
    }
}
