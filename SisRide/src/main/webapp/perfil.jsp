

<%@page import="com.ifpb.sisride.modelo.Usuario"%>
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

        <title>Editar perfil</title>
    </head>
    <body>
        <%@include file="menu.jsp"%>
    <center>
        <h3>Editar perfil</h3>



        <form method="post" action="usuario" enctype="multipart/form-data"  class="col s12 l4">

            <div class="row">
                <div  class="input-field col l4  offset-l4">

                    <input type="file" name="foto2"><br><br>

                    <input type="text" class="validate" name="nome" placeholder="Nome"
                           value ="<%= atual.getNome()%>"> <br>

                    <input type="hidden" value="AtualizaUsuario" name="command"><br>

                    <input type="password" class="validate" name="senha" placeholder="Senha"
                           value="<%=  atual.getSenha()%>"><br><br>
                    <input type="text" name="cidade" class="validate" placeholder="Cidade"
                           value="<%= atual.getCidade()%>"><br><br>
                    <input type="text" class="validate" name="profissao" placeholder="Profissão"
                           value="<%= atual.getProfissao()%>"><br><br>
                    <input type="text" class="datepicker" placeholder="Data de nascimento" name="data"><br><br>
                </div>
            </div>
            <input name="sexo" type="radio" id="masc" value="Masculino" class="validate"/>
            <label for="masc">Masculino</label>

            <input name="sexo" type="radio" id="fem"  value="Feminino" class="row"/>
            <label for="fem">Feminino</label><br><br>
            <div>
                <button type="submit" class="waves-effect waves-light btn s12">Atualizar perfil</button>
            </div>
        </form>
        <div>
            <a href="usuario?command=ExcluirConta"><button class="waves-effect waves-light btn s12">
                    Excluir conta</button></a>
            <a href="inicial.jsp"><button class="waves-effect waves-light btn s12">Cancelar</button></a>
        </div>

</center>
<br><br>

</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
<script src="JS/app.js"></script>

</html>
