<%--
  Created by IntelliJ IDEA.
  User: alencarmachado
  Date: 18/05/20
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <h1>SISTEMA DE TRIAGEM PARA O COVID-19</h1>
        <h5>Olá ${logado.nome}</h5>
        <h5>ID da sessão: ${pageContext.session.id}</h5>

        <ul>
            <li>
                <a href="controlador?opcao=pacientes">Cadastrar Pacientes</a> <br />
                <a href="controlador?opcao=checklist">Responder Checklist de Triagem</a>
            </li>
        </ul>
        <a href="controlador?opcao=sair">SAIR</a>
</body>
</html>
