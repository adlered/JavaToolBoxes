package pers.adlered.java_tool_boxes.console_table.main;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>ConsoleTable</h3>
 * <p>在控制台中打印表单</p>
 *
 * <h3>用法：</h3>
 * <p>打印三行两列的表格：</p>
 * <pre>
 * ConsoleTable consoleTable = new ConsoleTable(2, true);
 * // 如果不想打印标题行，将上方的true改为false
 * consoleTable.appendRow();
 * consoleTable.appendColum("Title 1");
 * consoleTable.appendColum("Title 2");
 * consoleTable.appendRow();
 * consoleTable.appendColum("Text 1");
 * consoleTable.appendColum("Text 2");
 * System.out.println(consoleTable.toString());
 * </pre>
 *
 * <p>appendColum方法还支持级联调用：</p>
 * <pre>
 * consoleTable.appendColum("Text 1")
 *     .appendColum("Text 2");
 * </pre>
 *
 * @author : https://github.com/AdlerED
 * @date : 2019-11-23 21:25
 **/
public class ConsoleTable {
    private static int margin = 2;
    private List<List> rows = new ArrayList<List>();
    private int colum;
    private int[] columLen;
    private boolean printHeader = false;

    public ConsoleTable(int colum, boolean printHeader) {
        this.printHeader = printHeader;
        this.colum = colum;
        this.columLen = new int[colum];
    }

    public void appendRow() {
        List row = new ArrayList(colum);
        rows.add(row);
    }

    public ConsoleTable appendColum(Object value) {
        if (value == null) {
            value = "NULL";
        }
        List row = rows.get(rows.size() - 1);
        row.add(value);
        int len = value.toString().getBytes().length;
        if (columLen[row.size() - 1] < len)
            columLen[row.size() - 1] = len;
        return this;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();

        int sumlen = 0;
        for (int len : columLen) {
            sumlen += len;
        }
        if (printHeader)
            buf.append("|").append(printChar('=', sumlen + margin * 2 * colum + (colum - 1))).append("|\n");
        else
            buf.append("|").append(printChar('-', sumlen + margin * 2 * colum + (colum - 1))).append("|\n");
        for (int ii = 0; ii < rows.size(); ii++) {
            List row = rows.get(ii);
            for (int i = 0; i < colum; i++) {
                String o = "";
                if (i < row.size())
                    o = row.get(i).toString();
                buf.append('|').append(printChar(' ', margin)).append(o);
                buf.append(printChar(' ', columLen[i] - o.getBytes().length + margin));
            }
            buf.append("|\n");
            if (printHeader && ii == 0)
                buf.append("|").append(printChar('=', sumlen + margin * 2 * colum + (colum - 1))).append("|\n");
            else
                buf.append("|").append(printChar('-', sumlen + margin * 2 * colum + (colum - 1))).append("|\n");
        }
        return buf.toString();
    }

    private String printChar(char c, int len) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < len; i++) {
            buf.append(c);
        }
        return buf.toString();
    }
}
