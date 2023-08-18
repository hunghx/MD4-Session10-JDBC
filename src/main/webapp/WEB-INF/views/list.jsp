<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<h1>Danh sách khách hàng</h1>
<a href="/CustomerController?action=CREATE">Thêm mới</a>
<table border="10" cellspacing="10px" cellpadding="20px">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Age</th>
        <th colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${customers}" var="c">
        <tr>
            <td>${c.id}</td>
            <td>${c.name}</td>
            <td>${c.age}</td>
            <td><a href="/CustomerController?action=EDIT&id=${c.id}">Edit</a></td>
            <td><a onclick="return confirm('Bạn có chắc muốn xóa không?')"  href="/CustomerController?action=DELETE&id=${c.id}">Delete</a></td>
        </tr>
    </c:forEach>
    <c:if test="${page>0}">
        <a href="/CustomerController?action=GETALL&page=${page-1}&size=${size}">Prev</a>
    </c:if>
    <c:if test="${page<totalPage-1}">
        <a href="/CustomerController?action=GETALL&page=${page+1}&size=${size}">Next</a>
    </c:if>
    </tbody>
</table>
</body>
</html>
