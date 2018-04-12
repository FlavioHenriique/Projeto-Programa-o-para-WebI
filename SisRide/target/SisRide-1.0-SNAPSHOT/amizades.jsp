
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
        <link rel="stylesheet" href="CSS/app.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8"/>

        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
        <title>Sisride - Amizades</title>
    </head>
    <body>
        <%@include file="menu.jsp"%>
        <br><br>

        <c:choose>
            <c:when test="${empty amigos}">
                <h4>Você não tem nenhum amigo</h4>
            </c:when>

            <c:otherwise>
                <table width="100%">
                    <tr>
                        <td width="30%">
                            <h5>Lista de amigos</h5><br>
                            <c:forEach var="amigo" items="${amigos}">
                                <div class="solicitacoes">
                                    <c:choose>
                                        <c:when test="${amigo.foto2 != null}">
                                            <img src="front?command=ImprimeFoto&amigo=${amigo.email}" class="circle amizade">        
                                        </c:when>
                                        <c:otherwise>
                                            <img src="Imagens/user.png" class="circle amizade">
                                        </c:otherwise>
                                    </c:choose>
                                            
                                    <a  href="front?command=ListarAmigos&amigo=${amigo.email}" class="amizade">
                                        ${amigo.nome}</a>
                                    <hr>
                                </div>
                            </c:forEach>
                        </td>
                        <td width="5%"></td>
                        <td width="60%">
                            <br><br>

                            <c:if test="${param.amigo != null}">
                                <div class="solicitacoes">
                                    <c:choose>
                                        <c:when test="${amigo.foto2 != null}">
                                            <img src="front?command=ImprimeFoto&amigo=${amigo.email}" class="circle amizade">        

                                        </c:when>
                                        <c:otherwise>
                                            <img src="Imagens/user.png" class="circle amizade">
                                        </c:otherwise>
                                    </c:choose>
                                    <label>${amigo.nome}</label>
                                    <hr>
                                </div>
                                    
                                    <!-- Imprimindo as mensagens trocadas entre os dois usuários-->
                                <c:if test="${!empty mensagens}">
                                    <div class=" campo div_chat">
                                        <c:forEach var="mensagem" items="${mensagens}">
                                            <c:choose>
                                                <c:when test="${mensagem.usuario eq usuario.email}">
                                                    <label id="mensagem_usuario"><label>${mensagem.mensagem}</label></label>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:choose>
                                                        <c:when test="${amigo.foto2 != null}">
                                                            <img src="front?command=ImprimeFoto&amigo=${mensagem.usuario}" class="circle mensagem">        

                                                        </c:when>
                                                        <c:otherwise>
                                                            <img src="Imagens/user.png" class="circle mensagem">
                                                        </c:otherwise>
                                                    </c:choose>  
                                                    <label id="mensagem_amigo">${mensagem.mensagem}</label>
                                                    <br>
                                                </c:otherwise>
                                            </c:choose>
                                            <br>
                                        </c:forEach>
                                    </div>    
                                </c:if>

                                <br><br>
                                <form method="post" action="front" class="row">
                                    <input type="hidden" name="command" value="EnviarMensagem" >
                                    <input type="hidden" name="amigo" value="${param.amigo}">
                                    <textarea  class="materialize-textarea input-field col s11 mensagem" name="mensagem"
                                               placeholder="Digite uma mensagem..." maxlength="70"></textarea>
                                  
                                    <button class="waves-effect waves-light btn  input-field col s1">
                                        <i class="material-icons">send</i></button>
                                </form>    
                            </c:if>
                        </td>
                        <td width="5%"></td>
                    </tr>
                </table>
            </c:otherwise>
        </c:choose>

    </body>
</html>