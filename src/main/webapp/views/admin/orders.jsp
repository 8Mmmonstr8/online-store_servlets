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
    <title>Orders</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
</head>
<body>

<jsp:include page="../blocks/header.jsp"></jsp:include>

<br/>

<main class="float-none m-3">
    <div class="row col-md-8">
        <table class="table table-striped table-bordered table-sm">
        <caption><h2><fmt:message key="ordersPage.tables.tableWaitingForApproval"/></h2></caption>
        <tr>
            <th><fmt:message key="ordersPage.tables.label.id"/></th>
            <th><fmt:message key="ordersPage.tables.label.orderDate"/></th>
            <th><fmt:message key="ordersPage.tables.label.userId"/></th>
            <th><fmt:message key="ordersPage.tables.label.cartId"/></th>
            <th><fmt:message key="ordersPage.tables.label.isOrderApproved"/></th>
            <th><fmt:message key="ordersPage.tables.label.action"/></th>

        </tr>
        <c:forEach items="${notApprovedOrders}" var="notApprovedOrder" >
            <tr>
                <td>${notApprovedOrder.getId()}</td>
                <td>${notApprovedOrder.getOrderDate()}</td>
                <td>${notApprovedOrder.getUser().getId()}</td>
                <td>${notApprovedOrder.getCart().getId()}</td>
                <td>${notApprovedOrder.isApproved()}</td>
                <td>
                    <a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/admin_home/orders/approve?orderId=${notApprovedOrder.getId()}"><fmt:message key="ordersPage.tables.button.approve"/></a>
                    <a class="btn btn-sm btn-danger" href="${pageContext.request.contextPath}/admin_home/orders/decline?orderId=${notApprovedOrder.getId()}"><fmt:message key="ordersPage.tables.button.decline"/></a>
                    <a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/admin_home/orders/details?orderId=${notApprovedOrder.getId()}"><fmt:message key="ordersPage.tables.button.details"/></a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</main>

<br/>

<main class="float-none m-3">
    <div class="row col-md-8">
        <table class="table table-striped table-bordered table-sm">
        <caption><h2><fmt:message key="ordersPage.tables.tableApprovedOrders"/></h2></caption>
        <tr>
            <th><fmt:message key="ordersPage.tables.label.id"/></th>
            <th><fmt:message key="ordersPage.tables.label.orderDate"/></th>
            <th><fmt:message key="ordersPage.tables.label.userId"/></th>
            <th><fmt:message key="ordersPage.tables.label.cartId"/></th>
            <th><fmt:message key="ordersPage.tables.label.isOrderApproved"/></th>
            <th><fmt:message key="ordersPage.tables.label.action"/></th>

        </tr>
        <c:forEach items="${approvedOrders}" var="approvedOrder" >
            <tr>
                <td>${approvedOrder.getId()}</td>
                <td>${approvedOrder.getOrderDate()}</td>
                <td>${approvedOrder.getUser().getId()}</td>
                <td>${approvedOrder.getCart().getId()}</td>
                <td>${approvedOrder.isApproved()}</td>
                <td>
                    <a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/admin_home/orders/details?orderId=${approvedOrder.getId()}"><fmt:message key="ordersPage.tables.button.details"/></a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</main>



<a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/admin_home"><fmt:message key="ordersPage.button.back"/></a>

<jsp:include page="../blocks/footer.jsp"></jsp:include>

</body>
</html>