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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
</head>
<body>

<jsp:include page="../blocks/header.jsp"></jsp:include>


<main class="float-none m-3">
    <div class="row col-md-8">
        <table class="table table-striped table-bordered table-sm">
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
                        <a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/user_home/addToCart?productId=${product.id}"><fmt:message key="userHomePage.table.button.addToCart"/></a>
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
                                         href="/store/user_home?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active"><a class="page-link">
                                ${i} <span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link"
                                                 href="/store/user_home?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item"><a class="page-link"
                                         href="/store/user_home?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>
</main>

<main class="m-3">
    <form action="${pageContext.request.contextPath}/user_home">

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





<jsp:include page="../blocks/footer.jsp"></jsp:include>

</body>
</html>