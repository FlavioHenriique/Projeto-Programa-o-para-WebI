<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="viagem" required="true"  type="com.ifpb.sisride.modelo.Viagem"%>

<div class="campo">
    <h5><b>${viagem.partida.nome} - ${viagem.destino.nome}</b></h5>

    <br>Data da viagem: ${viagem.data}, �s ${viagem.hora} horas
    <br> H� <b>${viagem.vagas} vagas</b> dispon�veis
    <br>O n�vel de conversa desejado � ${viagem.conversa},
    a m�sica desejada � ${viagem.musica},
    <c:choose>
        <c:when test="${viagem.fumar eq true}">
            � permitido fumar
        </c:when>
        <c:otherwise>
            n�o � permitido fumar
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${viagem.animais eq true}">
            e � permitido levar animais.
        </c:when>
        <c:otherwise>
            e n�o � permitido levar animais.
        </c:otherwise>
    </c:choose>
    <br>O carro utilizado ser� um ${viagem.carro.modelo} ${viagem.carro.ano}
    <c:choose>
        <c:when test="${viagem.carro.ar_condicionado eq true}">
            com ar-condicionado.
        </c:when>
        <c:otherwise>
            sem ar-condicionado.
        </c:otherwise>
    </c:choose>
    
       <br>