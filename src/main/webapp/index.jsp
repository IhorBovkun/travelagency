<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags/custom"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="js/loginJQuery.js"></script>
<script type="text/javascript" src="js/cookie.js"></script>

<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>

<custom:navbar/>
<custom:listTours tours="${requestScope.tours}" descriptions="${pageContext.servletContext.getAttribute('locations')}"/>

</body>
</html>