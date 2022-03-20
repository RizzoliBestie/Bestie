function UpdatePage(){

    var check = new URLSearchParams(window.location.search);

    var xhttp = new XMLHttpRequest();
    var URIAddr = "../../../users/"+check.get('id');
    var jsonResponse;

    xhttp.onreadystatechange = function() {
        if (this.status == 200 && xhttp.readyState == 4) {
            if(!(this.responseText == "")){
                jsonResponse = JSON.parse(this.responseText);
                switch(check.get('type')){
                    case 'users':
                        break;
                    case 'species':
                        break;
                    case 'races':
                        break;
            }
        }
    }
    xhttp.open("GET", URIAddr);
    xhttp.send(null);

    /*
    //alert(check.get('selected'));
    }*/
}