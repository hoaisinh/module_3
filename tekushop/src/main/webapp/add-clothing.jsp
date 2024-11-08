<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<main class="add-clothing">
    <form action="clothing-management/add" method="post" enctype="multipart/form-data" class="row">
        <legend>Add Clothing</legend>
        <div class="form-group col-3">
            <label for="name">Name</label>
            <input type="text" name="name" id="name" class="form-control form-control-sm" required>
        </div>
        <div class="form-group col-3">
            <label for="clothesType">Clothes type</label>
            <input type="text" name="clothesType" id="clothesType" class="form-control form-control-sm" required>
        </div>
        <div class="form-group col-3">
            <label for="color">Color</label>
            <input type="text" name="color" id="color" class="form-control form-control-sm" required>
        </div>
        <div class="form-group col-3">
            <label for="size">Size</label>
            <input type="number" name="size" id="size" class="form-control form-control-sm" required>
        </div>
        <div class="form-group col-3">
            <label for="price">Price</label>
            <input type="number" name="price" id="price" class="form-control form-control-sm" required>
        </div>
        <div class="form-group col-3">
            <label for="quantity">Quantity</label>
            <input type="number" name="quantity" id="quantity" class="form-control form-control-sm" required>
        </div>
        <div class="form-group col-3">
            <label for="images">Images</label>
            <input type="file" name="images" id="images" class="form-control form-control-sm" required>
        </div>
        <div class="form-group col-3">
            <label for="description">Description</label>
            <textarea name="description" id="description" class="form-control form-control-sm" required></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Add</button>
    </form>

</main>
<jsp:include page="footer.jsp" />