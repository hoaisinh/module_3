<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<main class="admin-page">
    <h1>Admin Page</h1>
    <div class="row admin-content">
        <div class="col-4 admin-action">
            <h3>Action</h3>
            <a href="clothing-management" class="btn btn-outline-primary">Clothing Management</a>
            <c:if test="${userObj.role == 'admin'}">
                <a href="user-management" class="btn btn-outline-primary">User Management</a>
                <a href="order-management" class="btn btn-outline-primary">Order Management</a>
            </c:if>
        </div>
        <div class="col-8 admin-info">
            <h3>User Info</h3>
            <ul>
                <li><span>Username:</span> ${userObj.username}</li>
                <li><span>Email:</span> ${userObj.email}</li>
                <li><span>User Type:</span> ${userObj.role}</li>
            </ul>

        </div>
    </div>


</main>
<jsp:include page="footer.jsp" />