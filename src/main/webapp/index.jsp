<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2020/3/18
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎来到天狗旗舰店</title>
</head>
<body>
    <h1>天狗旗舰店</h1>
    <form action="<%=request.getContextPath()%>/user/login" method="post">
        用户名<input type="text" name="username" /><br />
        密码  <input type="password" name="password" /><br />
        <input type="submit" value="登录">
    </form>
     <a href="<%=request.getContextPath()%>/user/registering">注册</a>

</body>
</html>
