<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="usuario" required="true"  type="com.ifpb.sisride.modelo.Usuario"%>

<c:choose>
    <c:when test="${usuario.foto2 != null}">
        <img src="front?command=FotoUsuario" class="circle">
    </c:when>
    <c:otherwise>
        <img src="Imagens/user.png" class="circle">
    </c:otherwise>
</c:choose>
<label id="nome">${usuario.nome}</label><br><br>

<label>É ${usuario.profissao}</label>
<br><label>Nasceu em ${usuario.nascimento}</label>
<br><label>Mora em ${usuario.cidade}</label>
<br><br>
<h5>Nota como motorista: ${usuario.nota_motorista}</h5>
<h5>Nota como passageiro: ${usuario.nota_passageiro}</h5>