<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="user" required="true"  type="com.ifpb.sisride.modelo.Usuario"%>

<c:choose>
    <c:when test="${user.foto2 != null}">
        <img src="front?command=FotoUsuario" class="circle">
    </c:when>
    <c:otherwise>
        <img src="Imagens/user.png" class="circle">
    </c:otherwise>
</c:choose>
<label id="nome">${user.nome}</label><br><br>

<label>É ${user.profissao}</label>
<br><label>Nasceu em ${user.nascimento}</label>
<br><label>Mora em ${user.cidade}</label>
<br><br>
<h5>Nota como motorista: ${user.nota_motorista}</h5>
<h5>Nota como passageiro: ${user.nota_passageiro}</h5>