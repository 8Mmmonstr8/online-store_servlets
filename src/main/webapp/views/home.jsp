<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<c:if test="${empty param.lang}">--%>
<%--    <fmt:setLocale value="en" scope="session"/>--%>
<%--</c:if>--%>
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

<main class="m-3">
    <div>
        <label for="categories">Select category </label>
        <form action="/store/home">
            <select name="category">
                <option value="All">All</option>
                <c:forEach items="${categories}" var="category">
                    <option value="${category.getId()}">
                            ${category.getName()}
                    </option>
                </c:forEach>
            </select>
            <input type="hidden" name="recordsPerPage" value="${recordsPerPage}">
            <input type="hidden" name="currentPage" value="${currentPage}">
            <button type="submit" class="btn btn-sm btn-primary">Submit</button>
        </form>
    </div>
</main>


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
                        <a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/login"><fmt:message key="homePage.table.button.addToCart"/></a>
                    </td>
                        <%--                    <td>--%>
                        <%--                        <a href="deleteProduct?code=${product.code}">Delete</a>--%>
                        <%--                    </td>--%>
                </tr>
            </c:forEach>
        </table>
    </div>
    <nav aria-label="Navigation for products">
        <ul class="pagination">
            <c:if test="${currentPage != 1}">
                <li class="page-item"><a class="page-link"
                                         href="/store/home?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}&category=${category}">Previous</a>
                    <input type="hidden" name="category" value="${category}">
                </li>
            </c:if>

            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active"><a class="page-link">
                                ${i} <span class="sr-only">(current)</span></a>
                            <input type="hidden" name="category" value="${category}">
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link"
                                                 href="/store/home?recordsPerPage=${recordsPerPage}&currentPage=${i}&category=${category}">${i}</a>
                            <input type="hidden" name="category" value="${category}">
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item"><a class="page-link"
                                         href="/store/home?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}&category=${category}">Next</a>
                    <input type="hidden" name="category" value="${category}">
                </li>
            </c:if>
        </ul>
    </nav>
</main>

<main class="m-3">
    <form action="${pageContext.request.contextPath}/home">

        <%--  <input type="hidden" name="currentPage" value="1">  --%>

        <div class="form-group col-md-4">

            <label for="records">Select records per page:</label>

            <select class="form-control" id="records" name="recordsPerPage">
                <option value="5">5</option>
                <option value="10" selected>10</option>
                <option value="15">15</option>
            </select>

        </div>

        <button type="submit" class="btn btn-sm btn-primary">Submit</button>

    </form>
</main>

<jsp:include page="blocks/footer.jsp"></jsp:include>

</body>
</html>