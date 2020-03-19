<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2020/3/16
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>查询所有商品</h2>
    <tr>
        <td>名称</td>
        <td>存货</td>
        <td>价格</td>
        <td>介绍</td><br>
    </tr>
    <c:forEach items="${list}" var="product">
         <tr>
             <td><c:out value="${product.name}" /></td>
             <td><c:out value="${product.stock}" /></td>
             <td><c:out value="${product.price}" /></td>
             <td><c:out value="${product.remark}" /></td>
             <td>
                 <a href="/setOrder">购买</a>
             </td><br>
         </tr>
    </c:forEach>
</body>
</html>
