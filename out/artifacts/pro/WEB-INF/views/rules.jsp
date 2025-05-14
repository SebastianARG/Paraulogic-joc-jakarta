<%@ include file="init.jsp" %>
<!DOCTYPE html>
<html lang="${pageContext.request.locale.language}">
<head>
    <meta charset="UTF-8">
    <title>${rulesTitle}</title>
</head>
<body>

    <%@ include file="header-footer/header.jsp" %>

    <main class="score-wrapper">
        <h1 class="score-title">${rulesTitle}</h1>
        <hr/><br/>

        <div class="score-back">
            <a href="${pageContext.request.contextPath}/HomeServlet">
                <i class="fas fa-arrow-left"></i> ${back}
            </a>
        </div>
        <br/>

        <ul class="score-list">
            <li>${rule1}</li>
            <li>${rule2}</li>
            <li>${rule3}</li>
            <li>${rule4}</li>
            <li>${rule5}</li>
        </ul>
    </main>

    <%@ include file="header-footer/footer.jsp" %>

</body>
</html>
