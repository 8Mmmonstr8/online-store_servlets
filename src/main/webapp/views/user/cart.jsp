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
    <title>Cart</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
</head>
<body>

<jsp:include page="../blocks/header.jsp"></jsp:include>


    <h2 align="center"><fmt:message key="cartPage.title"/></h2>


<main class="float-none m-3">
    <div class="row col-md-8">
        <h3 align="center"><fmt:message key="cartPage.tableProductsInCart.tableName"/></h3>
        <table class="table table-striped table-bordered table-sm">
            <tr>
                <th><fmt:message key="cartPage.tableProductsInCart.label.name"/></th>
                <th><fmt:message key="cartPage.tableProductsInCart.label.category"/></th>
                <th><fmt:message key="cartPage.tableProductsInCart.label.description"/></th>
                <th><fmt:message key="cartPage.tableProductsInCart.label.pubDate"/></th>
                <th><fmt:message key="cartPage.tableProductsInCart.label.availableQuantity"/></th>
                <th><fmt:message key="cartPage.tableProductsInCart.label.price"/></th>
                <th><fmt:message key="cartPage.tableProductsInCart.label.neededQuantity"/></th>
                <th><fmt:message key="cartPage.tableProductsInCart.label.Action"/></th>
            </tr>
            <c:forEach items="${products}" var="product" >
                <tr>
                    <td>${product.getKey().getName()}</td>
                    <td>${product.getKey().getCategory().getName()}</td>
                    <td>${product.getKey().getDescription()}</td>
                    <td>${product.getKey().getPublicationDate()}</td>
                    <td>${product.getKey().getQuantity()}</td>
    <%--                <td>${product.getKey().getPrice()}</td>--%>
                    <td>
                        <c:choose>
                            <c:when test="${sessionScope.lang.equals('en') || sessionScope.lang == null}">
                                ${product.getKey().getPrice()}
                            </c:when>
                            <c:otherwise>
                                ${product.getKey().getPrice() * 28}
                            </c:otherwise>
                        </c:choose>
                    </td>
    <%--                <td>${product.getValue()}</td>--%>
                    <td>
                        <form method="POST" action="${pageContext.request.contextPath}/user_home/cart">
                            <div style="display:flex">
                                <input name="neededQuantity"
                                       value="${product.getValue()}"
                                       size="2">
                                <input type="hidden" id="productId" name="productId" value="${product.getKey().getId()}"/>
                                <input type="submit" class="btn btn-sm btn-success" value="<fmt:message key="cartPage.tableProductsInCart.button.submit"/>">
                            </div>
                        </form>
                    </td>

                    <td>
        <%--                <a href="addToCart?id=${product.id}">Add to Cart</a>--%>
                        <a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/user_home/cart/delete?productId=${product.getKey().getId()}"><fmt:message key="cartPage.tableProductsInCart.button.delete"/></a>

                    </td>

                </tr>
            </c:forEach>
        </table>
    <%--    <p align="center"><b>Total sum: ${totalPrice}</b></p>--%>
        <p align="center"><b><fmt:message key="cartPage.tableProductsInCart.totalSum"/>
            <c:choose>
                <c:when test="${sessionScope.lang.equals('en') || sessionScope.lang == null}">
                    ${totalPrice}
                </c:when>
                <c:otherwise>
                    ${totalPrice * 28}
                </c:otherwise>
            </c:choose>
        </b></p>

        <a class="btn btn-sm btn-warning" href="${pageContext.request.contextPath}/user_home/cart/checkout"><fmt:message key="cartPage.tableProductsInCart.button.checkout"/></a>
    </div>
</main>

<br/>

<main class="float-none m-3">
    <div class="row col-md-8">
        <table class="table table-striped table-bordered table-sm">
            <caption><h2><fmt:message key="cartPage.tableWaitingForApproval.tableName"/></h2></caption>
            <tr>
                <th><fmt:message key="cartPage.tableWaitingForApproval.label.name"/></th>
                <th><fmt:message key="cartPage.tableWaitingForApproval.label.quantity"/></th>
                <th><fmt:message key="cartPage.tableWaitingForApproval.label.price"/></th>
                <th><fmt:message key="cartPage.tableWaitingForApproval.label.category"/></th>
                <th><fmt:message key="cartPage.tableWaitingForApproval.label.description"/></th>
                <th><fmt:message key="cartPage.tableWaitingForApproval.label.orderId"/></th>
                <th><fmt:message key="cartPage.tableWaitingForApproval.label.orderDate"/></th>

            </tr>
            <c:forEach items="${notApprovedOrderedProducts}" var="notApprovedOrderedProduct" >
                <tr>
                    <td>${notApprovedOrderedProduct.getName()}</td>
                    <td>${notApprovedOrderedProduct.getQuantity()}</td>
    <%--                <td>${notApprovedOrderedProduct.getPrice()}</td>--%>
                    <td>
                        <c:choose>
                            <c:when test="${sessionScope.lang.equals('en') || sessionScope.lang == null}">
                                ${notApprovedOrderedProduct.getPrice()}
                            </c:when>
                            <c:otherwise>
                                ${notApprovedOrderedProduct.getPrice() * 28}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${notApprovedOrderedProduct.getCategory().getName()}</td>
                    <td>${notApprovedOrderedProduct.getDescription()}</td>
                    <td>${notApprovedOrderedProduct.getOrder().getId()}</td>
                    <td>${notApprovedOrderedProduct.getOrder().getOrderDate()}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</main>

<br/>

<main class="float-none m-3">
    <div class="row col-md-8">
        <table class="table table-striped table-bordered table-sm">
            <caption><h2><fmt:message key="cartPage.tableOrderedProducts.tableName"/></h2></caption>
            <tr>
                <th><fmt:message key="cartPage.tableOrderedProducts.label.name"/></th>
                <th><fmt:message key="cartPage.tableOrderedProducts.label.quantity"/></th>
                <th><fmt:message key="cartPage.tableOrderedProducts.label.price"/></th>
                <th><fmt:message key="cartPage.tableOrderedProducts.label.category"/></th>
                <th><fmt:message key="cartPage.tableOrderedProducts.label.description"/></th>
                <th><fmt:message key="cartPage.tableOrderedProducts.label.orderId"/></th>
                <th><fmt:message key="cartPage.tableOrderedProducts.label.orderDate"/></th>

            </tr>
            <c:forEach items="${approvedOrderedProducts}" var="approvedOrderedProduct" >
                <tr>
                    <td>${approvedOrderedProduct.getName()}</td>
                    <td>${approvedOrderedProduct.getQuantity()}</td>
    <%--                <td>${approvedOrderedProduct.getPrice()}</td>--%>
                    <td>
                        <c:choose>
                            <c:when test="${sessionScope.lang.equals('en') || sessionScope.lang == null}">
                                ${approvedOrderedProduct.getPrice()}
                            </c:when>
                            <c:otherwise>
                                ${approvedOrderedProduct.getPrice() * 28}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${approvedOrderedProduct.getCategory().getName()}</td>
                    <td>${approvedOrderedProduct.getDescription()}</td>
                    <td>${approvedOrderedProduct.getOrder().getId()}</td>
                    <td>${approvedOrderedProduct.getOrder().getOrderDate()}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</main>


<jsp:include page="../blocks/footer.jsp"></jsp:include>


</body>
</html>
