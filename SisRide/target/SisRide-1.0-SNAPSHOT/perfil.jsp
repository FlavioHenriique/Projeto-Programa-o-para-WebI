
<%@page import="com.ifpb.sisride.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
        <link rel="stylesheet" href="CSS/app.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8"/>

        <title>SisRide - Editar perfil</title>
    </head>
    <body>
        <%@include file="menu.jsp"%>

    <center>
        <h4>Editar perfil</h4>
        <form method="post"  enctype="multipart/form-data" action="front">

            <div class="row">
                <div class="input-field col s14 offset-l4">
                    <br>
                    <input type="file" name="foto2" placeholder="selecione sua foto..."><br><br>

                    <input type="text" class="validate" name="nome" placeholder="Nome"
                           value ="${usuario.nome}"> <br>

                    <input type="hidden" value="AtualizaUsuario" name="command" ><br>

                    <input type="password" class="validate" name="senha" placeholder="Senha"
                           value="${usuario.senha}"><br><br>
                    <input type="text" name="cidade" class="validate" placeholder="Cidade"
                           value="${usuario.cidade}"><br><br>
                    <input type="text" class="validate" name="profissao" placeholder="Profissão"
                           value="${usuario.profissao}"><br><br>
                    <input type="text" class="datepicker" placeholder="Data de nascimento" name="data"><br><br>

                </div>
            </div>
            <input name="sexo" type="radio" id="masc" value="Masculino" class="validate"/>
            <label for="masc">Masculino</label>

            <input name="sexo" type="radio" id="fem"  value="Feminino" class="row"/>
            <label for="fem">Feminino</label><br><br>
            <div>
                <button type="submit" class="waves-effect waves-light btn s12">
                    <i class="material-icons right">send</i>Atualizar perfil</button>
            </div>
        </form>
        <div>
            <br>
            <a href="front?command=ExcluirConta"><button class="waves-effect waves-light btn s12 cancelar">
                    <i class="material-icons right">cancel</i>
                    Excluir conta</button></a>
            <a href="inicial.jsp"><button class="waves-effect waves-light btn s12 cancelar">Cancelar</button></a>
        </div>
    </center>
    <br><br>

</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<script src="JS/app.js"></script>
<script>
    var erro = "${param.erroPerfilUsuario}";
    if (erro == "1") {
        swal("Erro!!", "Preencha todos os campos", "error");
    }

</script>
</html>
