<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ attribute name="tour" type="sandbox2.models.TourData" required="true" %>
<%@ attribute name="description" type="sandbox2.models.TourDescriptionData" required="true" %>
<%@ tag import="java.time.LocalDate" %>
<%@ tag import="java.time.Period" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="lang"/>

<div class="tour">
    <img class="tour-img" src="images/tourPreviews/${tour.tour_description_id}.jpg">
    <div class="tour-descr">
        <div class="tour-resort">
            <c:forEach var="star" begin="1" end="${description.rating}">
                <span style="color: darkorange">&#9733</span>
            </c:forEach>
            <c:forEach var="star" begin="1" end="${5 - description.rating}">
                <span style="color: darkorange">&#9734</span>
            </c:forEach>
        </div>
        <div class="tour-name" >${description.hotel}</div>
        <div class="tour-resort">${description.country}, ${description.resort}</div>
<%--        <div class="tour-country"></div>--%>
        <div class="tour-money">$ ${tour.price}</div>
    </div>
    <div class="tour-point">
        <img src="images/tourDetails/airplane.png">
        <span><fmt:formatDate value="${tour.departure}"/></span>
    </div>
    <div class="tour-point">
        <img src="images/tourDetails/airplane2.png">
        <span><fmt:formatDate value="${tour.arrival}"/></span>
    </div>
    <div class="tour-point">
        <img src="images/tourDetails/calendar.png">
        <span>
            ${
            Period.between(
                    LocalDate.parse(tour.departure.toString()),
                    LocalDate.parse(tour.arrival.toString())
                ).getDays()
            } <fmt:message key="nights"/>
        </span>
    </div>
        <div class="tour-point">
            <img src="images/tourDetails/persons.png">
            <span><fmt:message key="persons"/></span>
        </div>
    <div class="tour-point">
        <img src="images/tourDetails/dish.png">
        <c:choose>
            <c:when test="${tour.service == 'BB'}">
                <span><fmt:message key="BB"/></span>
            </c:when>
            <c:when test="${tour.service == 'HB'}">
                <span><fmt:message key="HB"/></span>
            </c:when>
            <c:when test="${tour.service == 'FB'}">
                <span><fmt:message key="FB"/></span>
            </c:when>
            <c:when test="${tour.service == 'AI'}">
                <span><fmt:message key="AI"/></span>
            </c:when>
            <c:when test="${tour.service == 'UAI'}">
                <span><fmt:message key="UAI"/></span>
            </c:when>
        </c:choose>
    </div>

</div>