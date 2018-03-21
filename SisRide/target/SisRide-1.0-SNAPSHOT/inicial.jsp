<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

                        <label id="nome">${usuario.nome}</label>
                    </div>            
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
        <br>


        <a href="perfil.jsp"><button class="waves-effect waves-light btn s12">Editar perfil</button></a><br><br>
        <br>

        <c:if test="${!empty caronasSolicitadas}">
        <center>
            <table class="highlight centered responsive-table bordered">
                <tr>
                    <td><b>Situação</b></td>
                    <td><b>Trajeto</b></td>
                    <td><b>Data</b></td>
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
                    </tr>
                </c:forEach>    

            </table>
        </center>
    </c:if>
    <c:if test="${empty caronasSolicitadas}">
        Você ainda não solicitou nenhuma carona 
    </c:if><br>

    <c:if test="${!empty notificacoes}">
        <label>Notificações</label>
        <br><br>
        <c:forEach var="notificacao" items="${notificacoes}">
            <div class="solicitacoes col s12">
                <c:choose>
                    <c:when test="${notificacao.tipo eq 'rejeita'}">
                        <img src="Imagens/rejeita.jpg" class="circle amizade">
                    </c:when>
                    <c:otherwise>
                        <img src="Imagens/confirma.png" class="circle amizade">
                    </c:otherwise>
                </c:choose>
                <label>${notificacao.notificador} ${notificacao.mensagem}</label> <hr>
            </div>
        </c:forEach>
    </c:if>    

</body>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script>
    var mensagem = "${param.mensagem}";
    if (mensagem == "1") {
        swal("Parabéns!", "Seu perfil foi atualizado", "success");
    }
</script>
</html>


