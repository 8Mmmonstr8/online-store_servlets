<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>

<jsp:include page="blocks/header.jsp"></jsp:include>


<h3>Login Page</h3>
<p style="color: red;">${errorString}</p>


<form method="POST" action="/store/login">
    <table border="0">
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" /> </td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" /> </td>
        </tr>
        <tr>
            <td colspan ="2">
                <input type="submit" value= "Submit" />
                <a href="${pageContext.request.contextPath}/">Cancel</a>
            </td>
        </tr>
        <tr>
            <a href="${pageContext.request.contextPath}/registration">Register</a>
        </tr>
    </table>
</form>

<p style="color:blue;">Example. Email: tom@google.com, password: tom001</p>

<jsp:include page="blocks/footer.jsp"></jsp:include>

</body>
</html>
