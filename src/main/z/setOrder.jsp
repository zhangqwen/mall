<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2020/3/17
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请确认您的订单</title>
</head>
<body>
    <h2>确认您购买<c:out value="${od.product_name}" />的数量</h2>
    <tr>
        <td>id</td>
        <td>商品id</td>
        <td>商品名称</td>
        <td>订单id</td>
        <td>数量</td>
        <td>金额小计</td><br>
    </tr>
    <tr>
        <td><c:out value="${od.id}" /></td>
        <td><c:out value="${od.product_id}" /></td>
        <td><c:out value="${od.product_name}" /></td>
        <td><c:out value="${od.order_id}" /></td>
        <td><input type="text" name="数量" /></td>
        <input type="submit" value="提交" /><br />
    </tr>
    <!-- !!!!!!!!!!!!!!!!!!!要结合用户和订单详情来！！！！！！！！！！！！！！！！！！-->
    <form action="createOrder" method="post">
        名称：<input type="text" name="name" /><br />
        存货：<input type="text" name="stock" /><br />
        价格: <input type="text" name="price" /><br />
        介绍：<input type="text" name="remark" /><br />
        <input type="submit" value="保存" /><br />
    </form>



</body>
</html>
