<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2020/3/18
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎来到天狗</title>
</head>
<body>
    <h2>分类</h2>
    <tr>
        <td>
            <a href="<%=request.getContextPath()%>/product/add">录入商品信息</a>
        </td>
    </tr><br />
    <tr>
        <td>
            <a href="<%=request.getContextPath()%>/user/setting">修改个人信息</a>
        </td>
    </tr><br />
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
</body>
</html>
