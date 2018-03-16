<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <td width="30%">
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
        <td width="50%">
        <center>
            <h4>Minhas caronas</h4>
        </center>

    </td>
</tr>
</table>

</body>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>

<script src="JS/app.js"></script>
</html>
