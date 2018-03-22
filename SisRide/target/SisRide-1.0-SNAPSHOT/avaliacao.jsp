
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="minhasTags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
        <link rel="stylesheet" href="CSS/app.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8"/>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="JS/app.js"></script>
        <title>Sisride - Avaliação de usuários</title>
    </head>
    <body>
        <%@include file="menu.jsp"%>

        <!-- Imprimindo as viagens em que o usuário foi motorista -->

        <table width="100%">
            <h4>Caronas nas quais fui motorista</h4>
            <c:forEach var="viagem" items="${caronasRealizadas}">

                <tr>
                    <td width="60%">
                        <minhasTags:imprimeCarona viagem="${viagem}" />
                        </div>
                        <br>
                    </td>
                    <td width="5%"></td>
                    <td width="33%">
                        <form method="post" action="front">

                            <h5>Avaliação de passageiro</h5><br>
                            <input type="hidden" name="command" value="AvaliarUsuario">
                            <select>
                                <c:forEach var="passageiro" items="${viagem.passageiros}">
                                    <option value="" disabled selected>Selecione o passageiro...</option>
                                    <option value="${passageiro.email}">${passageiro.nome}</option>
                                </c:forEach>
                            </select> <br>
                            <input type="text" placeholder="Determine a nota" name="nota"><br>
                            <textarea  class="materialize-textarea" placeholder="Digite um comentário sobre este passageiro..."></textarea>
                            <br>
                            <center>
                                <a href="#" class="waves-effect waves-light btn s12">Avaliar</a>
                            </center>
                        </form>
                    </td>
                    <td width="2%"></td>
                </tr>
            </c:forEach>

        </table>
    </body>
</html>
