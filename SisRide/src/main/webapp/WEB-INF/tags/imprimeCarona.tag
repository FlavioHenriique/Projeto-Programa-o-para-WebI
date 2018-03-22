<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="viagem" required="true"  type="com.ifpb.sisride.modelo.Viagem"%>

<div class="solicitacoes">
    <h5><b>${viagem.partida.nome} - ${viagem.destino.nome}</b></h5>

    <br>Data da viagem: ${viagem.data}, às ${viagem.hora} horas
    <br> Há <b>${viagem.vagas} vagas</b> disponíveis
    <br>O nível de conversa desejado é ${viagem.conversa},
    a música desejada é ${viagem.musica},
    <c:choose>
        <c:when test="${viagem.fumar eq true}">
            é permitido fumar
        </c:when>
        <c:otherwise>
            não é permitido fumar
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${viagem.animais eq true}">
            e é permitido levar animais.
        </c:when>
        <c:otherwise>
            e não é permitido levar animais.
        </c:otherwise>
    </c:choose>
    <br>O carro utilizado será um ${viagem.carro.modelo} ${viagem.carro.ano}
    <c:choose>
        <c:when test="${viagem.carro.ar_condicionado eq true}">
            com ar-condicionado.
        </c:when>
        <c:otherwise>
            sem ar-condicionado.
        </c:otherwise>
    </c:choose>
    
       <br>