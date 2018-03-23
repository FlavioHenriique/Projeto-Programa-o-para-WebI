<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="lista" required="true"  type="java.util.List"%>


<h5>Comentários dos usuários (motorista)</h5>

<c:forEach var="avaliacao" items="${lista}">
    <c:if test="${avaliacao.tipo eq 'motorista' && avaliacao.comentario != ''}">
        <div class="solicitacoes">
            <img src="front?command=ImprimeFoto&avaliador=${avaliacao.avaliador.email}" class="circle comentario">
            <b class="comentario">${avaliacao.avaliador.nome}</b><br>
            ${avaliacao.comentario}<hr>
        </div>
    </c:if>
</c:forEach>
<br>
<h5>Comentários dos usuários (passageiro)</h5>
<c:forEach var="avaliacao" items="${lista}">
    <c:if test="${avaliacao.tipo eq 'passageiro' && avaliacao.comentario != ''}">
        <div class="solicitacoes">
            <c:choose>
                <c:when test="${avaliacao.avaliador.foto2 eq null}">
                    <img src="Imagens/user.png" class="circle comentario">
                </c:when>
                <c:otherwise>
                    <img src="front?command=ImprimeFoto&avaliador=${avaliacao.avaliador.email}" class="circle comentario">
                </c:otherwise>
            </c:choose>

            <b class="comentario">${avaliacao.avaliador.nome}</b><br>
            ${avaliacao.comentario}<hr>
        </div>
    </c:if>
</c:forEach>
