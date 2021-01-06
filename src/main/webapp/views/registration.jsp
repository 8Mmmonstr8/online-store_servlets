<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty param.lang}">
    <fmt:setLocale value="${param.lang}" scope="session"/>
</c:if>

<fmt:setBundle basename="message"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
</head>
<body>

<jsp:include page="blocks/header.jsp"></jsp:include>


<h3><fmt:message key="registrationPage.title"/></h3>
<p style="color: red;">${errorString}</p>


<form method="POST" action="/store/registration">
    <table border="0">
        <tr>
            <td><fmt:message key="registrationPage.label.firstName"/></td>
            <td><input type="text" name="firstName" /> </td>
        </tr>
        <tr>
            <td><fmt:message key="registrationPage.label.lastName"/></td>
            <td><input type="text" name="lastName" /> </td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" /> </td>
            <td><p style="color: red;">${emailError}</p></td>
        </tr>
        <tr>
            <td><fmt:message key="registrationPage.label.password"/></td>
            <td><input type="password" name="password" /> </td>
        </tr>
        <tr>
            <td colspan ="2">
                <input type="submit" value= "<fmt:message key="registrationPage.button.submit"/>" />
                <a href="/store/"><fmt:message key="registrationPage.button.cancel"/></a>
            </td>
        </tr>
    </table>
</form>


<jsp:include page="blocks/footer.jsp"></jsp:include>

</body>
</html>
