<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="minhasTags" %>
<%@page import="com.ifpb.sisride.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<html>
    <head>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
        <link rel="stylesheet" href="CSS/app.css">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8"/>

        <title>${buscado.nome}</title>
    </head>
    <body>

        <%@include file="menu.jsp"%>
        <br>
        <table>
            <tr>
                <td>
                    <div class="row">
                        <minhasTags:informacoesUsuario usuario="${buscado}" />
                    </div>
                </td>
                <td>
            <center>
                <br>

                <!-- Verificação do relacionamento de Amizade entre os usuários -->

                <c:set var="solicitouAmizade" scope="page" value="false" />
                <c:forEach var="solicitacao" items="${solicitacoes}">
                    <c:if test="${(solicitacao.usuario.email eq usuario.email) &&
                                  (solicitacao.amigo.email eq buscado.email) && (solicitacao.tipo eq 'amizade')}">
                        <c:set var="solicitouAmizade" scope="page" value="true" />
                        <c:choose>
                            <c:when test="${solicitacao.situacao eq 'pendente'}">
                                Aguardando resposta do usuário (amizade)
                            </c:when>
                            <c:when test="${solicitacao.situacao eq 'aceita'}">
                                <a  class="waves-effect waves-light btn s12 cancelar"
                                    href="front?command=DesfazerRelacionamento&tipo=amizade">Desfazer amizade</a>
                            </c:when>
                        </c:choose>
                    </c:if>
                    <c:if test="${(solicitacao.amigo.email eq usuario.email) && (solicitacao.usuario.email eq buscado.email)
                                  && (solicitacao.tipo eq 'amizade') && (solicitacao.situacao eq 'aceita')}">
                        <c:set var="foiSolicitado"  value="true"/>
                    </c:if>
                </c:forEach>

                <c:if test="${foiSolicitado eq 'true'}">
                    <a  class="waves-effect waves-light btn s12 cancelar"
                        href="front?command=DesfazerRelacionamento&tipo=amizade">Desfazer amizade</a>
                </c:if>

                <c:if test="${(solicitouAmizade eq 'false') && (foiSolicitado != 'true')}">
                    <a  class="waves-effect waves-light btn s12"
                        href="front?command=Solicitacao&tipo=amizade">Adicionar aos amigos</a>
                </c:if>

                <br><br>

                <!-- Verificação do relacionamento de Seguir entre os usuários -->

                <c:set var="solicitouSeguir" scope="page" value="false" />
                <c:forEach var="solicitacao" items="${solicitacoes}">
                    <c:if test="${(solicitacao.usuario.email eq usuario.email) &&
                                  (solicitacao.amigo.email eq buscado.email) && (solicitacao.tipo eq 'seguir')}">
                        <c:set var="solicitouSeguir" scope="page" value="true" />
                        <c:choose>
                            <c:when test="${solicitacao.situacao eq 'pendente'}">
                                Aguardando resposta do usuário (seguir)
                            </c:when>
                            <c:when test="${solicitacao.situacao eq 'aceita'}">
                                <a  class="waves-effect waves-light btn s12 cancelar"
                                    href="front?command=DesfazerRelacionamento&tipo=seguir">Deixar de seguir</a>
                            </c:when>
                        </c:choose>
                    </c:if>

                </c:forEach>
                <c:if test="${solicitouSeguir eq 'false'}">
                    <a  class="waves-effect waves-light btn s12"
                        href="front?command=Solicitacao&tipo=seguir">Seguir este usuário</a>
                </c:if>
            </center>
        </td>
    </tr>
    <tr>
        <!-- Comentários que o usuário recebeu-->
        <td>
            <minhasTags:imprimeComentarios lista="${avaliacoesBuscado}" />
        </td>
        <td></td>
    </tr>
</table>
<!--Imprimindo as caronas cadastradas pelo usuário buscado -->
<table width="100%">
    <tr>
        <td width="20%"></td>
        <td width="60%">
            <c:if test="${!empty caronasBuscado}">

        <center>
            <h4>Caronas cadastradas por ${buscado.nome}</h4>
        </center>

        <c:forEach var="viagem" items="${caronasBuscado}">
            <minhasTags:imprimeCarona viagem="${viagem}" />
            <minhasTags:jaSolicitou viagem="${viagem}" pagina="usuario" />
        </div>
        <br><br>
    </c:forEach>
</c:if>
</td>
<td width="20%"></td>
</tr>
</table>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
</html>
<script>
    var mensagem = "${param.mensagem}";
    switch (mensagem) {
    case "1":
    {
    swal("OK!", "Sua solicitação foi realizada!", "success");
            break;
    }
</script>
