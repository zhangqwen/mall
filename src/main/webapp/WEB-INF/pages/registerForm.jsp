<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2020/3/18
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册会员</title>
</head>
<body>
    <h2>注册账号</h2>
    <form action="<%=request.getContextPath()%>/user/register" method="post">
        用户名<input type="text" name="username" /> <br />
        密码  <input type="password" name="password" /> <br />
        <input type="submit" value="注册">
    </form>

</body>
</html>
