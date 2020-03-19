<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2020/3/19
  Time: 0:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>天狗旗舰店</title>
</head>
<body>
    <h2>录入商品信息</h2>
    <form action="<%=request.getContextPath()%>/product/save" method="post">
        名称<input type="text" name="name"><br />
        数量<input type="text" name="stock"><br />
        价格<input type="text" name="price"><br />
        类别<input type="text" name="type"><br />
        <input type="submit" value="录入">
    </form> <br />

    <a href="<%=request.getContextPath()%>/product/catalog">返回目录</a>
</body>
</html>
