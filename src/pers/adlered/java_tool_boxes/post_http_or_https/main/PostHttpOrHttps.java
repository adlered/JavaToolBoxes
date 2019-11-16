package pers.adlered.java_tool_boxes.post_http_or_https.main;

import pers.adlered.java_tool_boxes.post_http_or_https.util.MyX509TrustManager;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.util.Map;

/**
 * <h3>PostHttpOrHttps</h3>
 * <p>无需证书，访问HTTPS页面和HTTP页面</p>
 *
 * <p>返回一个<b>BufferedInputStream</b>输入流，可以通过下面方法读取出内容：</p>
 * <pre>
 * BufferedInputStream bufferedInputStream = HttpOrHttpsAccess.post("https://www.baidu.com", "", null);
 * byte[] bytes = new byte[1024];
 * int len = -1;
 * while ((len = bufferedInputStream.read(bytes)) != -1) {
 *     System.out.print(new String(bytes, 0 , len));
 * }
 * </pre>
 * <p>或者使用工具包中的<b>InputStreamToString</b>类直接转换为字符串：</p>
 * <pre>
 * BufferedInputStream bufferedInputStream = PostHttpOrHttps.post("https://www.baidu.com", "", null);
 * System.out.print(InputStreamToString.convert(bufferedInputStream));
 * </pre>
 *
 * <h3>使用方法：</h3>
 *
 * <p><b>使用POST方式传输内容，自定义header（postData不能为空）</b><br>
 * <pre>
 * Map<String, String> map = new HashMap<>();
 * map.put("user-agent",
 *         "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
 * PostHttpOrHttps.post("https://www.baidu.com", "hello=world", map);
 * </pre>
 * </p>
 *
 * <p><b>使用GET方式访问URL，不自定义header（将header设为null即可）</b><br>
 * <pre>
 * PostHttpOrHttps.get("https://www.baidu.com?hello=world", null);
 * </pre>
 * </p>
 *
 * @author : https://github.com/AdlerED
 * @date : 2019-11-16 23:52
 **/

public class PostHttpOrHttps {
    public static BufferedInputStream get(String path, Map<String, String> header) throws IOException, GeneralSecurityException {
        return post(path, null, header);
    }

    public static BufferedInputStream post(String path, String postData, Map<String, String> header) throws IOException, GeneralSecurityException {
        boolean HTTPS = path.matches("^(https://).*");
        // 打开连接
        URL url = new URL(path);
        URLConnection conn = null;
        conn = url.openConnection();
        // Content
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.835.163 Safari/535.1");
        // 超时
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(15000);
        // Header
        if (header != null) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        // 输入流
        BufferedInputStream in = null;
        if (path.matches("^(https://).*")) {
            HttpsURLConnection httpsConn = (HttpsURLConnection) conn;
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            httpsConn.setSSLSocketFactory(ssf);
            httpsConn.setDoInput(true);// 打开输入流，以便从服务器获取数据
            httpsConn.setDoOutput(true);// 打开输出流，以便向服务器提交数据
            if (postData != null && !postData.isEmpty()) {
                PrintWriter out = new PrintWriter(httpsConn.getOutputStream());
                out.print(postData);
                out.flush();
            }
            in = new BufferedInputStream(httpsConn.getInputStream());
        } else {
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            if (postData != null && !postData.isEmpty()) {
                PrintWriter out = new PrintWriter(httpConn.getOutputStream());
                out.print(postData);
                out.flush();
            }
            in = new BufferedInputStream(httpConn.getInputStream());
        }
        return in;
    }
}
