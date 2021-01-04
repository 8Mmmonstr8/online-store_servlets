<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Categories</title>
</head>
<body>

<jsp:include page="../blocks/header.jsp"></jsp:include>

<br/>


<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Categories</h2></caption>
        <tr>
            <th>Id</th>
            <th>Name</th>
        </tr>
        <c:forEach items="${categories}" var="category" >
            <tr>
                <td>${category.id}</td>
                <td>${category.name}</td>

                    <%--                    <td>--%>
                    <%--                        <a href="deleteProduct?code=${product.code}">Delete</a>--%>
                    <%--                    </td>--%>
            </tr>
        </c:forEach>
    </table>
</div>

<a href="${pageContext.request.contextPath}/admin_home/products/categories/create">Create new category</a>
<br/>
<a href="${pageContext.request.contextPath}/admin_home/products">Back</a>

<jsp:include page="../blocks/footer.jsp"></jsp:include>

</body>
</html>