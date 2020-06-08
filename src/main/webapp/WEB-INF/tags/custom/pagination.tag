<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="position" type="java.lang.String" required="true" %>
<%@ attribute name="list" type="java.util.List" required="true" %>

<script>
    setCookie("tours_pages_amount", '${tours_pages_amount}');
</script>


<div class="pag">
    <button class="pag-button" id="button<<" onclick="move('left')">    <<  </button>

    <c:forEach var="page" items="${list}">
        <button class="pag-button" id="${position}button${page}" onclick="selectPage(${page})">${page}</button>
    </c:forEach>

    <button class="pag-button" id="button>>" onclick="move('right')">   >>  </button>
</div>

<script>
    function selectPage(num) {
        // $("button").removeClass("pag-active");
        $("#upperbutton" + String(getCookie("tours_pages_current"))).removeClass("pag-active");
        $("#upperbutton" + String(num)).addClass("pag-active");
        $("#lowerbutton" + String(getCookie("tours_pages_current"))).removeClass("pag-active");
        $("#lowerbutton" + String(num)).addClass("pag-active");
        setCookie("tours_pages_current", num);

        var index = num - 1;
        var elementsByClassName = document.getElementsByClassName("tour-container");
        for (var i = 0; i < elementsByClassName.length; i++) {
            if(i != index) {
                elementsByClassName[i].style.display = "none";
            } else {
                elementsByClassName[i].style.display = "";
                // document.getElementById("page" + num).style.display = "";
            }
        }
    }

    function move(val) {
        var page = parseInt(getCookie("tours_pages_current"));
        var amount = parseInt(getCookie("tours_pages_amount"));

        if (val == 'left') {
            if (page > 1)
                selectPage(page - 1);
        }
        else if (val == 'right') {
            if (page < amount)
                selectPage(page + 1);
        }
    }

</script>