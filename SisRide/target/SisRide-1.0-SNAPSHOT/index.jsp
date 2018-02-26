
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
        
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>JSP Page</title>
    </head>
    <body>

    <center>
        <br><br><br>
        <h1>SisRide - Login</h1>
        <form>

            <div class="input-field col s6">
                <i class="material-icons prefix">account_circle</i>
                <input id="icon_prefix" type="text" class="validate" placeholder="Email">
            </div>
            <br><br>
            <div class="input-field col s6">
                <i class="material-icons prefix">lock</i>
                <input id="icon_prefix" type="password" class="validate" placeholder="Senha">
            </div>
            <br><Br>
            <input type="submit" value="Login"  class="waves-effect waves-light btn">
        </form>
    </center>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>


</html>
