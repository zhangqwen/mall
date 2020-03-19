<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2020/3/19
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>天狗旗舰店</title>
</head>
<body>
    <h2>请选择您想购买的商品</h2>
    <form action="<%=request.getContextPath()%>/order/confirmOrder" method="post">
        名称<input type="text" name="product_name"><br />
        购买数量<input type="text" name="amount"><br />
        <input type="submit" value="立即购买">
    </form>
</body>
</html>
