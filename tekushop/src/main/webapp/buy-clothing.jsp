<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<main class="buy-clothing-page">
    <c:if test="${not empty product_id}">

    <h1>Buy Clothes</h1>
    <div class="row">
        <div class="productInfo col-6">
            <img src="${clothingInfo.images[0]}" />
        </div>
        <div class="productBuy col-6">
            <h3>Name: ${clothingInfo.name}</h3>
            <p class="desc">Descripton: ${clothingInfo.description}</p>
            <p class="price">Price: ${clothingInfo.price}</p>
            <form action="buy-clothing" method="post">
                <input type="hidden" name="product_id" value="${product_id}" />
                <input type="hidden" name="user_id" value="${user_id}" />
                <lable for="quantity">Quantity:</lable>
                 <input type="number" name="quantity" />
                <input class="btn-blue mt-3" type="submit" value="Buy Now" />
            </form>
        </div>

    </div>

    <div class="msg">
        <c:if test="${not empty message}">
            <p>${message}</p>
        </c:if>
    </div>
    </c:if>
    <h2>Order List</h2>
    <div class="row order-list">
        <c:if test="${listOrder.isEmpty()}">
            <p>No order found</p>
        </c:if>
        <c:if test="${!listOrder.isEmpty()}">
            <div class="order-item w-100">
                <div class="row order-info" >
                    <div class="col-2">
                            Username
                    </div>
                    <div class="col-2">
                            Order date
                    </div>
                    <div class="col-2">
                            Order Status
                    </div>
                    <div class="col-2">
                            Total Amount
                    </div>
                    <div class="col-2">
                            Payment Status
                    </div>
                    <div class="col-2">
                            Payment Method
                    </div>
                </div>
            </div>
            <c:forEach var="order" items="${listOrder}">
                <div class="order-item w-100">
                    <div class="row order-info  bg-light pt-2 pb-2 text-dark shadow-sm collapsed" data-bs-toggle="collapse" href="#collapse_id${order.orderId}" role="button" aria-expanded="false" aria-controls="collapseExample">
                        <div class="col-2">
                                ${order.customer.username}
                        </div>
                        <div class="col-2">
                                ${order.orderDate}
                        </div>
                        <div class="col-2">
                                ${order.status}
                        </div>
                        <div class="col-2">
                                ${order.totalAmount}
                        </div>
                        <div class="col-2">
                                ${order.paymentStatus}
                        </div>
                        <div class="col-2">
                                ${order.paymentMethod}
                        </div>
                    </div>

                    <div class="row w-100 order-item-child collapse" id="collapse_id${order.orderId}">
                        <c:if test="${order.orderItems.isEmpty()}">
                            <p>No order-item found</p>
                        </c:if>
                        <c:if test="${!order.orderItems.isEmpty()}">
                            <c:forEach var="orderItem" items="${order.orderItems}">
                                <div class="row order-item w-100">
                                    <div class="col-4">
                                        <img src="${orderItem.clothing.images[0]}">
                                    </div>
                                    <div class="col-4 order-item-info" >
                                        <h3>Name: ${orderItem.clothing.name}</h3>
                                        <p class="desc">Descripton: ${orderItem.clothing.description}</p>
                                    </div>
                                    <div class="col-4 order-item-info">
                                        <p class="unitPrice">Unit Price: ${orderItem.unitPrice}</p>
                                        <p class="quantity">Quantity: ${orderItem.quantity}</p>
                                        <p class="total">Total: ${orderItem.totalPrice}</p>
                                        <p class="date">Create At: ${orderItem.createdAt}</p>
                                    </div>

                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>
</main>
<jsp:include page="footer.jsp" />