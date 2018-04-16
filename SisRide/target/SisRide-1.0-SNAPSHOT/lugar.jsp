<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.ifpb.sisride.modelo.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    Usuario atual = (Usuario) session.getAttribute("usuario");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
        <link rel="stylesheet" href="CSS/app.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8"/>

        <title>SisRide - Lugares</title>
    </head>
    <body>

        <%@include file="menu.jsp"%>

        <table>
            <tr>
                <td width="40%">
            <center>
                <c:set value="false" var="atualizar" />

                <c:if test="${param.lugarAtualizado != null}">
                    <c:forEach var="lugar" items="${MeusLugares}">
                        <c:if test="${param.lugarAtualizado == lugar.identificacao}">
                            <c:set var="atualizado" value="${lugar}" scope="page" />
                            <c:set value="true" var="atualizar" />
                        </c:if>
                    </c:forEach>
                </c:if>

                <c:choose>
                    <c:when test="${atualizar eq 'true'}">
                        <h4>Atualizar lugar</h4>
                        <form method="post" action="front" class="col s12 l4">
                            <fieldset class="campo">
                                <div>
                                    <input type="text" name="nome" placeholder="Nome do lugar" class="validate" value="${atualizado.nome}"><br>
                                    <input type="number" name="numero" placeholder="Número" class="validate" min="0" value="${atualizado.numero}"><br>
                                    <input type="text" name="rua" placeholder="Rua" class="validate" value="${atualizado.rua}"><br>
                                    <input type="text" name="cidade" placeholder="Cidade" class="validate" value="${atualizado.cidade}"><br>
                                    <input type="hidden" name="command" value="AtualizaLugar"/>
                                    <input type="text" name="estado" placeholder="Estado" class="validate" value="${atualizado.estado}"><br>
                                    <input type="hidden" name="email" value="${atualizado.emailUsuario}">
                                    <input type="hidden" name="id" value="${atualizado.identificacao}"> 
                                    <textarea placeholder="Descrição do lugar" name="descricao" 
                                              rows="5" class="validate materialize-textarea">${atualizado.descricao}</textarea><br><br>
                                    <button type="submit" class="waves-effect waves-light btn s12">Atualizar</button>
                                </div>
                            </fieldset>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <div>
                            <h4>Cadastrar lugar</h4><br>
                            <form method="post" action="front"  class="col s12 l4">
                                <fieldset class="campo">
                                    <div>
                                        <input type="text" name="nome" placeholder="Nome do lugar" class="validate"><br>
                                        <input type="number" name="numero" placeholder="Número" class="validate" min="0"><br>
                                        <input type="text" name="rua" placeholder="Rua" class="validate"><br>
                                        <input type="text" name="cidade" placeholder="Cidade" class="validate"><br>
                                        <input type="hidden" name="command" value="CadastroLugar"/>
                                        <input type="text" name="estado" placeholder="Estado" class="validate"><br>
                                        <textarea placeholder="Descrição do lugar" name="descricao" 
                                                  rows="5" class="validate materialize-textarea""></textarea><br><br>
                                        <button type="submit" class="waves-effect waves-light btn s12">Cadastrar</button>
                                    </div>
                                </fieldset>
                            </form>

                        </div>

                    </c:otherwise>
                </c:choose>


            </center>
        </td>
        <td width="5%"></td>
        <td width="45%">
            <h4 align="top">Lugares cadastrados por mim </h4>
            <br>
            <c:if test="${empty MeusLugares}">
                Você não cadastrou nenhum lugar
            </c:if>
            <c:forEach var="lugar" items="${MeusLugares}">
                <div class="campo">
                    <h5><b>${lugar.nome}</b></h5>
                    ${lugar.descricao}<br>
                    Rua ${lugar.rua}, ${lugar.cidade}, ${lugar.estado}
                    <br>
                    <a href="front?command=MeusLugares&lugarAtualizado=${lugar.identificacao}"
                       class="waves-effect waves-light btn s12 alinhado">Atualizar</a>
                         <a href="front?command=DeletaLugar&lugar=${lugar.identificacao}"
                       class="waves-effect waves-light btn s12 alinhado cancelar">Deletar</a>
                       <br>
                </div>
                <br>
            </c:forEach>

        </td>
    </tr>
</table>
</body>


<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
<script src="JS/app.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script>
    var erro = "${param.erroCadastroLugar}";
    if (erro == "1") {
        swal("Erro!", "Preencha todos os campos", "error");
    }

    var mensagem = "${param.mensagem}";
    if (mensagem == "1") {
        swal("Parabéns!", "O lugar foi cadastrado com sucesso", "success");
    }
    if (mensagem == "2") {
        swal("OK!", "O lugar foi deletado com sucesso", "success");
    }
    if(mensagem == "3"){
        swal("OK!","O lugar foi atualizado com sucesso","success");
    }
</script>
</html>
