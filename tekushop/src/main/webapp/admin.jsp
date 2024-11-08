<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<main class="admin-page">
    <h1>Admin Page</h1>
    <div class="row admin-content">
        <div class="col-4 admin-action">
            <a href="clothing-management" class="btn-blue">Clothing</a>
            <c:if test="${userObj.role == 'admin'}">
                <a href="user-mng" class="btn-blue">User</a>
                <a href="order-mng" class="btn-blue">Order</a>
            </c:if>
        </div>
        <div class="col-8 admin-action">
            <h2>Hello ${userObj.username}</h2>
        </div>
    </div>


</main>
<jsp:include page="footer.jsp" />