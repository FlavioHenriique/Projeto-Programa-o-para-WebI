
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
        <link rel="stylesheet" href="CSS/app.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8"/>

        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>

        <script src="JS/app.js"></script>
        <title>Solicitações</title>
    </head>
    <body>

        <%@include file="menu.jsp"%>
        <table width="100%">
            <tr>
                <td>

                </td>
                <td>
                    <h4>Solicitações</h4>
                </td>
            </tr>
        </table>
        <c:forEach var="solicitacao" items="${solicitacoes}">
            <div>
                <h5>${solicitacao.amigo.nome}</h5>
                <a href="front?command=BuscaUsuario&buscado=${solicitacao.amigo.nome}">Ver perfil</a>
                <button class="waves-effect waves-light btn s12">Aceitar</button>
                <button class="waves-effect waves-light btn s12">Recusar</button>
            </div>
        </c:forEach>
    </body>
</html>
