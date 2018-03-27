
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
            <c:forEach var="viagem" items="${avaliacao_motorista}">

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
                            <input type="hidden" id="viagem" value="${viagem.codigo}">

                            <select name="avaliado" id="avaliado">
                                <option value="" disabled selected>Selecione o passageiro...</option>
                                <c:forEach var="passageiro" items="${viagem.passageiros}">
                                    <option value="${passageiro.email}">${passageiro.nome}</option>
                                </c:forEach>
                            </select>
                            <input type="hidden" name="command" value="AvaliarUsuario">
                            <input type="hidden" name="tipo" value="passageiro">
                            <input type="hidden" name="viagem" value="${viagem.codigo}">
                            <br>
                            <input type="text" placeholder="Determine a nota" name="nota"><br>
                            <textarea name="comentario" class="materialize-textarea" 
                                      placeholder="Digite um comentário sobre este passageiro..."></textarea>
                            <br>
                            <center>
                                <button type="submit" class="waves-effect waves-light btn s12">
                                     <i class="material-icons right">send</i>Avaliar passageiro</button>
                                <br><br>

                            </center>
                        </form><center>
                    <button class="waves-effect waves-light btn s12 cancelar"
                            onclick="cancela('${usuario.email}', '${viagem.codigo}')">
                         <i class="material-icons right">cancel</i>
                        Cancelar avaliação</button>
                </center>
            </td>
            <td width="2%"></td>
        </tr>
    </c:forEach>
    <tr>
        <td>
            <br><br>

            <!-- Imprimindo as caronas em que o usuário foi passageiro-->

            <h4>Minhas caronas (passageiro)</h4>
        </td>
    </tr>
    <c:forEach var="viagem" items="${avaliacao_passageiro}">

        <tr>
            <td width="60%">
                <minhasTags:imprimeCarona viagem="${viagem}" />
                </div>
                <br>
            </td>
            <td width="5%"></td>
            <td width="33%">
                <minhasTags:avaliacaoMotorista viagem="${viagem}" />
                <br>
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
        case "3":
        {
            swal("OK!", "A avaliação foi atualizada!", "success");
            break;
        }
        case "4":
        {
            swal("OK!", "A avaliação foi cancelada.", "success");
            break;
        }
        case "5":
        {
            swal("Erro!", "Você ainda não avaliou este passageiro.", "error");
            break;
        }

    }

    function cancela(avaliador, viagem) {

        var useravaliado = avaliado.value;
        window.location.replace("front?command=CancelaAvaliacaoPassageiro&avaliado=" + useravaliado + "&avaliador=" + avaliador + "&viagem=" + viagem + "&tipo=passageiro");
    }
    ;

</script>
