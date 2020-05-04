<%--
  Created by IntelliJ IDEA.
  User: alencarmachado
  Date: 29/04/20
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%><%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>

<html>
<head>
    <title>Pacientes</title>
</head>
<body>

    <form action="paciente-controller" method="post">

        Nome: <input type="text" name="nome" /> <br />
        E-mail: <input type="email" name="email"/> <br />
        Senha: <input type="password" name="senha"/> <br />
        <input type="submit" value="GRAVAR" />
    </form>

    <c:if test="${retorno == 'OK'}" >
        <h2>Paciente Cadastrado com sucesso!</h2>
    </c:if>

        <h1>Pacientes</h1>
<table>
    <tr>
        <th>Nome</th>
        <th>Email</th>
        <th>Ações</th>
    </tr>
    <c:forEach var="p" items="${pacientes}">

        <c:if test="${p.ativo != false}">
            <tr>
                <td>${p.nome}</td>
                <td>${p.email}</td>
                <td>
                    <a href="http://localhost:8080/app_triagem_covid19/paciente-controller?opcao=excluir&&id=${p.id}">Excluir</a>
                    <a href="http://localhost:8080/app_triagem_covid19/paciente-controller?opcao=editar&&id=${p.id}">Editar</a>
                </td>
            </tr>
        </c:if>
    </c:forEach>
</table>


</body>
</html>
