<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2020/3/17
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Order Detail</title>
</head>
<body>
    <h2>Order Detial</h2>
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
        <td><c:out value="${od.amount}" /></td>
        <td><c:out value="${od.money}" /></td><br>
    </tr>

</body>
</html>
