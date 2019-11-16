<h3>ConsoleArgs</h3>
<p>将用户在控制台传入的参数分解为键&值</p>
<h3>用法：</h3>
<h4>将控制台参数解析：</h4>
<pre>
public static void main(String[] args) {
    Map<String, List<String>> map = ConsoleArgs.analyze(args);
    for (Map.Entry<String, List<String>> listEntry : map.entrySet()) {
        System.out.println("键：" + listEntry.getKey());
        System.out.println("值：");
        for (String value : listEntry.getValue()) {
            System.out.println(value);
        }
    }
}
</pre>
<h4>你也可以直接定义一个数组被解析：</h4>
<pre>
String[] param = new String[] {
        "-p", "7426", "--ip=127.0.0.1"
};
Map<String, List<String>> map = ConsoleArgs.analyze(param);
</pre>