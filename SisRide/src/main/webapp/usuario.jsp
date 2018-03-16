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
                <c:forEach var="solicitacao" items="${solicitacoes}">
                    <c:if test="${(solicitacao.usuario.email eq usuario.email) 
                                  && (solicitacao.amigo.email eq buscado.email)}">
                                      
                    </c:if>
                </c:forEach>
              
                <br><br>
                <a href="front?command=Solicitacao&tipo=seguir"><button 
                        class="waves-effect waves-light btn s12">Seguir</button></a>
            </center>
        </td>
    </tr>
</table>
</body>


<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
</html>
