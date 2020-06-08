<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags/custom" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="lang"/>

<div id="loginPopup" class="modal">
    <form id="loginForm" class="modal-content animate" action="${pageContext.request.contextPath}/login" method="post">
        <div class="imgcontainer">
            <span class="close" onclick="document.getElementById('loginPopup').style.display='none'" title="close">&times</span>
            <img src="images/login.png" alt="Avatar" class="avatar">
        </div>
        <div class="container">
            <span id="wrongUser" class="wrongInput" style="display: none"><fmt:message key="wronguser"/></span>
            <span id="wrongPassword" class="wrongInput" style="display: none"><fmt:message key="wrongpass"/></span>
            <span id="logSuccess" class="wrongInput" style="display: none"><fmt:message key="success"/></span>

            <label for="name"><b><fmt:message key="username"/></b></label>
            <input id="name" type="text" placeholder="<fmt:message key="enterUser"/>" name="user" <%--required--%>>

            <label for="pass"><b><fmt:message key="password"/></b></label>
            <input id="pass" type="password" placeholder="<fmt:message key="enterPass"/>" name="password" <%--required--%>>

            <button id="login" type="submit"><fmt:message key="login"/></button>
        </div>
    </form>
</div>

<%--/*  Log in  (Listener) */--%>
<script>
    $("#loginForm").submit(function(event) {

        event.preventDefault();

        url = $( this ).attr( 'action' );
        var posting = $.post( url,
            {
                user: $('#name').val(),
                password: $('#pass').val()
            } );

        posting.done(function( data ) {

            document.getElementById("wrongUser").style.display="none";
            document.getElementById("wrongPassword").style.display="none";

            if(data=='user') {
                document.getElementById("wrongUser").style.display="block";
            } else if(data=='password') {
                document.getElementById("wrongPassword").style.display="block";
            } else if(data=='ok') {
                document.getElementById("logSuccess").style.display="block";
                document.location.reload(false);    // true reload page from server
            } else {
                alert("wrong: " + data);
            }
        });
    });
</script>