function Menu_Selection(){
    var check = new URLSearchParams(window.location.search);
    //alert(check.get('selected'));
    document.getElementById(check.get('selected')).style.background = "#ffffff91";
    document.getElementById(check.get('selected')).style.color = "#524a4a"

    var xhttp = new XMLHttpRequest();
    var URIAddr = "../element_list/"+check.get('selected');
    var jsonResponse;
    xhttp.onreadystatechange = function() {
        if (this.status == 200 && xhttp.readyState == 4) {
            if(!(this.responseText == "")){
                jsonResponse = JSON.parse(this.responseText);
                var i=0;
                switch(check.get('selected')){
                    case "users":
                        document.getElementById("dynamic_list").innerHTML="<th>ID_USER</th><th>USERNAME</th><th>EMAIL</th><th>PHONE_NUMBER</th>";
                        while(jsonResponse[i] != null){
                            //alert(jsonResponse[i].username);
                            var id_col = document.createElement("td");
                            var username_col = document.createElement("td");
                            var email_col = document.createElement("td");
                            var phone_number_col = document.createElement("td");

                            var id_txt = document.createTextNode(jsonResponse[i].id_user);
                            var username_txt = document.createTextNode(jsonResponse[i].username);
                            var email_txt = document.createTextNode(jsonResponse[i].email);
                            var phone_number_txt = document.createTextNode(jsonResponse[i].phone_number);

                            id_col.appendChild(id_txt);
                            username_col.appendChild(username_txt);
                            email_col.append(email_txt);
                            phone_number_col.appendChild(phone_number_txt);

                            document.getElementById("dynamic_list").appendChild(id_col);
                            document.getElementById("dynamic_list").appendChild(username_col);
                            document.getElementById("dynamic_list").appendChild(email_col);
                            document.getElementById("dynamic_list").appendChild(phone_number_col);
                            document.getElementById("dynamic_list").appendChild(document.createElement("tr"));

                            i++;
                        }
                        break;
                    case "species":
                        document.getElementById("dynamic_list").innerHTML="<th>ID_SPECIE</th><th>COMMON_NAME</th><th>SCIENTIFIC_NAME</th>";
                        while(jsonResponse[i] != null){
                            //alert(jsonResponse[i].username);
                            var id_col = document.createElement("td");
                            var common_name_col = document.createElement("td");
                            var scientific_name_col = document.createElement("td");

                            var id_txt = document.createTextNode(jsonResponse[i].id_specie);
                            var common_name_txt = document.createTextNode(jsonResponse[i].common_name);
                            var scientific_name_txt = document.createTextNode(jsonResponse[i].scientific_name);

                            id_col.appendChild(id_txt);
                            common_name_col.appendChild(common_name_txt);
                            scientific_name_col.append(scientific_name_txt);

                            document.getElementById("dynamic_list").appendChild(id_col);
                            document.getElementById("dynamic_list").appendChild(common_name_col);
                            document.getElementById("dynamic_list").appendChild(scientific_name_col);
                            document.getElementById("dynamic_list").appendChild(document.createElement("tr"));

                            i++;
                        }
                        break;
                    case "races":
                        document.getElementById("dynamic_list").innerHTML="<th>ID_USER</th><th>USERNAME</th><th>EMAIL</th><th>PHONE_NUMBER</th>";
                        while(jsonResponse[i] != null){
                            //alert(jsonResponse[i].username);
                            var idRace_col = document.createElement("td");
                            var idSpecie_col = document.createElement("td");
                            var name_col = document.createElement("td");
                            var information_col = document.createElement("td");
                            var size_col = document.createElement("td");
                            var fur_type_col = document.createElement("td");

                            var idRace_txt = document.createTextNode(jsonResponse[i].id_user);
                            var idSpecie_txt = document.createTextNode(jsonResponse[i].username);
                            var name_txt = document.createTextNode(jsonResponse[i].email);
                            var inforation_txt = document.createTextNode(jsonResponse[i].phone_number);
                            var phone_number_txt = document.createTextNode(jsonResponse[i].phone_number);
                            var phone_number_txt = document.createTextNode(jsonResponse[i].phone_number);

                            id_col.appendChild(id_txt);
                            username_col.appendChild(username_txt);
                            email_col.append(email_txt);
                            phone_number_col.appendChild(phone_number_txt);

                            document.getElementById("dynamic_list").appendChild(id_col);
                            document.getElementById("dynamic_list").appendChild(username_col);
                            document.getElementById("dynamic_list").appendChild(email_col);
                            document.getElementById("dynamic_list").appendChild(phone_number_col);
                            document.getElementById("dynamic_list").appendChild(document.createElement("tr"));

                            i++;
                        }
                        break;
                }
            }else
                alert("Errore");
        }
        else{
        }
    };
    xhttp.open("GET", URIAddr);
    xhttp.send(null);
}