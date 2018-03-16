
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
        <link rel="stylesheet" href="CSS/app.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8"/>
        <title>Cadastro de Usuário</title>
    </head>
    <body>
    <center>

        <h2 class="row col 12">Cadastre-se aqui</h2>
        <br>
        <form method="post" action="front" enctype="multipart/form-data" >

            <div class="row">
                <div  class="input-field col l4  offset-l4">
                    <input type="file" name="foto" ><br><br>
                    <input type="text" class="validate" name="nome" placeholder="Nome"> <br>
                    <input type="hidden" value="CadastroUsuario" name="command" ><br>
                    <input type="text" class="validate" name="email" placeholder="Email"> <br><br>
                    <input type="password" class="validate" name="senha" placeholder="Senha"><br><br>
                    <input type="text" name="cidade" class="validate" placeholder="Cidade"><br><br>
                    <input type="text" class="validate" name="profissao" placeholder="Profissão"><br><br>
                    <input type="text" class="datepicker" placeholder="Data de nascimento" name="data"><br><br>
                </div>
            </div>
            <input name="sexo" type="radio" id="masc" value="Masculino" class="validate"/>
            <label for="masc">Masculino</label>

            <input name="sexo" type="radio" id="fem"  value="Feminino" class="row"/>
            <label for="fem">Feminino</label><br><br>
            <div>
                <button type="submit" class="waves-effect waves-light btn s12">Cadastrar</button>
                <a href="index.jsp">Voltar</a>
            </div>

        </form>
    </center>       
</body>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<script src="JS/app.js"></script>
<script>
    var erro = "${param.erroCadastroUsuario}";
    if (erro == "1") {
        swal("Erro!!", "Preencha todos os campos", "error");
    }
    if (erro == "2") {
        swal("Erro!!","Este email já está sendo utilizado","error");
    }
</script>
</html>