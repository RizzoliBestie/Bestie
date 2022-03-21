/////////////////////////////
/////   Page Creation   /////
/////////////////////////////

function Menu_Selection(){
    var check = new URLSearchParams(window.location.search);
    //alert(check.get('selected'));
    document.getElementById(check.get('selected')).style.background = "#ffffff91";
    document.getElementById(check.get('selected')).style.color = "#524a4a"

    var xhttp = new XMLHttpRequest();
    var URIAddr = "../list/"+check.get('selected');
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

                            //Creazione elementi

                            var id_col = document.createElement("td");
                            var username_col = document.createElement("td");
                            var email_col = document.createElement("td");
                            var phone_number_col = document.createElement("td");

                            var update_row = document.createElement("td");
                            var delete_row = document.createElement("td");
                            var update_input = document.createElement("input");
                            var delete_input = document.createElement("input");

                            //Creazione attributi

                            var update_type = document.createAttribute("type");
                            var delete_type = document.createAttribute("type");
                            var update_src = document.createAttribute("src");
                            var delete_src = document.createAttribute("src");
                            var update_class = document.createAttribute("class");
                            var delete_class = document.createAttribute("class");
                            var update_onclick = document.createAttribute("onclick");
                            var delete_onclick = document.createAttribute("onclick");

                            //Assegnamento attributi

                            update_type.value="image";
                            delete_type.value="image";
                            update_src.value="../img/icons/pencil.png";
                            delete_src.value="../img/icons/trash.png";
                            update_class.value = "pencil";
                            delete_class.value = "trash";
                            update_onclick.value = "switchPage('type=users&id="+ jsonResponse[i].id_user + "')";
                            delete_onclick.value= "deleteUserRow('"+ jsonResponse[i].id_user+"')";

                            //Aggiunta attributi

                            update_input.setAttributeNode(update_type);
                            update_input.setAttributeNode(update_src);
                            update_row.setAttributeNode(update_class);
                            update_input.setAttributeNode(update_onclick);
                            delete_input.setAttributeNode(delete_onclick);
                            delete_input.setAttributeNode(delete_type);
                            delete_input.setAttributeNode(delete_src);
                            delete_row.setAttributeNode(delete_class);
                            
                            //Creazione nodi di testo
                            var id_txt = document.createTextNode(jsonResponse[i].id_user);
                            var username_txt = document.createTextNode(jsonResponse[i].username);
                            var email_txt = document.createTextNode(jsonResponse[i].email);
                            var phone_number_txt = document.createTextNode(jsonResponse[i].phone_number);
                            
                            //var x=  "<td><input type='button' value='&#x270e'></td><td><input type='button' value='&#128465'></td>";
                            //console.log(x);
                            //document.getElementById("dynamic_list").innerHTML=x;


                            //Aggiunta elementi secondari
                            id_col.appendChild(id_txt);
                            username_col.appendChild(username_txt);
                            email_col.append(email_txt);
                            phone_number_col.appendChild(phone_number_txt);

                            update_row.appendChild(update_input);
                            delete_row.appendChild(delete_input);

                            //Stampa elementi primncipali
                            document.getElementById("dynamic_list").appendChild(id_col);
                            document.getElementById("dynamic_list").appendChild(username_col);
                            document.getElementById("dynamic_list").appendChild(email_col);
                            document.getElementById("dynamic_list").appendChild(phone_number_col);
                            document.getElementById("dynamic_list").appendChild(update_row);
                            document.getElementById("dynamic_list").appendChild(delete_row);

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

                            var update_row = document.createElement("td");
                            var delete_row = document.createElement("td");
                            var update_input = document.createElement("input");
                            var delete_input = document.createElement("input");

                            //Creazione attributi
                            var update_type = document.createAttribute("type");
                            var delete_type = document.createAttribute("type");
                            var update_src = document.createAttribute("src");
                            var delete_src = document.createAttribute("src");
                            var update_class = document.createAttribute("class");
                            var delete_class = document.createAttribute("class");
                            var update_onclick = document.createAttribute("onclick");
                            var delete_onclick = document.createAttribute("onclick");

                            //Assegnamento attributi
                            update_type.value="image";
                            delete_type.value="image";
                            update_src.value="../img/icons/pencil.png";
                            delete_src.value="../img/icons/trash.png";
                            update_class.value = "pencil";
                            delete_class.value = "trash";
                            update_onclick.value = "switchPage('type=species&id="+ jsonResponse[i].id_user + "')";
                            delete_onclick.value= "deleteRow('"+ jsonResponse[i].id_specie+"')";

                            //Aggiunta attributi
                            update_input.setAttributeNode(update_type);
                            update_input.setAttributeNode(update_src);
                            update_row.setAttributeNode(update_class);
                            update_input.setAttributeNode(update_onclick);
                            delete_input.setAttributeNode(delete_onclick);
                            delete_input.setAttributeNode(delete_type);
                            delete_input.setAttributeNode(delete_src);
                            delete_row.setAttributeNode(delete_class);

                            var id_txt = document.createTextNode(jsonResponse[i].id_specie);
                            var common_name_txt = document.createTextNode(jsonResponse[i].common_name);
                            var scientific_name_txt = document.createTextNode(jsonResponse[i].scientific_name);

                            id_col.appendChild(id_txt);
                            common_name_col.appendChild(common_name_txt);
                            scientific_name_col.append(scientific_name_txt);

                            update_row.appendChild(update_input);
                            delete_row.appendChild(delete_input);

                            document.getElementById("dynamic_list").appendChild(id_col);
                            document.getElementById("dynamic_list").appendChild(common_name_col);
                            document.getElementById("dynamic_list").appendChild(scientific_name_col);
                            document.getElementById("dynamic_list").appendChild(update_row);
                            document.getElementById("dynamic_list").appendChild(delete_row);
                            document.getElementById("dynamic_list").appendChild(document.createElement("tr"));

                            i++;
                        }
                        break;
                    case "races":
                        document.getElementById("dynamic_list").innerHTML="<th>ID_RACE</th><th>ID_SPECIE</th><th>NAME</th><th>INFORMATIONS</th><th>SIZE</th><th>FUR_TYPE</th>";
                        while(jsonResponse[i] != null){
                            //alert(jsonResponse[i].username);
                            var idRace_col = document.createElement("td");
                            var idSpecie_col = document.createElement("td");
                            var name_col = document.createElement("td");
                            var information_col = document.createElement("td");
                            var size_col = document.createElement("td");
                            var fur_type_col = document.createElement("td");

                            var update_row = document.createElement("td");
                            var delete_row = document.createElement("td");
                            var update_input = document.createElement("input");
                            var delete_input = document.createElement("input");

                            //Creazione attributi
                            var update_type = document.createAttribute("type");
                            var delete_type = document.createAttribute("type");
                            var update_src = document.createAttribute("src");
                            var delete_src = document.createAttribute("src");
                            var update_class = document.createAttribute("class");
                            var delete_class = document.createAttribute("class");
                            var update_onclick = document.createAttribute("onclick");
                            var delete_onclick = document.createAttribute("onclick");

                            //Assegnamento attributi
                            update_type.value="image";
                            delete_type.value="image";
                            update_src.value="../img/icons/pencil.png";
                            delete_src.value="../img/icons/trash.png";
                            update_class.value = "pencil";
                            delete_class.value = "trash";
                            update_onclick.value = "switchPage('type=races&id="+ jsonResponse[i].id_user + "')";
                            delete_onclick.value= "deleteRow('"+ jsonResponse[i].id_race+"')";

                            //Aggiunta attributi
                            update_input.setAttributeNode(update_type);
                            update_input.setAttributeNode(update_src);
                            update_row.setAttributeNode(update_class);
                            update_input.setAttributeNode(update_onclick);
                            delete_input.setAttributeNode(delete_onclick);
                            delete_input.setAttributeNode(delete_type);
                            delete_input.setAttributeNode(delete_src);
                            delete_row.setAttributeNode(delete_class);

                            var idRace_txt = document.createTextNode(jsonResponse[i].id_race);
                            var idSpecie_txt = document.createTextNode(jsonResponse[i].id_specie);
                            var name_txt = document.createTextNode(jsonResponse[i].name);
                            var information_txt = document.createTextNode(jsonResponse[i].information);
                            var size_txt = document.createTextNode(jsonResponse[i].size);
                            var fur_type_txt = document.createTextNode(jsonResponse[i].fur_type);

                            idRace_col.appendChild(idRace_txt);
                            idSpecie_col.appendChild(idSpecie_txt);
                            name_col.append(name_txt);
                            information_col.appendChild(information_txt);
                            size_col.appendChild(size_txt);
                            fur_type_col.appendChild(fur_type_txt);

                            update_row.appendChild(update_input);
                            delete_row.appendChild(delete_input);

                            document.getElementById("dynamic_list").appendChild(idRace_col);
                            document.getElementById("dynamic_list").appendChild(idSpecie_col);
                            document.getElementById("dynamic_list").appendChild(name_col);
                            document.getElementById("dynamic_list").appendChild(information_col);
                            document.getElementById("dynamic_list").appendChild(size_col);
                            document.getElementById("dynamic_list").appendChild(fur_type_col);
                            document.getElementById("dynamic_list").appendChild(update_row);
                            document.getElementById("dynamic_list").appendChild(delete_row);
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

function switchPage(query){
    location.href = "../home/update_page/admin_update.html?" + query;
}

function deleteUserRow(id){
    var xhttp = new XMLHttpRequest();
    var URIAddr = "../../../deleteUser/"+id;
    var choice = confirm("Vuoi davvero eliminare questa riga (id = "+id+")?");
    //var jsonResponse;
    if(choice){
        xhttp.onreadystatechange = function() {
            if (this.status == 200 && xhttp.readyState == 4) {
                Menu_Selection();
            }
        }
        xhttp.open("DELETE", URIAddr);
        xhttp.send(null);
    }else{
    }
}