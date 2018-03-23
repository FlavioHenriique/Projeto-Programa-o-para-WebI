<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="usuario" required="true"  type="com.ifpb.sisride.modelo.Usuario"%>


<label>Profissão: ${usuario.profissao}</label>
<br><label>Data de nascimento: ${usuario.nascimento}</label>
<br><label> Cidade: ${usuario.cidade}</label>
<br><br>
<h5>Nota como motorista: ${usuario.nota_motorista}</h5>
<h5>Nota como passageiro: ${usuario.nota_passageiro}</h5>