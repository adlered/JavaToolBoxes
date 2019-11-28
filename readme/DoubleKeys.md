<h3>DoubleKeys</h3>
<p>验证Key1中的key2在key1中是否已经存在</p>
<p>用于检测某个用户名是否已经发过相同的一句话</p>
<p>用法：通常Key1为用户名，Key2为用户说的话</p>

```
DoubleKeys.check("XiaoMing", "OhMyGod");
```

已经说过话返回false，第一次说这句话返回true。