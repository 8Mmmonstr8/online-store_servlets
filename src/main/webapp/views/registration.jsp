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


<h3>Registration Page</h3>
<p style="color: red;">${errorString}</p>


<form method="POST" action="/store/registration">
    <table border="0">
        <tr>
            <td>First Name</td>
            <td><input type="text" name="firstName" /> </td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><input type="text" name="lastName" /> </td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" /> </td>
            <td><p style="color: red;">${emailError}</p></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" /> </td>
        </tr>
        <tr>
            <td colspan ="2">
                <input type="submit" value= "Submit" />
                <a href="/store/">Cancel</a>
            </td>
        </tr>
    </table>
</form>


<jsp:include page="blocks/footer.jsp"></jsp:include>

</body>
</html>
