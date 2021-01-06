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
    <title>Order Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
</head>
<body>

<jsp:include page="../blocks/header.jsp"></jsp:include>

<br/>

<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2><fmt:message key="orderPage.title"/></h2></caption>
        <tr>
            <th><fmt:message key="orderPage.tableOrder.label.name"/></th>
            <th><fmt:message key="orderPage.tableOrder.label.quantity"/></th>
            <th><fmt:message key="orderPage.tableOrder.label.price"/></th>
            <th><fmt:message key="orderPage.tableOrder.label.category"/></th>
            <th><fmt:message key="orderPage.tableOrder.label.description"/></th>
            <th><fmt:message key="orderPage.tableOrder.label.orderId"/></th>
            <th><fmt:message key="orderPage.tableOrder.label.orderDate"/></th>
            <th><fmt:message key="orderPage.tableOrder.label.isOrderApproved"/></th>

        </tr>
        <c:forEach items="${orderedProducts}" var="orderedProduct" >
            <tr>
                <td>${orderedProduct.getName()}</td>
                <td>${orderedProduct.getQuantity()}</td>
<%--                <td>${orderedProduct.getPrice()}</td>--%>
                <td>
                    <c:choose>
                        <c:when test="${sessionScope.lang.equals('en') || sessionScope.lang == null}">
                            ${orderedProduct.getPrice()}
                        </c:when>
                        <c:otherwise>
                            ${orderedProduct.getPrice() * 28}
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${orderedProduct.getCategory().getName()}</td>
                <td>${orderedProduct.getDescription()}</td>
                <td>${orderedProduct.getOrder().getId()}</td>
                <td>${orderedProduct.getOrder().getOrderDate()}</td>
                <td>${orderedProduct.getOrder().isApproved()}</td>
            </tr>
        </c:forEach>
    </table>
<%--    <p align="center"><b><fmt:message key="orderPage.totalSum"/> ${totalPrice}</b></p>--%>
    <p align="center"><b><fmt:message key="orderPage.totalSum"/>

        <c:choose>
            <c:when test="${sessionScope.lang.equals('en') || sessionScope.lang == null}">
                ${totalPrice}
            </c:when>
            <c:otherwise>
                ${totalPrice * 28}
            </c:otherwise>
        </c:choose>
    </b></p>

</div>

<a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/admin_home/orders"><fmt:message key="orderPage.button.back"/></a>

<jsp:include page="../blocks/footer.jsp"></jsp:include>

</body>
</html>