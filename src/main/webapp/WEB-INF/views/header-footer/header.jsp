<%-- 
    Document   : index.jsp
    Created on : 05.05.2025, 17:49:32
    Author     : sebastianr
--%>

<%@ include file="../init.jsp" %>
<%@ include file="../init.jsp" %>

<header class="header">
	
	<div class="logo">
	    <a href="https://agora.xtec.cat/ieslaferreria/" target="_blank">
	        <img src="${pageContext.request.contextPath}/assets/imgs/iesferreria.png" alt="Logo" />
	    </a>
	    <span class="logo-text">Paraulogic</span>
	</div>

    <nav class="menu">
        <a href="${pageContext.request.contextPath}/HomeServlet">${game}</a>
        <a href="#">${hits}</a>
        <a href="#">${modes}</a>
        <a href="${pageContext.request.contextPath}/RulesServlet">${rules}</a>
        <a href="${pageContext.request.contextPath}/ScoreServlet">${score}</a>
        <a href="${pageContext.request.contextPath}/StatisticsServlet">${stats}</a>
        <a href="${pageContext.request.contextPath}/StatsServlet">${statistics}</a>
    </nav>

    <div class="actions">
        <form action="${pageContext.request.contextPath}/LanguageServlet" method="post" style="display:inline;">
            <select name="lang" onchange="this.form.submit()">
                <option value="es" ${sessionScope.lang == 'es' ? 'selected' : ''}>ES</option>
                <option value="en" ${sessionScope.lang == 'en' ? 'selected' : ''}>EN</option>
            </select>
        </form>
        <button class="icon-button" title="Modo claro">
            <i class="fas fa-sun"></i>
        </button>
        <button class="icon-button" title="Modo oscuro">
            <i class="fas fa-moon"></i>
        </button>
    </div>
</header>