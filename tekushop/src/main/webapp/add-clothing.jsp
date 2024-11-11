<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<main class="add-clothing">
    <form action="" method="post" enctype="multipart/form-data" class="row">
        <c:if test="${error != null}">
            <p class="error">${error}</p>
        </c:if>
        <c:if test="${success != null}">
            <script>
                alert("New clothes added")
                window.location = "http://localhost:8080/clothing-management";
            </script>
        </c:if>
        <legend>Add Clothing</legend>
        <div class="form-group col-3">
            <label for="name">Name</label>
            <c:if test="${clothing != null}">
                <input type="text" value="${clothing.name}" name="name" id="name" class="form-control form-control-sm" required>
            </c:if>
            <c:if test="${clothing == null}">
                <input type="text" name="name" id="name" class="form-control form-control-sm" required>
            </c:if>

        </div>
        <div class="form-group col-3">
            <label for="clothesType">Clothes type</label>
            <c:if test="${clothing != null}">
                <input type="text" value="${clothing.clothesType}" name="clothesType" id="clothesType" class="form-control form-control-sm" required>
            </c:if>
            <c:if test="${clothing == null}">
                <input type="text" name="clothesType" id="clothesType" class="form-control form-control-sm" required>
            </c:if>

        </div>
        <div class="form-group col-3">
            <label for="color">Color</label>
            <c:if test="${clothing != null}">
                <input type="text" value="${clothing.color}" name="color" id="color" class="form-control form-control-sm" required>
            </c:if>
            <c:if test="${clothing == null}">
                <input type="text" name="color" id="color" class="form-control form-control-sm" required>
            </c:if>
        </div>
        <div class="form-group col-3">
            <label for="size">Size</label>
            <c:if test="${clothing != null}">
                <input type="number" value="${clothing.size}" name="size" id="size" class="form-control form-control-sm" required>
            </c:if>
            <c:if test="${clothing == null}">
                <input type="number" name="size" id="size" class="form-control form-control-sm" required>
            </c:if>

        </div>
        <div class="form-group col-3">
            <label for="price">Price</label>
            <c:if test="${clothing != null}">
                <input type="number" value="${clothing.price}" name="price" id="price" class="form-control form-control-sm" required>
            </c:if>
            <c:if test="${clothing == null}">
                <input type="number" name="price" id="price" class="form-control form-control-sm" required>
            </c:if>

        </div>
        <div class="form-group col-3">
            <label for="quantity">Quantity</label>
            <c:if test="${clothing != null}">
                <input type="number" value="${clothing.stock}" name="quantity" id="quantity" class="form-control form-control-sm" required>
            </c:if>
            <c:if test="${clothing == null}">
                <input type="number" name="quantity" id="quantity" class="form-control form-control-sm" required>
            </c:if>
        </div>
        <div class="form-group col-6">
            <label for="images">Images</label>
            <input type="file" name="images" id="images" class="form-control form-control-sm" required>
        </div>
        <div class="form-group col-12">
            <label for="description">Description</label>
            <c:if test="${clothing != null}">
                <textarea name="description" id="description" class="form-control form-control-sm" required>${clothing.description}</textarea>
            </c:if>
            <c:if test="${clothing == null}">
                <textarea name="description" id="description" class="form-control form-control-sm" required></textarea>
            </c:if>
        </div>
        <div class="col-5"></div>
        <button type="submit" class="mt-3 btn-blue col-2">Add</button>
    </form>

</main>
<jsp:include page="footer.jsp" />