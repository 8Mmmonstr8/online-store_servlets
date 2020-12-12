<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Simple Web Application</title>
</head>

<body>

<h2>IndexsukaPage</h2>

<ul>
    <li><a href="home">Home</a></li>
    <li><a href="login">Login</a></li>
    <li><a href="/products">Product  List</a>

<%--        <table >--%>
<%--            <tr>--%>
<%--                <th>Id</th>--%>
<%--                <th>Name</th>--%>
<%--                <th>Price</th>--%>
<%--                <th>Edit</th>--%>
<%--                <th>Delete</th>--%>
<%--            </tr>--%>
<%--            <c:forEach items="${productList}" var="product" >--%>
<%--                <tr>--%>
<%--                    <td>${product.getId()}</td>--%>
<%--                    <td>${product.name}</td>--%>
<%--                    <td>${product.price}</td>--%>
<%--                    <td>${product.quantity}</td>--%>
<%--                    <td>${product.description}</td>--%>
<%--                    <td>--%>
<%--                        <a href="editProduct?code=${product.code}">Edit</a>--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <a href="deleteProduct?code=${product.code}">Delete</a>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
<%--        </table>--%>

</ul>

</body>
</html>