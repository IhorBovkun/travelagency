function filterList(input, select) {
    var input, filter, ul, li, a, i, txtValue;
    input = document.getElementById(input);
    filter = input.value.toUpperCase();
    ul = document.getElementById(select);
    li = ul.getElementsByTagName("a");
    for (i = 0; i < li.length; i++) {
        a = li[i];
        txtValue = a.textContent || a.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";
        }
    }
}

function show(id) {
    _hideAll();
    document.getElementById(id).style.display = "block";
}

function _hideAll() {
    var dropdowns = document.getElementsByClassName("sort-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
        dropdowns[i].style.display = "none";
    }
}

function listTours(location, val) {
    var value = String(val);
    var action = window.location.origin + "/sessionAttributes";
    var posting;

    switch (location) {
        case 'country':
            posting = $.post (action,
                {
                    country: value,
                    resort: 'all',
                    hotel: 'all'
                } );
            break;
        case 'resort' :
            posting = $.post (action,
                {
                    resort: value,
                    hotel: 'all'
                } );
            break;
        case 'hotel' :
            posting = $.post (action,
                {
                    hotel: value
                } );
            break;
        default:
            break;
    }
    posting.done(function (data) {
        if (data == 'ok') {
            document.location.reload();
        } else {
            alert("err");
        }
    });
}

function itemsOnPage(num) {

    var action = window.location.origin + "/sessionAttributes";

    var posting = $.post (action,
        {
            itemsOnPage: num
        } );

    posting.done(function (data) {

        if (data == 'ok') {
            document.location.reload();
        } else {
            alert("err");
        }
    });
}

function login(action, name, pass) {
    action = String(action);

    var posting = $.post(action,
        {
            user: name,
            password: pass
        });

    posting.done(function( response ) {

        document.getElementById("wrongUser").style.display="none";
        document.getElementById("wrongPassword").style.display="none";

        if(response=='user') {
            document.getElementById("wrongUser").style.display="block";
        } else if(response=='password') {
            document.getElementById("wrongPassword").style.display="block";
        } else if(response=='ok') {
            document.getElementById("logSuccess").style.display="block";
            document.location.reload(false);    // true reload page from server
        } else {
            alert(response);
        }
    });
}

function logout(action) {
    action = String(action);

    var posting = $.post(action);

    posting.done(function( data ) {
        if (data == 'ok') {
            document.location.reload(false);    // true reload page from server
        }
    });
}

// Close the dropdown menu if the user clicks outside of it
window.onclick = function(event) {
    var loginPopup = document.getElementById('loginPopup');
    if (event.target == loginPopup) {
        loginPopup.style.display = "none";
        document.getElementById('wrongUser').style.display = "none";
        document.getElementById('wrongPassword').style.display = "none";
        return;
    }

    if ((!event.target.matches('.sortbtn')) && (!event.target.matches('.sortInput'))) {
        _hideAll();
    }
};