<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
        <link rel="stylesheet" href="CSS/app.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8"/>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>

        <script src="JS/app.js"></script>
        <title>Solicitações</title>
    </head>
    <body>
        <%@include file="menu.jsp"%>
        <table width="100%">
            <tr>
                <td width="40%">
                    <form method="get" action="front">
                        <input type="text" name="nomeLugar" placeholder="Para onde deseja ir?" class="validate">
                        <input type="hidden" name="command" value="BuscarCaronas">
                        <button class="waves-effect waves-light btn s12"><i class="material-icons right">search</i>
                            Buscar caronas</button>
                    </form>
                    <c:if test="${usuario.sexo eq 'Feminino'}">
                        <a class="waves-effect waves-light btn s12"
                           href="front?command=SoElas"
                           ><i class="material-icons right">search</i>Só elas</a>
                    </c:if>



                    <c:if test="${buscaViagens != null}">
                        <c:forEach var="viagem" items="${buscaViagens}">
                            <c:if test="${(viagem.motorista.email != usuario.email) && (viagem.vagas > 0)}">
                                <div class="solicitacoes">
                                    <h5><b>${viagem.partida.nome} - ${viagem.destino.nome}</b></h5>
                                    Motorista: <a href="front?command=BuscaUsuario&buscado=${viagem.motorista.nome}">
                                        ${viagem.motorista.nome}</a>
                                    <br>Data da viagem: ${viagem.data}, às ${viagem.hora} horas
                                    <br> Há <b>${viagem.vagas} vagas</b> disponíveis
                                    <br>O nível de conversa desejado é ${viagem.conversa},
                                    a música desejada é ${viagem.musica},
                                    <c:choose>
                                        <c:when test="${viagem.fumar eq true}">
                                            é permitido fumar
                                        </c:when>
                                        <c:otherwise>
                                            Não é permitido fumar
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${viagem.animais eq true}">
                                            e é permitido levar animais.
                                        </c:when>
                                        <c:otherwise>
                                            e não é permitido levar animais.
                                        </c:otherwise>
                                    </c:choose>
                                    <br>O carro utilizado será um ${viagem.carro.modelo} ${viagem.carro.ano}
                                    <c:choose>
                                        <c:when test="${viagem.carro.ar_condicionado eq true}">
                                            com ar-condicionado.
                                        </c:when>
                                        <c:otherwise>
                                            sem ar-condicionado.
                                        </c:otherwise>
                                    </c:choose>
                                    <br><br>
                                    <c:choose>
                                        <c:when test="${viagem.soelas eq true && usuario.sexo eq 'Masculino'}">
                                            <h5 id="soelas">Permitido apenas mulheres</h5>
                                        </c:when>
                                        <c:otherwise>

                                            <c:set var="jaSolicitou" value="false" />
                                            <c:forEach  var="solicitador" items="${viagem.solicitadores}">

                                                <c:if test="${solicitador.email eq usuario.email}">
                                                    <c:set  var="jaSolicitou" value="true" />
                                                </c:if>
                                            </c:forEach>

                                            <c:forEach var="passageiro" items="${viagem.passageiros}">
                                                <c:if test="${passageiro.email eq usuario.email}">
                                                    <c:set value="true" var="jaSolicitou" />
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${jaSolicitou eq 'false'}">

                                                <a class="waves-effect waves-light btn s12 alinhado"
                                                   href="front?command=SolicitaVaga&codViagem=${viagem.codigo}">Solicitar vaga</a>
                                                <hr>
                                            </c:if> 
                                        </c:otherwise>
                                    </c:choose>

                                </div>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </td>

                <td width="5%"></td>
                <td width="45%">
                    <h4>Solicitações de amizade</h4>
                    <hr>
                    <c:forEach var="solicitacao" items="${solicitacoes}">
                        <c:if test="${(solicitacao.tipo eq 'amizade') && 
                                      (solicitacao.situacao eq 'pendente') 
                                      && (solicitacao.amigo.email eq usuario.email)}">
                              <div class="solicitacoes">
                                  <h5><b>${solicitacao.usuario.nome}</b></h5>
                                  <a 
                                      href="front?command=BuscaUsuario&buscado=${solicitacao.usuario.nome}">Ver perfil</a>

                                  <a  class="waves-effect waves-light btn s12 alinhado"
                                      href="front?command=AceitarSolicitacao&tipoSolicitacao=${solicitacao.tipo}&solicitador=${solicitacao.usuario.email}&requisitado=${solicitacao.amigo.email}">
                                      <i class="material-icons left">thumb_up</i>Aceitar</a>

                                  <a href="front?command=RecusarSolicitacao&tipoSolicitacao=${solicitacao.tipo}&solicitador=${solicitacao.usuario.email}&requisitado=${solicitacao.amigo.email}"
                                     class="waves-effect waves-light btn s12 alinhado cancelar">
                                      <i class="material-icons left">thumb_down</i>Recusar</a>
                                  <hr>
                              </div>    
                        </c:if>
                    </c:forEach>
                    <br><br>
                    <h4>Solicitações de seguir</h4>
                    <hr>
                    <c:forEach var="solicitacao" items="${solicitacoes}">
                        <c:if test="${(solicitacao.tipo eq 'seguir') && (solicitacao.situacao eq 'pendente')
                                      && (solicitacao.amigo.email eq usuario.email)}">
                              <div class="solicitacoes">

                                  <h5><b>${solicitacao.usuario.nome}</b></h5>

                                  <a href="front?command=BuscaUsuario&buscado=${solicitacao.usuario.nome}">Ver perfil</a>

                                  <a href="front?command=AceitarSolicitacao&tipoSolicitacao=${solicitacao.tipo}&solicitador=${solicitacao.usuario.email}&requisitado=${solicitacao.amigo.email}"
                                     class="waves-effect waves-light btn s12 alinhado">
                                      <i class="material-icons left">thumb_up</i>Aceitar</a>

                                  <a  href="front?command=RecusarSolicitacao&tipoSolicitacao=${solicitacao.tipo}&solicitador=${solicitacao.usuario.email}&requisitado=${solicitacao.amigo.email}"
                                      class="waves-effect waves-light btn s12 alinhado cancelar">
                                      <i class="material-icons left">thumb_down</i>Recusar</a>
                                  <hr>
                              </div>    
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
        </table>
    </body>

    <script>
        var erro = "${param.erroBuscaCarona}";
        if (erro == "1") {
            swal("Infelizmente...", "Não encontramos nenhuma carona para este lugar.", "warning");
        }

        var mensagem = "${param.mensagem}";
        if (mensagem == "1") {
            swal("Parabéns!", "A solicitação foi realizada", "success");

        }

    </script>
</html>