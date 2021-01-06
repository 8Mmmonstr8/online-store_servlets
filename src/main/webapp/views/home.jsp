<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${empty param.lang}">
    <fmt:setLocale value="en" scope="session"/>
</c:if>
<c:if test="${not empty param.lang}">
    <fmt:setLocale value="${param.lang}" scope="session"/>
</c:if>

<fmt:setBundle basename="message"/>

<!DOCTYPE html>
<html lang="${param.lang}">
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
</head>
<body>

<jsp:include page="blocks/header.jsp"></jsp:include>

<main class="float-none m-3">
    <div class="row col-md-8">
        <table class="table table-striped table-bordered table-sm">
            <tr>
                <th><fmt:message key="homePage.table.label.id"/></th>
                <th><fmt:message key="homePage.table.label.name"/></th>
                <th><fmt:message key="homePage.table.label.price"/></th>
                <th><fmt:message key="homePage.table.label.quantity"/></th>
                <th><fmt:message key="homePage.table.label.description"/></th>
                <th><fmt:message key="homePage.table.label.category"/></th>
                <th><fmt:message key="homePage.table.label.pubDate"/></th>
                <th><fmt:message key="homePage.table.label.Action"/></th>
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
                        <a class="btn btn-sm btn-primary" href="addToCart?id=${product.id}"><fmt:message key="homePage.table.button.addToCart"/></a>
                    </td>
                        <%--                    <td>--%>
                        <%--                        <a href="deleteProduct?code=${product.code}">Delete</a>--%>
                        <%--                    </td>--%>
                </tr>
            </c:forEach>
        </table>
    </div>
</main>

<jsp:include page="blocks/footer.jsp"></jsp:include>

</body>
</html>