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
    <a href="controlador?opcao=dashbord">VOLTAR</a>

    <h5>Olá ${logado.nome}</h5>
    <h5>ID da sessão: ${pageContext.session.id}</h5>

    <form action="paciente-controller" method="post">

        <c:choose>
            <c:when test="${paciente.id != null}">
                <h1>Editar Paciente</h1>
                <input type="hidden" name="idpaciente" value="${paciente.id}" />
                <input type="hidden" name="idusuario" value="${paciente.usuario.id}"/>
            </c:when>
            <c:otherwise>
                <h1>Adicionar Paciente</h1>
                <input type="hidden" name="idpaciente" value="0" />
            </c:otherwise>
        </c:choose>

        Nome: <input type="text" name="nome" value="${paciente.usuario.nome}" /> <br />

        E-mail: <input type="email" name="email" value="${paciente.usuario.email}"/> <br />
        Cartao Sus: <input type="text" name="cartaosus" value="${paciente.cartaoSus}"/> <br />
        Idade: <input type="number" name="idade" value="${paciente.idade}"/> <br />
        Senha: <input type="password" name="senha" value="${paciente.usuario.senha}"/> <br />

        <input type="hidden" name="opcao" value="gravar" />
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
        <th>Cartão Sus</th>
        <th>Idade</th>
        <th>Ações</th>
    </tr>
    <c:forEach var="p" items="${pacientes}">

        <c:if test="${p.usuario.ativo != false}">
            <tr>
                <td>${p.usuario.nome}</td>
                <td>${p.usuario.email}</td>
                <td>${p.cartaoSus}</td>
                <td>${p.idade}</td>
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
