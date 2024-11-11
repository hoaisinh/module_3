<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<main class="add-clothing">
    <form action="edit-clothing" method="post" enctype="multipart/form-data" class="row">
        <legend>Edit Clothing</legend>
        <c:if test="${error != null}">
            <p class="text-danger">${error}</p>
        </c:if>

        <input type="hidden" name="id" value="${clothing.id}">
        <div class="col-4 img-form row ">
            <img src="${clothing.images[0]}" alt="clothing" class="col-12">
            <small class="text-danger">Don't select new image if you don't want to change this image!</small>
            <div class="form-group col-12">
                <label for="images">Images</label>
                <input type="file"  name="images" id="images" class="form-control form-control-sm" >
                <input type="hidden" name="oldImages" value="${clothing.images[0]}">
            </div>
        </div>
        <div class="col-8 row">
            <div class="form-group col-3">
                <label for="name">Name</label>
                <input type="text" name="name" value="${clothing.name}" id="name" class="form-control form-control-sm" required>
            </div>
            <div class="form-group col-3">
                <label for="clothesType">Clothes type</label>
                <input type="text" value="${clothing.clothesType}" name="clothesType" id="clothesType" class="form-control form-control-sm" required>
            </div>
            <div class="form-group col-3">
                <label for="color">Color</label>
                <input type="text" value="${clothing.color}" name="color" id="color" class="form-control form-control-sm" required>
            </div>
            <div class="form-group col-3">
                <label for="size">Size</label>
                <input type="number" value="${clothing.size}" name="size" id="size" class="form-control form-control-sm" required>
            </div>
            <div class="form-group col-3">
                <label for="price">Price</label>
                <input type="number" value="${clothing.price}" name="price" id="price" class="form-control form-control-sm" required>
            </div>
            <div class="form-group col-3">
                <label for="quantity">Stock</label>
                <input type="number" value="${clothing.stock}" name="quantity" id="quantity" class="form-control form-control-sm" required>
            </div>
            <div class="form-group col-12">
                <label for="description">Description</label>
                <textarea  name="description" id="description" class="form-control form-control-sm" required>${clothing.description}</textarea>
            </div>
            <div class="col-5"></div>
            <button type="submit" class="mt-3 btn-blue col-2">Edit</button>
        </div>
    </form>

</main>
<jsp:include page="footer.jsp" />