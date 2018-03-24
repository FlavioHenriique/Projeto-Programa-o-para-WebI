<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="viagem" required="true"  type="com.ifpb.sisride.modelo.Viagem"%>

<h5>Avaliação de motorista</h5><br>


<c:if test="${empty minhasAvaliacoes}">
    <c:set var="vazio" value="true" />

    <form method="post" action="front">

        <input type="hidden" name="command" value="AvaliarUsuario">
        <input type="hidden" name="viagem" value="${viagem.codigo}">
        <input type="hidden" name="avaliado" value="${viagem.motorista.email}">
        <input type="hidden" name="tipo" value="motorista">
        Motorista: ${viagem.motorista.nome}<br>
        <br>
        <input type="text" placeholder="Determine a nota" name="nota"><br>
        <textarea name="comentario" class="materialize-textarea" 
                  placeholder="Digite um comentário sobre este passageiro..."></textarea>
        <br>
        <center>
            <button type="submit" class="waves-effect waves-light btn s12" >Avaliar motorista</button>
        </center>
    </form>
</c:if>


<c:set var="jaAvaliou" value="false" />

<c:forEach var="avaliacao" items="${minhasAvaliacoes}">
    <c:if test="${avaliacao.usuarioAvaliado.email eq viagem.motorista.email && 
                  avaliacao.tipo eq 'motorista' && avaliacao.viagem eq viagem.codigo}">
        <c:set var="jaAvaliou" value="true" />
        <c:set var="avaliacaoRealizada" value="${avaliacao}" />
    </c:if>
</c:forEach>

<c:choose>
    <c:when test="${jaAvaliou eq 'true'}">
        <form method="post" action="front">
            <input type="hidden" name="avaliado" value="${viagem.motorista.email}">
            <input type="hidden" name="tipo" value="motorista">
            <input type="hidden" name="viagem" value="${viagem.codigo}">
            <input type="hidden" name="command" value="AtualizarAvaliacao">
            Motorista: ${viagem.motorista.nome}<br>
            <br>
            <input type="hidden" name="codigo" value="${avaliacaoRealizada.codigo}">
            <input type="text" placeholder="Determine a nota" name="nota" value="${avaliacaoRealizada.nota}"><br>
            <textarea name="comentario" class="materialize-textarea" 
                      placeholder="Digite um comentário sobre este passageiro...">${avaliacaoRealizada.comentario}
            </textarea>
            <center>
                <button type="submit" class="waves-effect waves-light btn s12" >Atualizar avaliação</button>
            </center>

        </form>
        <center>
            <a class="waves-effect waves-light btn s12 cancelar" 
               href="front?command=CancelaAvaliacao&codigo=${avaliacaoRealizada.codigo}">Cancelar avaliação</a>
        </center>
    </c:when>
    <c:otherwise>
        <c:if test="${vazio != 'true'}">
            <form method="post" action="front">

                <input type="hidden" name="command" value="AvaliarUsuario">
                <input type="hidden" name="avaliado" value="${viagem.motorista.email}">
                <input type="hidden" name="viagem" value="${viagem.codigo}">
                <input type="hidden" name="tipo" value="motorista">
                Motorista: ${viagem.motorista.nome}<br>
                <br>
                <input type="text" placeholder="Determine a nota" name="nota"><br>
                <textarea name="comentario" class="materialize-textarea" 
                          placeholder="Digite um comentário sobre este passageiro..."></textarea>
                <br>
                <center>
                    <button type="submit" class="waves-effect waves-light btn s12" >Avaliar motorista</button>
                </center>
            </form>
        </c:if>
    </c:otherwise>
</c:choose>
