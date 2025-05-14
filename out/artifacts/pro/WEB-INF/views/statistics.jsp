<%@ include file="init.jsp" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${scoreTitle}</title>
</head>
<body>

    <%@ include file="header-footer/header.jsp" %>

    <main class="score-wrapper">
        <h1 class="score-title">${scoreTitle}</h1>
        <hr/><br/>

        <div class="score-back">
            <a href="${pageContext.request.contextPath}/HomeServlet">
                <i class="fas fa-arrow-left"></i> ${back}
            </a>
        </div>
        <br/>

        <ul class="score-list">
            <li>${lvl3}</li>
            <li>${lvl4}</li>
            <li>${lvl5}</li>
            <li>${lvl6}</li>
            <li>${lvl7}</li>
        </ul>

        <p class="score-bonus">${bonus}</p>
    </main>

    <%@ include file="header-footer/footer.jsp" %>

</body>
</html>