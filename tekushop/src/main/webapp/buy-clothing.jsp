<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<main>
    <h1>Buy</h1>
    <div>product_id:${product_id}</div>
    <form action="buy-clothing" method="post">
        <input type="hidden" name="product_id" value="${product_id}" />
        <input type="hidden" name="user_id" value="${user_id}" />
        Quantity: <input type="number" name="quantity" />
        <input type="submit" value="Buy Now" />
    </form>
    <div class="msg">
        <c:if test="${not empty message}">
            <p>${message}</p>
        </c:if>
    </div>
    <h2>Order List</h2>
    <div class="row order-list">
        <c:if test="${listOrder.isEmpty()}">
            <p>No order found</p>
        </c:if>
        <c:if test="${!listOrder.isEmpty()}">
            <c:forEach var="order" items="${listOrder}">
                <div class="row order-item w-100">
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
                    <div class="row col-12 order-item-child">
                        <c:if test="${order.orderItems.isEmpty()}">
                            <p>No order-item found</p>
                        </c:if>
                        <c:if test="${!order.orderItems.isEmpty()}">
                            <c:forEach var="orderItem" items="${order.orderItems}">
                                <div class="row order-item w-100">
                                    <div class="col-2">

                                        <img src="uploads/image/${orderItem.clothing.images[0]}">
                                    </div>
                                    <div class="col-2">
                                        ${orderItem.clothing.name}
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