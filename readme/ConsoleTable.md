<h3>JavaToolBoxes</h3>
<p>在控制台中打印表单</p>
<h3>用法：</h3>
<p>打印三行两列的表格：</p>

```
ConsoleTable consoleTable = new ConsoleTable(2, true);
// 如果不想打印标题行，将上方的true改为false
consoleTable.appendRow();
consoleTable.appendColum("Title 1");
consoleTable.appendColum("Title 2");
consoleTable.appendRow();
consoleTable.appendColum("Text 1");
consoleTable.appendColum("Text 2");
System.out.println(consoleTable.toString());
```

<p>appendColum方法还支持级联调用：</p>

```
consoleTable.appendColum("Text 1")
    .appendColum("Text 2");
```