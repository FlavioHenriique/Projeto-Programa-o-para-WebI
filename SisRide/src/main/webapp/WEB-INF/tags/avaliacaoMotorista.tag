<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="viagem" required="true"  type="com.ifpb.sisride.modelo.Viagem"%>

<h5>Avaliação de motorista</h5><br>
<c:forEach var="avaliacao" items="${minhasAvaliacoes}">

    <c:choose>
        <c:when test="${avaliacao.usuarioAvaliado.email eq viagem.motorista.email && avaliacao.tipo eq 'motorista'}">
            <form method="post" action="front">
                <input type="hidden" name="avaliado" value="${viagem.motorista.email}">
                <input type="hidden" name="tipo" value="motorista">
                <input type="hidden" name="command" value="AtualizarAvaliacao">
                Motorista: ${viagem.motorista.nome}<br>
                <br>
                <input type="hidden" name="codigo" value="${avaliacao.codigo}">
                <input type="text" placeholder="Determine a nota" name="nota" value="${avaliacao.nota}"><br>
                <textarea name="comentario" class="materialize-textarea" 
                          placeholder="Digite um comentário sobre este passageiro..." value="${avaliacao.comentario}">
                </textarea>
                <center>
                    <button type="submit" class="waves-effect waves-light btn s12" >Atualizar avaliação</button>
                </center>

            </form>
            <center>
                <a class="waves-effect waves-light btn s12 cancelar" 
                   href="front?command=CancelaAvaliacao&codigo=${avaliacao.codigo}">Cancelar avaliação</a>
            </center>
        </c:when>
        <c:otherwise>
          
            <form method="post" action="front">
                <input type="hidden" name="command" value="AvaliarUsuario">
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
        </c:otherwise>

    </c:choose>
</c:forEach>

<c:if test="${empty minhasAvaliacoes}">
    <form method="post" action="front">
        <input type="hidden" name="command" value="AvaliarUsuario">
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
