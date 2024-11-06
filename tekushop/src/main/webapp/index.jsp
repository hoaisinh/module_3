<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
  <main>
<div class="dress-list">
    <h2>Dress List</h2>
  <div class="row dress-list">
    <c:if test="${indexData['clothingList'].isEmpty()}">
      <p>No dress found</p>
    </c:if>
  <c:if test="${!indexData['clothingList'].isEmpty()}">
    <c:forEach var="dress" items="${indexData['clothingList']}">
      <div class="col-3 mb-4">
        <div class="dress-item ">
          <img src="uploads/image/${dress.images[0]}" alt="${dress.name}" />
          <div class="clothing-content">
            <h3>${dress.name}</h3>
            <div class="clothing-description">
                <p>${dress.description}</p>
            </div>

            <hr>
            <div class="clothing-action">
              <a href="buy-clothing?id=${dress.id}" class="btn-blue">Buy Now</a>

              <c:if test="${not empty user &&user.id == 1}">
                <a href="edit-clothing?id=${dress.id}" class="btn-blue">Edit</a>
              </c:if>

            </div>
          </div>
          <p class="clothing-price">
            <span class="price">${dress.price}$</span>
          </p>

        </div>
      </div>
    </c:forEach>
    </c:if>
  </div>
</div>

  </main>
<jsp:include page="footer.jsp" />