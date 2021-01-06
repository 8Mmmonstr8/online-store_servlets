<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
</head>
<body>

<jsp:include page="blocks/header.jsp"></jsp:include>


<h3><fmt:message key="loginPage.title"/></h3>
<p style="color: red;">${errorString}</p>


<form method="POST" action="/store/login">
    <table border="0">
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" /> </td>
        </tr>
        <tr>
            <td><fmt:message key="loginPage.label.password"/></td>
            <td><input type="password" name="password" /> </td>
        </tr>
        <tr>
            <td colspan ="2">
                <input class="btn btn-sm btn-primary" type="submit" value= "<fmt:message key="loginPage.button.submit"/>" />
                <a class="btn btn-sm btn-danger" href="${pageContext.request.contextPath}/"><fmt:message key="loginPage.button.cancel"/></a>
            </td>
        </tr>
        <tr>
            <a class="btn btn-sm btn-warning" href="${pageContext.request.contextPath}/registration"><fmt:message key="loginPage.button.register"/></a>
        </tr>
    </table>
</form>

<p style="color:blue;"><fmt:message key="loginPage.text.example"/></p>

<jsp:include page="blocks/footer.jsp"></jsp:include>

</body>
</html>
