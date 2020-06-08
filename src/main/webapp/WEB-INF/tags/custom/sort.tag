<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="lang"/>

<%@ attribute name="locations_by_category" type="java.util.Map<java.lang.String, java.util.HashSet<java.lang.String>>" %>

<%--<c:set var="unique_locations" value="${pageContext.servletContext.getAttribute(\"locations_by_category\")}"/>--%>

<div class="sort-container">

<%--    SORT--%>
    <div class="sort">
        <button class="sortbtn" onclick="show('sort')"><fmt:message key="sort"/></button>
        <div id="sort" class="sort-content">
            <a href="${pageContext.request.contextPath}/index?sort=random"><fmt:message key="random"/></a>
            <a href="${pageContext.request.contextPath}/index?sort=countryName"><fmt:message key="country"/></a>
            <a href="${pageContext.request.contextPath}/index?sort=price"><fmt:message key="price"/></a>
            <a href="${pageContext.request.contextPath}/index?sort=date"><fmt:message key="date"/></a>
        </div>
    </div>

<%--    ITEMS ON PAGE--%>
    <div class="sort">
        <button class="sortbtn" onclick="show('ItemsOnPage')"><fmt:message key="itemsOnPage"/></button>
        <div id="ItemsOnPage" class="sort-content">
            <a class="itemsOnPage" onclick="itemsOnPage(4)">4</a>
            <a class="itemsOnPage" onclick="itemsOnPage(8)">8</a>
            <a class="itemsOnPage" onclick="itemsOnPage(12)">12</a>
            <a class="itemsOnPage" onclick="itemsOnPage(24)">24</a>
            <a class="itemsOnPage" onclick="itemsOnPage(99)">All</a>
        </div>
    </div>

<%--    COUNTRY--%>
    <div class="sort">
        <input type="text" id="inputCountry1" class="sortInput" onfocus="show('selectCountry')"
               onkeyup="filterList('inputCountry1','selectCountry')" placeholder="<fmt:message key="country"/>..." title="Type in a name">

        <div id="selectCountry" class="sort-content">
            <a onclick="listTours('country','all')">All</a>
            <c:forEach var="loc" items="${locations_by_category.get(\"countries\")}">
                <a onclick="listTours('country', '${fn:replace(loc, '&', '%26')}')">${loc}</a>
            </c:forEach>
        </div>
    </div>

<%--    <div class="sort">--%>
<%--        <input type="text" id="inputCountry1" class="sortInput" onfocus="show('selectCountry')"--%>
<%--               onkeyup="filterList('inputCountry1','selectCountry')" placeholder="<fmt:message key="country"/>..." title="Type in a name">--%>

<%--        <div id="selectCountry" class="sort-content">--%>
<%--            <a href="${pageContext.request.contextPath}/index?all=all">All</a>--%>
<%--            <c:forEach var="loc" items="${locations_by_category.get(\"countries\")}">--%>
<%--                <a href="${pageContext.request.contextPath}/index?country=${fn:replace(loc, '&', '%26')}">${loc}</a>--%>
<%--            </c:forEach>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--    RESORT--%>
    <div class="sort">
        <input type="text" id="inputResort" class="sortInput" onfocus="show('selectResort')"
               onkeyup="filterList('inputResort','selectResort')" placeholder="<fmt:message key="resort"/>..." title="Type in a name">

        <div id="selectResort" class="sort-content">
            <a onclick="listTours('resort','all')">All</a>
            <c:forEach var="loc" items="${locations_by_category.get(\"resorts\")}">
                <a onclick="listTours('resort', '${fn:replace(loc, '&', '%26')}')">${loc}</a>
            </c:forEach>
        </div>
    </div>

<%--    HOTEL--%>
    <div class="sort">
        <input type="text" id="inputHotel" class="sortInput" onfocus="show('selectHotel')"
               onkeyup="filterList('inputHotel', 'selectHotel')" placeholder="<fmt:message key="hotel"/>..." title="Type in a name">

        <div id="selectHotel" class="sort-content">
            <a onclick="listTours('hotel','all')">All</a>
            <c:forEach var="loc" items="${locations_by_category.get(\"hotels\")}">
                <a onclick="listTours('hotel', '${fn:replace(loc, '&', '%26')}')">${loc}</a>
            </c:forEach>
        </div>
    </div>

</div>