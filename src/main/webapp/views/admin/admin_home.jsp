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
    <title>Admin Home</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
</head>
<body>

<jsp:include page="../blocks/header.jsp"></jsp:include>

<%--<br/>--%>

<%--<a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/admin_home/orders" class="button"><fmt:message key="adminHomePage.button.orders"/></a>--%>
<%--<br/>--%>
<%--<a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/admin_home/users" class="button"><fmt:message key="adminHomePage.button.users"/></a>--%>
<%--<br/>--%>
<%--<a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/admin_home/products" class="button"><fmt:message key="adminHomePage.button.products"/></a>--%>

<div class="center">

    <div class="center" style="margin-top:20px">
        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
            <fieldset class="center">
<%--                <h1 align="center">Admin's Home Page</h1>--%>
                <div style="margin-top: 16px">
                    <p><a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/admin_home/orders" class="button"><fmt:message key="adminHomePage.button.orders"/></a></p>
                </div><div style="margin-top: 16px">
                <p><a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/admin_home/users" class="button"><fmt:message key="adminHomePage.button.users"/></a></p>
            </div>
                <div style="margin-top: 16px">
                    <p><a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/admin_home/products" class="button"><fmt:message key="adminHomePage.button.products"/></a></p>
                </div>
            </fieldset>
        </div>
    </div>

    <div/>

<jsp:include page="../blocks/footer.jsp"></jsp:include>

</body>
</html>