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
    <title>User Home</title>
</head>
<body>

<jsp:include page="../blocks/header.jsp"></jsp:include>

<table border="1">
    <tr>
        <th><fmt:message key="userHomePage.table.label.id"/></th>
        <th><fmt:message key="userHomePage.table.label.name"/></th>
        <th><fmt:message key="userHomePage.table.label.price"/></th>
        <th><fmt:message key="userHomePage.table.label.quantity"/></th>
        <th><fmt:message key="userHomePage.table.label.description"/></th>
        <th><fmt:message key="userHomePage.table.label.category"/></th>
        <th><fmt:message key="userHomePage.table.label.pubDate"/></th>
        <th><fmt:message key="userHomePage.table.label.Action"/></th>
    </tr>
    <c:forEach items="${products}" var="product" >
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
<%--            <td>${product.price}</td>--%>
            <td>
                <c:choose>
                    <c:when test="${sessionScope.lang.equals('en') || sessionScope.lang == null}">
                        ${product.price}
                    </c:when>
                    <c:otherwise>
                        ${product.price * 28}
                    </c:otherwise>
                </c:choose>
            </td>
            <td>${product.quantity}</td>
            <td>${product.description}</td>
            <td>${product.getCategory().getName()}</td>
            <td>${product.publicationDate}</td>
            <td>
                <a href="${pageContext.request.contextPath}/user_home/addToCart?productId=${product.id}"><fmt:message key="userHomePage.table.button.addToCart"/></a>
            </td>
                <%--                    <td>--%>
                <%--                        <a href="deleteProduct?code=${product.code}">Delete</a>--%>
                <%--                    </td>--%>
        </tr>
    </c:forEach>
</table>

<jsp:include page="../blocks/footer.jsp"></jsp:include>

</body>
</html>