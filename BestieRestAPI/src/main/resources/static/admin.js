function CheckLogin(){
    var xhttp = new XMLHttpRequest();
    var username = document.getElementById("username_id").nodeValue;
    var password = document.getElementById("password_id").nodeValue;
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console-log(this.responseText);
        }
    };
    xhttp.open("GET", "http://192.168.96.200:8080/"+username+"/"+password, true);
    xhttp.send();
}