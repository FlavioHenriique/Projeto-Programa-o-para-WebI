<%@page import="com.ifpb.sisride.modelo.*, java.io.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"  %>


<html>
    <head>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
        <link rel="stylesheet" href="CSS/app.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8"/>
        <title>SisRide - Página inicial</title>
    </head>
    <body>
        <%@include file="menu.jsp"%>
        <br> 

        <table width="100%">
            <tr>
                <td width="50%">
                    <div class="row">
                        <img src="foto?command=FotoUsuario" class="circle">

                        <label id="nome">${usuario.nome}</label>
                    </div>            
                </td>
                <td width="50%">
                    <div class="row" id="busca" width="100%">
                        <form method="get" action="front">

                            <input type="hidden" name="command" value="BuscaUsuario">

                            <input type="text" name="buscado"  placeholder="Buscar usuários">


                            <button type="submit" class="waves-effect waves-light btn" >Buscar</button>

                        </form>
                    </div>
                    <div>

                    </div>
                </td>
            </tr>
        </table>

        <br><br>    
        <a href="perfil.jsp"><button class="waves-effect waves-light btn s12">Editar perfil</button></a><br><br>
    </body>

    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script>
        var mensagem = "${param.mensagem}";
        if(mensagem == "1"){
            swal("Parabéns!","Seu perfil foi atualizado","success");
        }
    </script>
</html>


