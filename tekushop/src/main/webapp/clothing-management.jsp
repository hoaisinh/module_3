<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<main class="clothing-management">
    <h1>Clothing Management </h1>
    <div class="add-clothing">
        <a href="/clothing-management/add" class="btn-blue">Add Clothing</a>
    </div>
    <div class="search-bar w-100">
        <form action="clothing-management" class="row search-clothing">
            <div class="form-group col">
                <label>Clothes Type</label>
                <select name="clothesType" class="form-control form-control-sm" aria-label="Default select example">
                    <option value="">All</option>
                    <c:forEach var="clothesType" items="${clothesTypeList}">
                        <option value="${clothesType}" <c:if test="${param.clothesType == clothesType}">selected </c:if>>${clothesType}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col">
                <label>Color</label>
                <select name="color" class="form-control form-control-sm" aria-label="Default select example">
                    <option value="">All</option>
                    <c:forEach var="color" items="${colorList}">
                        <option value="${color}" <c:if test="${param.color == color}">selected </c:if>>${color}</option>
                    </c:forEach>
                </select>
            </div>
            <c:forEach var="searchAtt" items="${searchFromAtt}">
                <div class="form-group col">
                    <label>${searchAtt.value}</label>
                    <input class="form-control form-control-sm" type="text" name="${searchAtt.key}" value="${param[searchAtt.key]}">
                </div>
            </c:forEach>
            <div class="form-group col">
               <br>
            <button type="submit" class="btn-blue col">Search</button>
                <a href="http://localhost:8080/clothing-management?clothesType=&color=&minPrice=&maxPrice=" class="btn-blue">Clear</a>
            </div>
        </form>
    </div>
    <div class="clothing-list">
        <table class="table">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Clothes type</th>
                    <th>Color</th>
                    <th>Price</th>
                    <th>Quantity</th>

                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="clothing" items="${clothingList}">
                    <tr>
                        <td>${clothing.id}</td>
                        <td>${clothing.name}</td>
                        <td>${clothing.clothesType}</td>
                        <td>${clothing.color}</td>
                        <td>${clothing.price}</td>
                        <td>${clothing.stock}</td>
                        <td>
                            <a href="/clothing-management/edit?id=${clothing.id}" class="btn-blue"></a>
<%--                            <button data-bs-toggle="modal" data-toggle="modal" data-target="#deleteClothing${clothing.id}"  class="btn btn-sm btn-danger deleteClothing">Delete</button>--%>
                            <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteClothing${clothing.id}">
                                Delete
                            </button>
                            <div class="modal fade" id="deleteClothing${clothing.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-body">
                                            Are you sure you want to delete clothing ${clothing.name}?
                                        </div>
                                        <div class="modal-footer">

                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                                            <a href="/clothing-management/delete?clothingId=${clothing.id}" class="btn btn-danger">Yes</a>
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
