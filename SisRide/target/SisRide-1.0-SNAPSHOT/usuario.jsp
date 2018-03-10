
<%@page import="com.ifpb.sisride.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Usuario atual = (Usuario) session.getAttribute("usuario");
    Usuario buscado = (Usuario) session.getAttribute("buscado");
%>

<html>
    <head>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
        <link rel="stylesheet" href="CSS/app.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8"/>

        <title><%= buscado.getNome()%></title>
    </head>
    <body>

        <%@include file="menu.jsp"%>

        <table>
            <tr>
                <td>
                    <div class="row">
                        <img src="foto?command=FotoBuscado" class="circle">

                        <label id="nome"><%= buscado.getNome()%></label>
                    </div>
                    <label>Profissão: <%=  buscado.getProfissao()%></label>
                    <br><label>Data de nascimento: <%= buscado.getNascimento()%></label>
                    <br><label> Cidade: <%= buscado.getCidade()%></label>
                </td>

                <td>
                    <a href="usuario?command=Solicitacao&tipo=amizade"><button 
                            class="waves-effect waves-light btn s12">Solicitar amizade</button></a>
                </td>
            </tr>
        </table>
    </body>


    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
</html>
