<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty param.lang}">
    <fmt:setLocale value="${param.lang}" scope="session"/>
</c:if>

<fmt:setBundle basename="message"/>

<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <div class="my-0 mr-md-auto font-weight-normal">
        <a href="?lang=en"><fmt:message key="header.lang.en"/></a>
        <a href="?lang=ru"><fmt:message key="header.lang.ru"/></a>

        <h2>Online Store</h2>
    </div>

    <nav class="my-2 my-md-0 mr-md-3">

        <!-- User store in session with attribute: loginedUser -->
        <fmt:message key="header.welcome"/> <b>${loggedUser.email}</b>
        <br/>
        <div style="padding: 5px;">
            <c:choose>
                <c:when test="${role == null}">
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/"><fmt:message key="header.button.home"/></a>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/login"><fmt:message key="header.button.cart"/></a>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/login"><fmt:message key="header.button.login.registration"/></a>
                </c:when>
                <c:when test="${role == 'USER'}">
<%--                    <fmt:message key="common.you_login_as"/> ${sessionScope.username}--%>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/user_home"><fmt:message key="header.button.home"/></a>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/user_home/cart"><fmt:message key="header.button.cart"/></a>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/logout"><fmt:message key="header.button.logout"/></a>
<%--                    <a href="${pageContext.request.contextPath}/app/user/personal-account"><fmt:message key="common.personal_account"/></a><br>--%>
<%--                    <a href="${pageContext.request.contextPath}/app/logout"><fmt:message key="common.logout"/></a><br>--%>
                </c:when>
                <c:when test="${role == 'ADMIN'}">
<%--                    <fmt:message key="common.you_login_as"/> ${sessionScope.username}--%>
                    <a class="btn btn-warning" href="${pageContext.request.contextPath}/admin_home"><fmt:message key="header.button.admin"/></a>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/user_home"><fmt:message key="header.button.home"/></a>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/user_home/cart"><fmt:message key="header.button.cart"/></a>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/logout"><fmt:message key="header.button.logout"/></a>

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

    </nav>

</div>