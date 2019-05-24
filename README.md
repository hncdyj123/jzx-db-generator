## jzx-db-generator能做什么？
jzx-db-generator可以把数据库中的表（mysql or oracle）转换成 word，做到基本的修改，即可以作为数据库文档提交。

## 使用方法
java -jar jzx-db-generator.jar 数据库名 jdbc连接字符串 数据库用户名 数据库密码 输出文件路径<br/>
示例：<br/>
```
java -jar jzx-db-generator.jar test jdbc:mysql://localhost:3306/test root root123 d:/test_create_table.docx
```

## 生成效果图
![image](https://github.com/hncdyj123/jzx-db-generator/blob/master/images/result.png)

## 喜欢请帮忙点击下项目右上角的star
