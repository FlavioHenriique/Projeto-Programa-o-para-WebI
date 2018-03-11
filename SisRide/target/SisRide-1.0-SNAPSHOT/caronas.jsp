
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
        <link rel="stylesheet" href="CSS/app.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8"/>
        <title>Minhas caronas</title>

        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
    </head>

    <body>
        <%@include file="menu.jsp"%>
    <center>
        <table width="100%">
            <tr>
                <td>
                    <form method="post" action="front">
                        <div class="row">
                            <div class="input-field col l4  offset-l4">
                                <h3>Cadastrar caronas</h3>
                            </div>
                        </div>
                    </form>
                </td>
                <td>

                </td>
            </tr>
        </table>
    </center>
</body>
</html>
