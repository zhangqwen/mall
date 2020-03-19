<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2020/3/19
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>天狗旗舰店</title>
</head>
<body>
    <h2>请确认您的订单</h2>
    <c:set var="od" value="${orderDetail}" />
    <tr>
        <td>名称：<c:out value="${od.product_name}" /></td><br />
        <td>数量：<c:out value="${od.amount}" /></td><br />
        <td>总价：<c:out value="${od.money}" /></td><br />
    </tr>
    <h2>验证您的身份以确保本次交易安全</h2>
    <form action="<%=request.getContextPath()%>/order/confirmUser?order_id=${od.id}" method="post">
        <!--用户名<input type="text" name="username" /><br /> -->
        密码  <input type="password" name="password" /><br />
        <input type="submit" value="验证">
    </form>
</body>
</html>
