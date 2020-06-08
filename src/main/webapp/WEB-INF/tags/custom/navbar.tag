<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags/custom" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="lang"/>

<div class="navbar">
    <div class="navbarLeft">
        <a class="floatLft active" href="${pageContext.request.contextPath}/index"><fmt:message key="home"/></a>
        <a class="floatLft" href="#news"><fmt:message key="news"/></a>
        <a class="floatLft" href="#contact"><fmt:message key="contact"/></a>
        <a class="floatLft" href="#about"><fmt:message key="about"/></a>
    </div>
    <c:choose>
        <c:when test="${sessionScope.user == null}">
            <a id="login" class="login" onclick="document.getElementById('loginPopup').style.display='block'"
               style="width:auto; display: block"><fmt:message key="login"/></a>
        </c:when>
        <c:when test="${sessionScope.user != null}">
            <custom:userMenu/>
        </c:when>
    </c:choose>
    <custom:localization/>
</div>

<custom:loginForm/>

<%--<form id="logoutForm" action="${pageContext.request.contextPath}/logout" method="post"></form>--%>