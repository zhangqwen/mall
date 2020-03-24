<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2020/3/18
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>商品详情</title>
</head>
<body>
<tr>
    <td>
        <a href="<%=request.getContextPath()%>/product/huawei">华为手机</a>
    </td><br />
    <td>
        <a href="<%=request.getContextPath()%>/product/honor">荣耀手机</a>
    </td><br />
    <td>
        <a href="<%=request.getContextPath()%>/product/laptop">笔记本</a>
    </td><br />
    <td>
        <a href="<%=request.getContextPath()%>/product/tablet">平板</a>
    </td><br />
    <td>
        <a href="<%=request.getContextPath()%>/product/findAll">显示所有</a>
    </td>
</tr>
<h2>商品详情</h2>
<tr>
    <td>名称</td>
    <td>存货</td>
    <td>价格</td>
    <td>介绍</td><br>
</tr>
<c:forEach items="${listAll}" var="product">
<form action="<%=request.getContextPath()%>/order/confirmOrder" method="post">
    <tr>
        <td><c:out value="${product.name}" /></td>
        <td><c:out value="${product.stock}" /></td>
        <td><c:out value="${product.price}" /></td>
        <td><c:out value="${product.type}" /></td>
        <td>
            <input type="hidden" name="product_id" value="${product.id}" />
            购买数量<input type="text" name="amount">
            <input type="submit" value="立即购买" />
        </td>
        <br />
    </tr>
</form>
</c:forEach>
</body>
</html>
