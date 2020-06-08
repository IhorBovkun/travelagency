<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="lang"/>

<c:set var="action" value="${pageContext.request.contextPath}/logout" />

<div class="userMenu">
    <div class="user">${sessionScope.user.login}</div>
    <div class="userMenu-content">
        <a style="font-size: 22px">
            ${sessionScope.user.name} ${sessionScope.user.last_name}
        </a>
        <a href="#Profile">Profile</a>
        <a href="#Profile">${action}</a>
        <a href="#Orders">Orders</a>
        <a href="#abc">abc</a>
        <a href="#def">def</a>
        <div class="logout">
            <img  src="images/logout.png" alt="Logout" style="height: 18px; float: left">
            <div  id="logout" style="float: left" onclick="logout('${action}')"><fmt:message key="logout"/></div>
        </div>
    </div>
</div>

<%--<form id="logoutForm" action="${pageContext.request.contextPath}/logout" method="post"></form>--%>
