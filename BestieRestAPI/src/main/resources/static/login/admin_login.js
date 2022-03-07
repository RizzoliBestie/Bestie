function CheckLogin(){
    var xhttp = new XMLHttpRequest();
    var username = document.getElementById("username_id").value;
    var password = document.getElementById("password_id").value;
    var URIAddr = "../admin/"+username+"/"+password;
    xhttp.onreadystatechange = function() {
        if (this.status == 200 && xhttp.readyState == 4) {
            if(this.responseText == "true")
                location.replace("../home/admin_home.html?selected=users");
            else
                alert("Credenziali errate");
        }
        else{
        }
    };
    xhttp.open("GET", URIAddr);
    xhttp.send(null);
}