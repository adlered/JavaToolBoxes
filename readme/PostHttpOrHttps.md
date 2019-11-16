<h3>PostHttpOrHttps</h3>
<p>无需证书，访问HTTPS页面和HTTP页面</p>
<p>返回一个<b>BufferedInputStream</b>输入流，可以通过下面方法读取出内容：</p>

```
BufferedInputStream bufferedInputStream = HttpOrHttpsAccess.post("https://www.baidu.com", "", null);
byte[] bytes = new byte[1024];
int len = -1;
while ((len = bufferedInputStream.read(bytes)) != -1) {
    System.out.print(new String(bytes, 0 , len));
}
```

<p>或者使用工具包中的<b>InputStreamToString</b>类直接转换为字符串：</p>

```
BufferedInputStream bufferedInputStream = PostHttpOrHttps.post("https://www.baidu.com", "", null);
System.out.print(InputStreamToString.convert(bufferedInputStream));
```

<h3>使用方法：</h3>
<p><b>使用POST方式传输内容，自定义header（postData不能为空）</b><br>

```
Map<String, String> map = new HashMap<>();
map.put("user-agent",
        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
PostHttpOrHttps.post("https://www.baidu.com", "hello=world", map);
```

</p>
<p><b>使用GET方式访问URL，不自定义header（将header设为null即可）</b><br>

```
PostHttpOrHttps.get("https://www.baidu.com?hello=world", null);
```

</p>