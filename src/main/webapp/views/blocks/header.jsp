<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="background: #E0E0E0; height: 55px; padding: 5px;">
    <div style="float: left">
        <h1>Online Store</h1>
    </div>

    <div style="float: right; padding: 10px; text-align: right;">

        <!-- User store in session with attribute: loginedUser -->
        Hello <b>${loggedUser.email}</b>
        <br/>
        <div style="padding: 5px;">
            <c:choose>
                <c:when test="${role == null}">
                    <a href="${pageContext.request.contextPath}/store/">Home</a>
                    <a href="${pageContext.request.contextPath}/store/login">Cart</a>
                    <a href="${pageContext.request.contextPath}/store/login">Login</a>
                </c:when>
                <c:when test="${role == 'USER'}">
<%--                    <fmt:message key="common.you_login_as"/> ${sessionScope.username}--%>
                    <a href="${pageContext.request.contextPath}/store/user_home">Home</a>
                    <a href="${pageContext.request.contextPath}/store/user_home/cart">Cart</a>
                    <a href="${pageContext.request.contextPath}/store/logout">Logout</a>
<%--                    <a href="${pageContext.request.contextPath}/app/user/personal-account"><fmt:message key="common.personal_account"/></a><br>--%>
<%--                    <a href="${pageContext.request.contextPath}/app/logout"><fmt:message key="common.logout"/></a><br>--%>
                </c:when>
                <c:when test="${role == 'ADMIN'}">
<%--                    <fmt:message key="common.you_login_as"/> ${sessionScope.username}--%>
                    <a href="${pageContext.request.contextPath}/store/admin">Admin</a>
                    <a href="${pageContext.request.contextPath}/store/user_home">Home</a>
                    <a href="${pageContext.request.contextPath}/store/user_home/cart">Cart</a>
                    <a href="${pageContext.request.contextPath}/store/logout">Logout</a>

<%--                    <a href="${pageContext.request.contextPath}/app/manager/tours-operations"><fmt:message key="common.manager_page"/></a><br>--%>
<%--                    <a href="${pageContext.request.contextPath}/app/admin/new-tour"><fmt:message key="common.new_tour"/></a><br>--%>
<%--                    <a href="${pageContext.request.contextPath}/app/logout"><fmt:message key="common.logout"/></a><br>--%>
                </c:when>
            </c:choose>








<%--            <a href="/store/">Home</a>--%>
<%--            |--%>
<%--            <a href="/products/">Product List</a>--%>
<%--&lt;%&ndash;            |&ndash;%&gt;--%>
<%--&lt;%&ndash;            <a href="${pageContext.request.contextPath}/userInfo">My Account Info</a>&ndash;%&gt;--%>
<%--            |--%>
<%--            <a href="/store/login">Login</a>--%>
<%--            |--%>
<%--            <a href="/store/logout">Logout</a>--%>

        </div>

    </div>

</div>