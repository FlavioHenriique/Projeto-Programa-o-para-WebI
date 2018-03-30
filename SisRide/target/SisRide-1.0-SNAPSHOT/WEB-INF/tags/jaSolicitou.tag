<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="viagem" required="true"  type="com.ifpb.sisride.modelo.Viagem"%>
<%@attribute name="pagina" required="false" %>
<c:set var="jaSolicitou" value="false" />
<c:forEach  var="solicitador" items="${viagem.solicitadores}">

    <c:if test="${solicitador.email eq usuario.email}">
        <c:set  var="jaSolicitou" value="true" />
    </c:if>
</c:forEach>

<c:forEach var="passageiro" items="${viagem.passageiros}">
    <c:if test="${passageiro.email eq usuario.email}">
        <c:set value="true" var="jaSolicitou" />
    </c:if>
</c:forEach>


<c:if test="${jaSolicitou eq 'false' && viagem.vagas > 0}">


    <a class="waves-effect waves-light btn s12 alinhado"
       href="front?command=SolicitaVaga&codViagem=${viagem.codigo}&pagina=${pagina}">Solicitar vaga</a>
</c:if>