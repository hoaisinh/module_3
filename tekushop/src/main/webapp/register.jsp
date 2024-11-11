<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<main>
    <div class="register">
        <h2>Register</h2>
        <form action="register" method="post">
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>
        <div class="form-group">
            <label for="username">Username</label>
            <c:if test="${not empty username}">
                <input class="form-control form-control-sm" value="${username}" type="text" id="username" name="username" required />
            </c:if>
            <c:if test="${empty username}">
                <input class="form-control form-control-sm"  value="" type="text" id="username" name="username" required />
            </c:if>

        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <c:if test="${not empty password}">
                <input class="form-control form-control-sm"  value="${password}" type="password" id="password" name="password" required />
            </c:if>
            <c:if test="${empty password}">
                <input class="form-control form-control-sm"  value="" type="password" id="password" name="password" required />
            </c:if>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <c:if test="${not empty email}">
                <input class="form-control form-control-sm"  value="${email}" type="email" id="email" name="email" required />
            </c:if>
            <c:if test="${empty email}">
                <input class="form-control form-control-sm"  value="" type="email" id="email" name="email" required />
            </c:if>
        </div>
            <button type="submit" class="btn btn-primary mt-2">Register</button>
        </form>
    </div>

</main>
<jsp:include page="footer.jsp" />