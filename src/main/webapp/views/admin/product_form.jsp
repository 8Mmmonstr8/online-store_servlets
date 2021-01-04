<%--
  Created by IntelliJ IDEA.
  User: Pavel Hubanov
  Date: 26.12.2020
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Product Form</title>
</head>
<body>

<jsp:include page="../blocks/header.jsp"></jsp:include>


<h1 align="center">Create new Product</h1>

<div align="center">
    <form method="POST" action="${pageContext.request.contextPath}/admin_home/products/create">
        <table border="0">
            <tr>
                <td>Name</td>
                <td><input type="text" name="name" /> </td>
            </tr>
            <tr>
                <td>Description</td>
                <td><input type="text" name="description" /> </td>
            </tr>
            <tr>
                <td>Price</td>
                <td><input type="text" name="price" /> </td>
            </tr>
            <tr>
                <td>Quantity</td>
                <td><input type="text" name="quantity" /> </td>
            </tr>
            <tr>
                <td>Date</td>
                <td><input type="date" name="date" /> </td>
            </tr>
            <tr>
                <div>
<%--                    <td>Category</td>--%>
    <%--                <td><input type="text" name="category" /> </td>--%>

                    <select name="category">
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.getId()}">
                                    ${category.getName()}
                            </option>
<%--                            <input type="hidden" id="categoryId" name="categoryId" value="${category.getId()}"/>--%>
                        </c:forEach>
                    </select>
                </div>

            </tr>
            <tr>
                <td colspan ="2">
                    <input type="submit" value= "Submit" />
                    <a href="/store/">Cancel</a>
                </td>
            </tr>
        </table>
    </form>
</div>

<a href="${pageContext.request.contextPath}/admin_home/products/categories">Back</a>

<jsp:include page="../blocks/footer.jsp"></jsp:include>


</body>
</html>
