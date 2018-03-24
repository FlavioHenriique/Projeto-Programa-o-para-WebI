<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="viagem" required="true"  type="com.ifpb.sisride.modelo.Viagem"%>

 <c:set var="jaAvaliei" value="false" />
 
<c:forEach var="avaliacao" items="${minhasAvaliacoes}">
    <c:if test="${avaliacao.usuarioAvaliado.email eq param.passageiro_avaliado  && avaliacao.tipo eq 'passageiro'
          && avaliacao.viagem eq viagem.codigo}">
        <c:set var="jaAvaliei" value="true" />
    </c:if>
</c:forEach>
 
 <c:choose>
     <c:when test="${jaAvaliei eq 'true'}">
         avaliei
     </c:when>
     <c:otherwise>
         
             
     </c:otherwise>
 </c:choose>