<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<main class="login-main">

    <form action="/login" method="post" class="login-form">
        <legend>Log in</legend>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <button class="btn-blue" type="submit">Login</button>
    </form>
</main>

<jsp:include page="footer.jsp" />
