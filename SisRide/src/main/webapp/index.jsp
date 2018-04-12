<%@page contentType="text/html" pageEncoding="UTF-8"  %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
        <link rel="stylesheet" href="CSS/app.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8"/>
        
        <title>SisRide</title>
    </head>
    <body>
    <center>
        <br><br><br><br>
        <img src="Imagens/logo.png" class="logo"><br><br>
        <form method="post" action="front" class="col s12 l4">

            <div class="row">
                <div class="input-field col l4  offset-l4">
                    <i class="material-icons prefix">account_circle</i>
                    <input id="icon_prefix" type="text" class="validate" placeholder="Email" name="email" >
                </div>
                <br><br>
                <div class="input-field col  l4 offset-l4">
                    <i class="material-icons prefix">lock</i>
                    <input id="icon_prefix" type="password" class="validate" placeholder="Senha" name="senha">
                </div>
                <br>
                <input type="hidden" name="command" value="Login">
            </div>    
            <Br>
            <button type="submit" class="waves-effect waves-light btn s12">
             Entrar</button>


        </form>
        <br>
        Nao possui conta? <a href="CadastroUsuario.jsp"> clique aqui para se cadastrar</a>
    </center>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script>
    var erro = "${param.erro}";

    if (erro == 1) {
        swal("Erro!", "Usuário não encontrado!", "error");
    }
    var mensagem = "${param.mensagem}";
    if(mensagem == "1"){
        swal("OK!","A conta foi excluida.","success");
    }
</script>
</html>
