<%@ include file="../init.jsp" %>
<div class="found-words-panel">
    <div class="score">${scoreText}: <strong>${score} pts.</strong></div>

    <label for="foundWordsSearch">${foundWordsTitle}</label>
    <div class="search-box">
        <i class="fas fa-search"></i>
        <input type="text" id="foundWordsSearch" placeholder="${foundWordsPlaceholder}">
    </div>

    <ul class="found-list">
        <c:forEach var="palabra" items="${foundWords}">
            <li>${palabra}</li>
        </c:forEach>
    </ul>
</div>