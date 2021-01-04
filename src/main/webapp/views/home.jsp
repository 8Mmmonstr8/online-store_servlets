<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty param.lang}">
    <fmt:setLocale value="${param.lang}" scope="session"/>
</c:if>

<fmt:setBundle basename="message"/>

<!DOCTYPE html>
<html lang="${param.lang}">
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
</head>
<body>

<jsp:include page="blocks/header.jsp"></jsp:include>

<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Description</th>
        <th>Category</th>
        <th>Pub Date</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${products}" var="product" >
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.quantity}</td>
            <td>${product.description}</td>
            <td>${product.getCategory().getName()}</td>
            <td>${product.publicationDate}</td>
            <td>
                <a href="addToCart?id=${product.id}">Add to Cart</a>
            </td>
                <%--                    <td>--%>
                <%--                        <a href="deleteProduct?code=${product.code}">Delete</a>--%>
                <%--                    </td>--%>
        </tr>
    </c:forEach>
</table>

<jsp:include page="blocks/footer.jsp"></jsp:include>

</body>
</html>