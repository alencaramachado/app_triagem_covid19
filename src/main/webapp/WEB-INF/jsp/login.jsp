<%--
  Created by IntelliJ IDEA.
  User: alencarmachado
  Date: 18/05/20
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>BEM VINDO</title>
</head>
<body>
        <h1>LOGAR NO SISTEMA</h1>
        <form action="login" method="post">
            <label for="email"><b>Login</b></label>
            <input type="text" placeholder="email" name="email" required> <br />
            <label for="senha"><b>Senha</b></label>
            <input type="password" placeholder="senha" name="senha" required> <br />
            <input type="submit" value="Login" name="login" />
        </form>
    <c:if test="${not empty erro}">
        <h2><b>${erro}</b></h2>
    </c:if>
</body>
</html>
