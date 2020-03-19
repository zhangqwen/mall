<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2020/3/19
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>天狗旗舰店</title>
</head>
<body>

    <form action="<%=request.getContextPath()%>/user/update" method="post">
        新密码：<input type="password" name="password">
        <input type="submit" value="更改密码">
    </form>

</body>
</html>
