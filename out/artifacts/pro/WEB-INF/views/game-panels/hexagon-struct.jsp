<%@ include file="../init.jsp" %>
<div class="hexagon-container">
    <div class="hex-row">
        <c:forEach var="l" items="${fila1}">
            <div class="hex"><span>${l}</span></div>
        </c:forEach>
    </div>
    <div class="hex-row">
        <c:forEach var="l" items="${fila2}">
            <div class="hex"><span>${l}</span></div>
        </c:forEach>
    </div>
    <div class="hex-row">
        <c:forEach var="l" items="${fila3}">
            <div class="hex"><span>${l}</span></div>
        </c:forEach>
    </div>
</div>