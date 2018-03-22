
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
            <h4>Minhas caronas (motorista)</h4>
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
                            <input type="hidden" name="tipo" value="passageiro">
                            <select name="avaliado">
                                <c:forEach var="passageiro" items="${viagem.passageiros}">
                                    <option value="" disabled selected>Selecione o passageiro...</option>
                                    <option value="${passageiro.email}">${passageiro.nome}</option>
                                </c:forEach>
                            </select> <br>
                            <input type="text" placeholder="Determine a nota" name="nota"><br>
                            <textarea name="comentario" class="materialize-textarea" 
                                      placeholder="Digite um comentário sobre este passageiro..."></textarea>
                            <br>
                            <center>
                                <button type="submit" class="waves-effect waves-light btn s12" >Avaliar passageiro</button>
                            </center>
                        </form>
                    </td>
                    <td width="2%"></td>

                </tr>
            </c:forEach>
            <tr>
                <td>
                    <br><br>
                    <h4>Minhas caronas (passageiro)</h4>
                </td>
            </tr>
            <c:forEach var="viagem" items="${caronasSolicitadas}">

                <tr>
                    <td width="60%">
                        <minhasTags:imprimeCarona viagem="${viagem}" />
                        </div>
                        <br>
                    </td>
                    <td width="5%"></td>
                    <td width="33%">
                        <form method="post" action="front">
                           
                            <h5>Avaliação de motorista</h5><br>
                            <input type="hidden" name="command" value="AvaliarUsuario">
                            <input type="hidden" name="avaliado" value="${viagem.motorista.email}">
                            <input type="hidden" name="tipo" value="motorista">
                            Motorista: ${viagem.motorista.nome}<br>
                            <br>
                            <input type="text" placeholder="Determine a nota" name="nota"><br>
                            <textarea name="comentario" class="materialize-textarea" 
                                      placeholder="Digite um comentário sobre este passageiro..."></textarea>
                            <br>
                            <center>
                                <button type="submit" class="waves-effect waves-light btn s12" >Avaliar motorista</button>
                            </center>
                        </form>
                    </td>
                    <td width="2%"></td>
                </tr>
            </c:forEach>

        </table>
    </body>
</html>
<script>
    var mensagem = "${param.mensagem}";

    switch (mensagem) {
        case "1":
        {
            swal("OK!", "A avaliação foi feita!", "success");
            break;
        }
        case "2":
        {
            swal("Erro!", "Preencha todos os campos!", "error");
            break;
        }
    }
</script>
