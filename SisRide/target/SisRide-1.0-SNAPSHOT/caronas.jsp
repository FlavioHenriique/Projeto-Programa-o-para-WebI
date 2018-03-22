<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="minhasTags" %>
<html>
    <head>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
        <link rel="stylesheet" href="CSS/app.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8"/>
        <title>Minhas caronas</title>
    </head>
    <body>
        <%@include file="menu.jsp"%>


        <table width="100%">
            <tr>
                <td width="35%">
            <center>
                <h4>Cadastrar caronas</h4>
                <div class="row">
                    <div>
                        <form method="post" action="front">
                            <fieldset>
                                <input type="hidden" name="command" value="CadastroViagem">
                                <input type="text" class="datepicker" placeholder="Data da viagem" name="data"><br>
                                <input type="text" class="validate" name="vagas" placeholder="vagas"><br>                     
                                <input type="text" placeholder="Hora da viagem" name="hora" class="validate"><br>
                                <input type="text" placeholder="Valor por passagem" name="valor" class="validate"><br>
                                Permitido animais?
                                <input  type="radio" id="sim" name="animal" value="true" />
                                <label for="sim">Sim</label>

                                <input type="radio" name="animal" id="nao"  value="false" />
                                <label for="nao">Não</label>
                                <br><br>
                                Permitido fumar?
                                <input  type="radio" id="sim2" name="fumar" value="true" />
                                <label for="sim2">Sim</label>

                                <input type="radio" name="fumar" id="nao2"  value="false" />
                                <label for="nao2">Não</label>
                                <br><br>
                                <div>
                                    <select name="conversa">
                                        <option value="" disabled selected>Selecione o nível de conversa...</option>
                                        <option value="alto">Alto</option>
                                        <option value="médio">Médio</option>
                                        <option value="baixo">Baixo</option>
                                        <option value="nenhum">Nenhum</option>
                                    </select>
                                </div>
                                <br>
                                <input type="text" name="musica" class="validate" placeholder="Música desejada">
                                <br>
                                <select name="partida">
                                    <option value="" disabled selected>Selecione o lugar de partida...</option>
                                    <c:forEach var="lugar" items="${lugares}">
                                        <option value="${lugar.identificacao}">${lugar.nome}</option>
                                    </c:forEach>
                                </select>
                                <br>
                                <select name="destino">
                                    <option value="" disabled selected>Selecione o lugar de destino...</option>
                                    <c:forEach var="lugar" items="${lugares}">
                                        <option value="${lugar.identificacao}">${lugar.nome}</option>
                                    </c:forEach>
                                </select><br>
                                <input type="text" class="validate" name="modelo" placeholder="Modelo do carro"><br>
                                <br>
                                <input type="text" class="validate" name="ano" placeholder="Ano do carro"><br>
                                Seu carro possui ar-condicionado?
                                <input  type="radio" id="sim3" name="ar" value="true" />
                                <label for="sim3">Sim</label>

                                <input type="radio" name="ar" id="nao3"  value="false" />
                                <label for="nao3">Não</label><br><br><br>
                                <button class="waves-effect waves-light btn s12">Cadastrar carona</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </center>
        </td>
        <td width="5%"></td>
        <td width="60%">

            <h4>Minhas caronas</h4>
            <br>
            <c:if test="${empty minhasCaronas}">
                Você não cadastrou nenhuma carona
            </c:if>
            <c:forEach var="viagem" items="${minhasCaronas}">
            <minhasTags:imprimeCarona viagem="${viagem}" />
            <br>

            <ul class="collapsible" data-collapsible="accordion">
                <li>
                    <div class="collapsible-header"><i class="material-icons">screen_share</i>
                        Recomendar para um amigo</div>
                    <div class="collapsible-body">

                        <!-- Gerando lista com os amigos do usuário-->       
                        <c:choose>
                            <c:when test="${!empty amigos}">
                                <c:forEach var="amigo" items="${amigos}">
                                    <c:choose>
                                        <c:when test="${amigo.foto2 != null}">
                                            <img src="front?command=ImprimeFoto&amigo=${amigo.email}" class="circle recomendacao">        

                                        </c:when>
                                        <c:otherwise>
                                            <img src="Imagens/user.png" class="circle recomendacao">
                                        </c:otherwise>
                                    </c:choose>
                                    <a href="front?command=RecomendarCarona&carona=${viagem.codigo}&passageiro=${amigo.email}"
                                       class="recomendacao">${amigo.nome}</a>
                                    <hr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                Você não tem amigos
                            </c:otherwise>
                        </c:choose>
                    </div>
                </li>
            </ul>

            <a class="waves-effect waves-light btn s12 alinhado cancelar"
               href="front?command=CancelarCarona&codCarona=${viagem.codigo}&mensagem=3">Cancelar esta carona</a>
            <hr>
            </div>

            <br>
            <c:choose>
                <c:when test="${!empty viagem.solicitadores}">

                    <h5>Usuários que solicitaram vagas</h5>
                    <br>
                    <c:forEach var="solicitante" items="${viagem.solicitadores}">
                        <div class="solicitacoes">
                            <h5><b>${solicitante.nome}</b></h5>

                            <a href="front?command=BuscaUsuario&buscado=${solicitante.nome}">Ver perfil</a>
                            <br>
                            <a href="front?command=ConfirmaVaga&resposta=nao&codviagem=${viagem.codigo}&solicitante=${solicitante.email}"
                               class="waves-effect waves-light btn s12 alinhado cancelar">Recusar</a>
                            <a href="front?command=ConfirmaVaga&resposta=sim&codviagem=${viagem.codigo}&solicitante=${solicitante.email}"
                               class="waves-effect waves-light btn s12 alinhado ">Aceitar</a>
                            <hr>
                        </div><br>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <h5>Não existem solicitações de vagas para essa carona!</h5><br>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        </td>
        </tr>
    </table>
</body>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="JS/app.js"></script>
<script>
    var erro = "${param.erroCadastroCarona}";
    if (erro == "1") {
        swal("Erro!", "Preencha todos os campos", "error");
    }
    var mensagem = "${param.mensagem}";

    switch (mensagem) {
        case "1":
        {
            window.location.replace("front?command=ListarLugares");
            break;
        }
        case "3":
        {
            swal("OK!", "Esta carona foi cancelada.", "success");
            
            break;
        }
        case "4":
        {
            swal("OK!", "O usuário receberá uma notificação da sua decisão.", "success");
            break;
        }
        case "5":
        {
            swal("Ok!", "Seu amigo receberá a recomendação.", "success");
            break;
        }
    }
</script>
</html>
