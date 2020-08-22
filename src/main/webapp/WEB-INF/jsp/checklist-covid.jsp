<%--
  Created by IntelliJ IDEA.
  User: alencarmachado
  Date: 12/05/20
  Time: 00:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page isELIgnored="false" %>
<html>
<head>
    <title>TRIAGEM PARA COVID-19</title>
</head>
<body>
<a href="controlador?opcao=dashbord">VOLTAR</a>
<form action="checklist" method="post">
    <label>Pacientes</label>
    <select name="idpaciente">
        <c:forEach var="paciente" items="${pacientes}">
            <option value="${paciente.id}">${paciente.usuario.nome}</option>
        </c:forEach>
    </select> <br />

    <label>Agentes</label>
    <select name="idagente">
        <c:forEach var="agente" items="${agentes}">
            <option value="${agente.id}" >${agente.usuario.nome}</option>
        </c:forEach>
    </select> <br />

    <label for="temperatura">Temperatura</label>
    <input type="number" placeholder="36.9" step="0.01" min="0" max="44" name="temperatura" /> <br />

    <input type="checkbox" value="true" name="tosse" />
    <label for="tosse">Tosse?</label><br />

    <input type="checkbox" value="true" name="rouquidao" />
    <label for="rouquidao">Rouquidao?</label><br />

    <input type="checkbox" value="true" name="dorgarganta" />
    <label for="dorgarganta">Dor de Garganta?</label><br />

    <input type="checkbox" value="true" name="narizentupido" />
    <label for="narizentupido">Nariz entupido?</label><br />

    <input type="checkbox" value="true" name="catarro" />
    <label for="catarro">Catarro?</label><br />

    <label for="diassintomas">Dias com os sintomas</label>
    <input type="number" name="diassintomas" /> <br />

    <input type="hidden" name="opcao" value="gravar" />
    <input type="submit" name="gravar" value="GRAVAR" />

</form>

<c:if test="${not empty retorno}">
    <h2>${retorno}</h2>
    <h1>${suspeito}</h1>
</c:if>

<h2>COLETAS REALIZADAS</h2>
    <table>
        <tr>
            <th>NOME</th>
            <th>SUS </th>
            <th>Suspeito?</th>
            <th>COLETA</th>
        </tr>
        <c:forEach var="sintoma" items="${sintomas}">

            <c:choose>
                <c:when test="${sintoma.casoSuspeito == true}">
                    <tr style="color: white; background-color: brown">
                </c:when>
                <c:otherwise>
                    <tr>
                </c:otherwise>
            </c:choose>


                <td>${sintoma.paciente.usuario.nome}</td>
                <td>${sintoma.paciente.cartaoSus}</td>
                <td>${sintoma.casoSuspeito}</td>
                <td>
                    <fmt:formatDate value="${sintoma.dataAvaliacao}" pattern="dd/MM/yyyy" />
                </td>
            </tr>

        </c:forEach>
    </table>

</body>
</html>
