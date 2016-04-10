<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
</head>
<body>
<c:if test="${not empty errors}">
    <p style="color: red">${errors}</p>
</c:if>

<form method="post" action="/servlets/login">
    <p align="center">
        Login <input type="text" name="login" value="" size="20"/>
        <br/>
        <br/>
        Password <input type="password" name="password" value="" size="20"/>
        <br/>
        <br/>
        <input type="submit" name="Submit" value="Submit"/>
    </p>
</form>
</body>
</html>
