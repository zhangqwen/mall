<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
<a href="product/findAll">查询商品信息</a>

<h2>录入商品信息</h2>

<form action="product/save" method="post">
    名称：<input type="text" name="name" /><br />
    存货：<input type="text" name="stock" /><br />
    价格: <input type="text" name="price" /><br />
    介绍：<input type="text" name="remark" /><br />
    <input type="submit" value="保存" /><br />
</form>

</body>
</html>
