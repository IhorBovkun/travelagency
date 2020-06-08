<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags/custom" %>

<%@ attribute name="tours" type="java.util.List<sandbox2.models.TourData>" required="true" %>
<%@ attribute name="descriptions" type="java.util.Map<java.lang.Integer, sandbox2.models.TourDescriptionData>" required="true" %>

<%@ tag import="java.util.ArrayList" %>
<%@ tag import="java.util.List" %>

<custom:sort locations_by_category="${requestScope.locations_by_category}"/>

<%!
    long itemsOnPage = 8;   // default, when session parameter yet not set
    long counter = 0;
    long currentPage = 1;
    long size;
    ArrayList<Integer> pages = new ArrayList<>();
%>
<%
    Object attribute = request.getSession().getAttribute("itemsOnPage");
    if (attribute != null)
        itemsOnPage = Long.parseLong((String)attribute);

    size = ((List)(request.getAttribute("tours"))).size();
    size = size / itemsOnPage + (size % itemsOnPage > 0 ? 1 : 0);
    for (int i = 1; i <= size; i++) {
        pages.add(i);
    }

    request.setAttribute("tours_pages_amount", size);
    request.setAttribute("pages", pages);
    request.setAttribute("itemsOnPage", itemsOnPage);
    request.setAttribute("counter", counter);
%>


<c:if test="${requestScope.pages != null}">
    <custom:pagination position="upper" list="${requestScope.pages}"/>
</c:if>

<c:forEach var="tour" items="${tours}">
    <c:choose>
        <c:when test="${(requestScope.counter % requestScope.itemsOnPage) == 0}">
            <%--            close previous tour-container--%>
            <c:if test="${requestScope.counter > 0}">
                </div>
            </c:if>
            <%--            open new tour-container--%>
            <div id="page<%=(currentPage++)%>" class="tour-container">

            <custom:tour tour="${tour}" description="${descriptions.get(tour.tour_description_id)}"></custom:tour>
        </c:when>

        <c:otherwise>
            <custom:tour tour="${tour}" description="${descriptions.get(tour.tour_description_id)}"></custom:tour>
        </c:otherwise>
    </c:choose>

    <%
        counter++;
        request.setAttribute("counter", counter);
    %>
</c:forEach>
<%--close last tour-container--%>
<c:if test="${requestScope.counter > 0}">
    </div>
</c:if>

<c:if test="${requestScope.pages != null}">
    <custom:pagination position="lower" list="${requestScope.pages}"/>
    <script>selectPage(1);</script>
</c:if>