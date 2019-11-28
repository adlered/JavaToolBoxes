package pers.adlered.java_tool_boxes.double_keys.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h3>DoubleKeys</h3>
 * <p>验证Key1中的key2在key1中是否已经存在</p>
 * <p>用于检测某个用户名是否已经发过相同的一句话</p>
 * <p>用法：通常Key1为用户名，Key2为用户说的话</p>
 * ```
 * DoubleKeys.check("XiaoMing", "OhMyGod");
 * ```
 * 已经说过话返回false，第一次说这句话返回true。
 *
 * @author : https://github.com/AdlerED
 * @date : 2019-11-28 11:06
 **/
public class DoubleKeys {
    private static Map<String, List<String>> keyMap = new HashMap<>();

    public static boolean check(String key1, String key2) {
        List<String> tempList = new ArrayList<>();
        // 检查key1是否存在
        if (keyMap.containsKey(key1)) {
            // key1存在
            tempList = keyMap.get(key1);
            if (tempList.contains(key2)) {
                return false;
            } else {
                tempList.add(key2);
                keyMap.put(key1, tempList);
                return true;
            }
        } else {
            // key1不存在，生成key1
            tempList.add(key2);
            keyMap.put(key1, tempList);
            // 第一次生成，直接返回true
            return true;
        }
    }
}
