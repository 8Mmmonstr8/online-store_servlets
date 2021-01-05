<%--
  Created by IntelliJ IDEA.
  User: Pavel Hubanov
  Date: 26.12.2020
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty param.lang}">
    <fmt:setLocale value="${param.lang}" scope="session"/>
</c:if>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title>Product Form</title>
</head>
<body>

<jsp:include page="../blocks/header.jsp"></jsp:include>


<h1 align="center"><fmt:message key="productFormPage.title"/></h1>

<div align="center">
    <form method="POST" action="${pageContext.request.contextPath}/admin_home/products/create">
        <table border="0">
            <tr>
                <td><fmt:message key="productFormPage.label.name"/></td>
                <td><input type="text" name="name" /> </td>
            </tr>
            <tr>
                <td><fmt:message key="productFormPage.label.description"/></td>
                <td><input type="text" name="description" /> </td>
            </tr>
            <tr>
                <td><fmt:message key="productFormPage.label.price"/></td>
                <td><input type="text" name="price" /> </td>
            </tr>
            <tr>
                <td><fmt:message key="productFormPage.label.quantity"/></td>
                <td><input type="text" name="quantity" /> </td>
            </tr>
            <tr>
                <td><fmt:message key="productFormPage.label.date"/></td>
                <td><input type="date" name="date" /> </td>
            </tr>
            <tr>
                <div>
<%--                    <td>Category</td>--%>
    <%--                <td><input type="text" name="category" /> </td>--%>

                    <select name="category">
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.getId()}">
                                    ${category.getName()}
                            </option>
<%--                            <input type="hidden" id="categoryId" name="categoryId" value="${category.getId()}"/>--%>
                        </c:forEach>
                    </select>
                </div>

            </tr>
            <tr>
                <td colspan ="2">
                    <input type="submit" value= "<fmt:message key="productFormPage.button.create"/>" />
                    <a href="/store/"><fmt:message key="productFormPage.button.cancel"/></a>
                </td>
            </tr>
        </table>
    </form>
</div>

<a href="${pageContext.request.contextPath}/admin_home/products/categories"><fmt:message key="productFormPage.button.back"/></a>

<jsp:include page="../blocks/footer.jsp"></jsp:include>


</body>
</html>
