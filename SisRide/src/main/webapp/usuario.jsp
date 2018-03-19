<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.ifpb.sisride.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<html>
    <head>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
        <link rel="stylesheet" href="CSS/app.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8"/>

        <title>${buscado.nome}</title>
    </head>
    <body>

        <%@include file="menu.jsp"%>

        <table>
            <tr>
                <td>
                    <div class="row">
                        <img src="foto?command=FotoBuscado" class="circle">

                        <label id="nome">${buscado.nome}</label>
                    </div>
                    <label>Profissão: ${buscado.profissao}</label>
                    <br><label>Data de nascimento: ${buscado.nascimento}</label>
                    <br><label> Cidade: ${buscado.cidade}</label>
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
                                <a  class="waves-effect waves-light btn s12"
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
                    <a  class="waves-effect waves-light btn s12"
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
                                <a  class="waves-effect waves-light btn s12"
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
</table>
</body>


<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
</html>
