<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<main class="add-clothing w-25 m-auto">
    <form action="/user-management?action=edit" method="post" class="row">
        <legend>Edit User</legend>
        <c:if test="${errorType != null}">
            <c:if test="${errorType == 1}">
                <script>
                    alert("Not found user")
                    window.location = 'http://localhost:8080/user-management';
                </script>
            </c:if>

        </c:if>
        <c:if test="${error != null}">
            <p class="text-danger">${error}</p>
        </c:if>
        <c:if test="${success != null}">
            <p class="text-success text-center fs-5">${success}</p>
        </c:if>
        <input type="hidden" name="action" value="edit">
        <input type="hidden" name="id" value="${tempUser.id}">
        <div class="form-group col-12">
            <label for="username">Username</label>
            <c:if test="${tempUser != null}">
                <input type="text" name="username" id="username" class="form-control form-control-sm" value="${tempUser.username}" required>
            </c:if>
            <c:if test="${tempUser == null}">
                <input type="text" name="username" id="username" class="form-control form-control-sm" required>
            </c:if>
        </div>
        <div class="form-group col-12">
            <label for="email">Email</label>
            <c:if test="${tempUser != null}">
                <input type="email" name="email" id="email" class="form-control form-control-sm" value="${tempUser.email}" required>
            </c:if>
            <c:if test="${tempUser == null}">
                <input type="email" name="email" id="email" class="form-control form-control-sm" required>
            </c:if>
        </div>
        <div class="form-group col-12">
            <label for="password">Password</label>

                <input type="password" name="password" id="password" class="form-control form-control-sm" >

        </div>
        <div class="form-group col-12">
            <label for="role">Role</label>
            <select name="role" id="role" class="form-control form-control-sm" required>
                <c:if test="${tempUser != null}">
                    <option value="${tempUser.role}" selected>${tempUser.role}</option>
                </c:if>
                <option value="customer">Customer</option>
                <option value="editor">Editor</option>
                <option value="admin">Admin</option>
            </select>
        </div>

        <button type="submit" value="Add User" class="mt-3 btn-blue col-2">Save</button>
    </form>
</main>
<jsp:include page="footer.jsp" />