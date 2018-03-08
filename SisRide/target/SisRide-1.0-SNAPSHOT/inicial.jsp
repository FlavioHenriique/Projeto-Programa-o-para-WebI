<%@page import="com.ifpb.sisride.modelo.*, java.io.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"  %>


<%
    Usuario atual = (Usuario) session.getAttribute("usuario");
%>

<html>
    <head>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
        <link rel="stylesheet" href="CSS/app.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8"/>
        <title>SisRide</title>
    </head>
    <body>
        <%@include file="menu.jsp"%>
        <br> 
        <div>
            <img src="foto" class="circle">

            <label><%= atual.getNome()%></label>
        </div>
        <br><br>
        <a href="perfil.jsp"><button class="waves-effect waves-light btn s12">Editar perfil</button></a>
    </body>

    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
</html>


