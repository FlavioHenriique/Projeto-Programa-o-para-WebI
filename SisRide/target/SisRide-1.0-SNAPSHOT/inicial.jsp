<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="minhasTags" %>
<%@page import="com.ifpb.sisride.modelo.*, java.io.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"  %>


<html>
    <head>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
        <link rel="stylesheet" href="CSS/app.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8"/>
        <title>SisRide - Página inicial</title>
    </head>
    <body>
        <%@include file="menu.jsp"%>
        <br> 

        <table width="100%">
            <tr>
                <td width="50%">
                    <div class="row">
                        <c:choose>
                            <c:when test="${usuario.foto2 != null}">
                                <img src="front?command=FotoUsuario" class="circle">
                            </c:when>
                            <c:otherwise>
                                <img src="Imagens/user.png" class="circle">
                            </c:otherwise>
                        </c:choose>

                        <label id="nome">${usuario.nome}</label><br><br>
                        <a href="perfil.jsp"><button class="waves-effect waves-light btn s12">Editar perfil</button>
                        </a>
                        <br><br>
                        <minhasTags:informacoesUsuario usuario="${usuario}" /><br><br>

                    </div>
                <minhasTags:imprimeComentarios lista="${avaliacoesUsuario}" />
            
            <br><br>

            <br>
            </td>
            <td width="50%">
                <div class="row" id="busca" width="100%">
                    <form method="get" action="front">
                        <input type="hidden" name="command" value="BuscaUsuario">
                        <input type="text" name="buscado"  placeholder="Buscar usuários">
                        <button type="submit" class="waves-effect waves-light btn" >Buscar</button>
                    </form>
                </div>
                <div>

                </div>
            </td>
        </tr>
    </table>
    <table width="100%">
        <tr>
            <td width="10%"></td>
            <td width="80%">
                
                <!-- Imprimindo as caronas solicitadas em uma tabela -->
                
                <c:if test="${!empty caronasSolicitadas}">
            <center>
                <h4>Minhas solicitações de vaga</h4>
                <table class="highlight centered responsive-table bordered">
                    <tr>
                        <td><b>Situação</b></td>
                        <td><b>Trajeto</b></td>
                        <td><b>Data</b></td>
                        <td><b>Cancelar</b></td>
                    </tr>
                    <c:forEach var="viagem" items="${caronasSolicitadas}">
                        <tr>
                            <td>${viagem.situacao}</td>
                            <td>

                                ${viagem.partida.nome} - ${viagem.destino.nome}    
                            </td>
                            <td>
                                ${viagem.data}
                            </td>
                            <td>
                                <a class="waves-effect waves-light btn s12 cancelar"
                                   href="front?command=CancelaSolicitacao&carona=${viagem.codigo}">
                                    <i class="material-icons">cancel</i>  Cancelar solicitação</a>
                            </td>
                        </tr>
                    </c:forEach>    

                </table>
            </center>
        </c:if>
        <c:if test="${empty caronasSolicitadas}">
            <label>Você ainda não solicitou nenhuma carona</label> <br>
        </c:if><br>

        <!--Imprimindo as recomendações que o usuário recebeu-->

        <c:if test="${!empty recomendacoes}">
            <h4>Recomendações de caronas</h4>
            <br>
            <c:forEach var="recomendacao" items="${recomendacoes}">
                <minhasTags:imprimeCarona viagem="${recomendacao}" />

                <a class="waves-effect waves-light btn s12 alinhado"
                   href="front?command=SolicitaVaga&codViagem=${recomendacao.codigo}&pagina=inicial">Solicitar vaga</a>
            </div>
        </c:forEach>
    </c:if>

    <!-- Imprimindo as notificações que o usuário recebeu-->

    <c:if test="${!empty notificacoes}">
        <h4>Notificações</h4>
        <br>
        <c:forEach var="notificacao" items="${notificacoes}">
            <div class="solicitacoes col s12">
                <c:choose>
                    <c:when test="${notificacao.tipo eq 'rejeita'}">

                        <i class="material-icons icone_recusa">thumb_down</i>
                    </c:when>
                    <c:otherwise>
                        <i class="material-icons icone_confirma">thumb_up</i>
                    </c:otherwise>
                </c:choose>
                <label class="amizade">${notificacao.notificador} ${notificacao.mensagem}</label> <hr>
            </div>
        </c:forEach>
    </c:if>
</td>
<td width="10%"></td>
</tr>
</table>
<br>

<br><br>
</body>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script>
    var mensagem = "${param.mensagem}";
    switch (mensagem) {
        case "1":
        {
            swal("Parabéns!", "Seu perfil foi atualizado", "success");
            break;
        }
        case "2":
        {
            swal("OK!", "Sua solicitação foi realizada.", "success");
            break;
        }
        case "3":
        {
            swal("OK!", "Sua solicitação de vaga foi cancelada.", "success");
            break;
        }
    }
    var erro = "${param.erro}";
    if (erro == "1") {
        swal("Infelizmente...", "Não encontramos este usuário.", "error");
    }

</script>
</html>


