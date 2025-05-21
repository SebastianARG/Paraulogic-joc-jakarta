<%-- 
    Document   : game
    Created on : 05.05.2025, 17:45:16
    Author     : sebastianr
--%>
<%@ include file="init.jsp" %>

<div class="game-layout">
    <!-- Panel de hexagonos -->
    <div class="hexagon-struct">
        <%@ include file="game-panels/hexagon-struct.jsp" %>
    </div>

    <!-- Panel derecho: palabras acertadas + timer + input -->
    <div class="right-panel">
        <%@ include file="game-panels/found-words-panel.jsp" %>
        <%@ include file="game-panels/timer-panel.jsp" %>
        <%@ include file="game-panels/word-input-panel.jsp" %>
    </div>
</div>
