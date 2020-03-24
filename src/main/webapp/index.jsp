
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
        用户名<input type="text" name="username" placeholder="请输入您的用户名"/><br />
        密码  <input type="password" name="password" placeholder="请输入您的密码"/><br />
        <input type="submit" value="登录">
    </form>
     <a href="<%=request.getContextPath()%>/user/registering">注册</a>
     <a href="<%=request.getContextPath()%>/admin/login">管理员登录</a>

</body>
</html>
<!--
<!DOCTYPE html>
<html >
<head>
<meta charset="UTF-8">
<title>Login Form</title>

<link rel="stylesheet" href="css/normalize.css">

<style type="text/css">


</style>


</head>

<body>

<div class="login">
<h1>华为商城</h1>
<h2>登录</h2>
<form action="<%=request.getContextPath()%>/user/login" method="post">
<input type="text" name="username" placeholder="用户名" required="required" />
<input type="password" name="password" placeholder="密码" required="required" />
<input type="submit" class="btn btn-primary btn-block btn-large" value="登录">
</form>
</div>

<div class="register">
<p><a href="<%=request.getContextPath()%>/user/registering">注册</a> </p>
</div>

</body>
</html>



<html>
<head>
    <meta charset="utf-8">
    <script src="https://hm.baidu.com/hm.js?bc210cd22928076d28296716d9dfcf01"></script>
    <script>var _hmt = _hmt || [];
    !function () {
        var e = document.createElement("script");
        e.src = "https://hm.baidu.com/hm.js?bc210cd22928076d28296716d9dfcf01";
        var t = document.getElementsByTagName("script")[0];
        t.parentNode.insertBefore(e, t)
    }()</script>
    <link href="css/common.css?bd0e9d81cdd510cf08de" rel="stylesheet">
    <link href="css/login.css?bd0e9d81cdd510cf08de" rel="stylesheet">
</head>
<body>
<div class="header-simple">
    <div class="w"><a href="<%=request.getContextPath()%>" class="logo">SHOP</a></div>
</div>
<div class="user-wrap">
    <div class="user-con w">
        <div class="user-title">用户登录</div>
        <div class="user-box">
            <div class="err-item">
                <i class="fa fa-minus-circle err-icon"></i>
                <p class="err-msg">Error</p>
            </div>
            <div class="user-item">
                <label class="user-label" for="username"><i class="fa fa-user"></i></label>
                <input type="text" class="user-content" id="username" placeholder="请输入用户名" autocomplete="off">
            </div>
            <div class="user-item">
                <label class="user-label" for="password"><i class="fa fa-lock"></i></label>
                <input type="password" class="user-content" id="password" placeholder="请输入密码">
            </div>
            <a class="user-btn" id="submit">登录</a>
            <div class="link-item">
                <a class="link register" href="<%=request.getContextPath()%>/user/registering" target="_blank">免费注册</a>
            </div>
        </div>
    </div>
</div>
<script src="js/jquery.min.js?v=2.1.4"></script>
<script src="js/util.js?v=2.1.4"></script>
<script src="js/layui.js"></script>
<script src="js/common.js"></script>
<script type="text/javascript" src="js/login.js?bd0e9d81cdd510cf08de"></script>


</body>
</html>
-->