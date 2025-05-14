<%@ include file="init.jsp" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${statsTitle}</title>
</head>
<body>

<%@ include file="header-footer/header.jsp" %>

<main class="score-wrapper">
    <h1 class="score-title">${statsTitle}</h1>
    
    <div class="score-back">
        <a href="${pageContext.request.contextPath}/HomeServlet">
            <i class="fas fa-arrow-left"></i> ${back}
        </a>
    </div>

    <p>${totalGamesLabel}: ${totalGames}</p>
    <p>${averageScoreLabel}: ${averageScore}</p>

    <h2>${scoreListTitle}:</h2>
    <ul class="score-list">
    	<c:forEach var="game" items="${scoreList}">
        	<li>${fn:length(scoreList)} . ${game.score.totalPoints} ${pointsSuffix}</li>
    	</c:forEach>
	</ul>
</main>

<%@ include file="header-footer/footer.jsp" %>

</body>
</html>