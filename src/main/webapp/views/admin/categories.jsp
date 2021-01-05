<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty param.lang}">
    <fmt:setLocale value="${param.lang}" scope="session"/>
</c:if>

<fmt:setBundle basename="message"/>

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
        <caption><h2><fmt:message key="categoriesPage.title"/></h2></caption>
        <tr>
            <th><fmt:message key="categoriesPage.table.label.id"/></th>
            <th><fmt:message key="categoriesPage.table.label.name"/></th>
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

<a href="${pageContext.request.contextPath}/admin_home/products/categories/create"><fmt:message key="categoriesPage.button.createNewCategory"/></a>
<br/>
<a href="${pageContext.request.contextPath}/admin_home/products"><fmt:message key="categoriesPage.button.back"/></a>

<jsp:include page="../blocks/footer.jsp"></jsp:include>

</body>
</html>