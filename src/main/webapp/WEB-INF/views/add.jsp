<%--
  Created by IntelliJ IDEA.
  User: hung1
  Date: 8/16/2023
  Time: 8:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Thêm mới</h1>
<form action="/CustomerController" method="post">
    <table border="10" cellspacing="10px" cellpadding="20px">
        <tr>
            <th>Name:</th>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <th>Age:</th>
            <td><input type="number" min="16" name="age"></td>
        </tr>
        <input type="submit" value="ADD" name="action">
    </table>
</form>
</body>
</html>
