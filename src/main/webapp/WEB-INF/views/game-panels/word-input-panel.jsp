<div class="word-input-panel">
	<form action="${pageContext.request.contextPath}/game" method="post">
        <input type="text" name="palabra" placeholder="${wordInputPlaceholder}" required />
        <button type="submit" class="submit-button">${wordInputButton}</button>
    </form>
</div>