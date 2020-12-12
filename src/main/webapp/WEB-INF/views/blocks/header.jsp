<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div style="background: #E0E0E0; height: 55px; padding: 5px;">
    <div style="float: left">
        <h1>Online Store</h1>
    </div>

    <div style="float: right; padding: 10px; text-align: right;">

        <!-- User store in session with attribute: loginedUser -->
        Hello <b>${loginedUser.userName}</b>
        <br/>
        <div style="padding: 5px;">

            <a href="${pageContext.request.contextPath}/">Home</a>
            |
            <a href="/products/">Product List</a>
            |
            <a href="${pageContext.request.contextPath}/userInfo">My Account Info</a>
            |
            <a href="${pageContext.request.contextPath}/login">Login</a>

        </div>

    </div>

</div>