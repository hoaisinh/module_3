<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<main class="clothing-management">
    <h1>User Management </h1>
    <c:if test="${error != null}">
        <p class="text-danger">${error}</p>
    </c:if>
    <c:if test="${success != null}">
        <p class="text-success text-center fs-5">${success}</p>
    </c:if>
    <div class="add">
        <a href="/user-management?action=add" class="btn-blue"><i class="bi bi-plus-lg"></i> Add User</a>
    </div>

    <div class="clothing-list">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>No.</th>
                <th>Username</th>
                <th>Email</th>
                <th>Role</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${userList}" varStatus="status">
                <tr>
                    <td>${status.index+1}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>
                    <td>
                        <a href="/user-management?action=edit&id=${user.id}" class="btn-blue">Edit</a>

                        <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteClothing${user.id}">
                            Delete
                        </button>
                        <div class="modal fade" id="deleteClothing${user.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-body">
                                        Are you sure you want to delete User ${user.username}?
                                    </div>
                                    <div class="modal-footer">

                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                                        <a href="/user-management?action=delete&id=${user.id}" class="btn btn-danger">Yes</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>



</main>

<jsp:include page="footer.jsp" />
