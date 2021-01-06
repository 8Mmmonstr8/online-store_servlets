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
</main>

<a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/admin_home/products/categories/create"><fmt:message key="categoriesPage.button.createNewCategory"/></a>
<br/>
<a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/admin_home/products"><fmt:message key="categoriesPage.button.back"/></a>

<jsp:include page="../blocks/footer.jsp"></jsp:include>

</body>
</html>