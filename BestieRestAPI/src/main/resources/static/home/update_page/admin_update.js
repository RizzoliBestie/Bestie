function UpdatePage(){

    var check = new URLSearchParams(window.location.search);
    var URIAddr;
    switch(check.get('type')){
        case 'users':
            URIAddr = "../../../users/"+check.get('id');
            break;
        case 'species':
            URIAddr = "../../../species/"+check.get('id');
            break;
        case 'races':
            //alert(check.get('id'));
            URIAddr = "../../../race/"+check.get('id');
            break;
    }
    var xhttp = new XMLHttpRequest();
    var jsonResponse;

    xhttp.onreadystatechange = function() {
        if (this.status == 200 && xhttp.readyState == 4) {
            if(!(this.responseText == "")){
                jsonResponse = JSON.parse(this.responseText);
                switch(check.get('type')){
                    case 'users':
                        //Creazione Elementi

                        var username_field = document.createElement("input");
                        var email_field = document.createElement("input");
                        var phone_field = document.createElement("input");

                        var username_p = document.createElement("p");
                        var email_p = document.createElement("p");
                        var phone_p = document.createElement("p");

                        var button = document.createElement("input");

                        //Creazione attributi

                        var username_type = document.createAttribute("type");
                        var email_type = document.createAttribute("type");
                        var phone_type = document.createAttribute("type");
                        var username_style = document.createAttribute("style");
                        var email_style = document.createAttribute("style");
                        var phone_style = document.createAttribute("style");
                        var username_value = document.createAttribute("value");
                        var email_value = document.createAttribute("value");
                        var phone_value = document.createAttribute("value");
                        var username_id = document.createAttribute("id");
                        var email_id = document.createAttribute("id");
                        var phone_id = document.createAttribute("id");

                        var username_title = document.createAttribute("style");
                        var email_title = document.createAttribute("style");
                        var phone_title = document.createAttribute("style");
                        
                        var button_type = document.createAttribute("type");
                        var button_value = document.createAttribute("value");
                        var button_style = document.createAttribute("style");
                        var button_onclick = document.createAttribute("onclick");

                        //TextNode

                        var username_text = document.createTextNode("Username");
                        var email_text = document.createTextNode("Email");
                        var phone_text = document.createTextNode("Phone Number");

                        username_p.appendChild(username_text);
                        email_p.appendChild(email_text);
                        phone_p.appendChild(phone_text);

                        //Assegnamento valore atrtributi

                        //alert(jsonResponse);

                        username_type.value="text";
                        email_type.value="text";
                        phone_type.value="text";
                        username_style.value="display: block; margin-top: 2em; height: 3em; width: 20em; margin-left: 3em;";
                        email_style.value="display: block; margin-top: 2em; height: 3em; width: 20em; margin-left: 3em;";
                        phone_style.value="display: block; margin-top: 2em; height: 3em; width: 20em; margin-left: 3em;";
                        username_value.value=jsonResponse[0].username;
                        email_value.value=jsonResponse[0].email;
                        phone_value.value=jsonResponse[0].phone_number;
                        username_id.value = "usernameID";
                        email_id.value = "emailID";
                        phone_id.value = "phoneID";

                        username_title.value="margin-top: 2em; font-weigth: bold; margin-left: 3em;";
                        email_title.value="margin-top: 2em; font-weigth: bold; margin-left: 3em;";
                        phone_title.value="margin-top: 2em; font-weigth: bold; margin-left: 3em;";

                        button_type.value = "button";
                        button_value.value = "Send";
                        button_style.value = "margin-top: 2em; margin-left: 3em;";
                        button_onclick.value = "updateRecord('users')";

                        //Assegnamento attributi -> elementi

                        username_field.setAttributeNode(username_type);
                        email_field.setAttributeNode(email_type);
                        phone_field.setAttributeNode(phone_type);
                        username_field.setAttributeNode(username_style);
                        email_field.setAttributeNode(email_style);
                        phone_field.setAttributeNode(phone_style);
                        username_field.setAttributeNode(username_value);
                        email_field.setAttributeNode(email_value);
                        phone_field.setAttributeNode(phone_value);
                        username_field.setAttributeNode(username_id);
                        email_field.setAttributeNode(email_id);
                        phone_field.setAttributeNode(phone_id);

                        username_p.setAttributeNode(username_title);
                        email_p.setAttributeNode(email_title);
                        phone_p.setAttributeNode(phone_title);

                        button.setAttributeNode(button_type);
                        button.setAttributeNode(button_value);
                        button.setAttributeNode(button_style);
                        button.setAttributeNode(button_onclick);

                        //Costruzione elementi

                        document.getElementById("update_form").appendChild(username_p);
                        document.getElementById("update_form").appendChild(username_field);
                        document.getElementById("update_form").appendChild(email_p);
                        document.getElementById("update_form").appendChild(email_field);
                        document.getElementById("update_form").appendChild(phone_p);
                        document.getElementById("update_form").appendChild(phone_field);
                        document.getElementById("update_form").appendChild(button);

                        break;
                    case 'species':
                        //Creazione Elementi

                        var common_name_field = document.createElement("input");
                        var scientific_name_field = document.createElement("input");

                        var common_name_p = document.createElement("p");
                        var scientific_name_p = document.createElement("p");

                        var button = document.createElement("input");

                        //Creazione attributi

                        var common_name_type = document.createAttribute("type");
                        var scientific_name_type = document.createAttribute("type");
                        var common_name_style = document.createAttribute("style");
                        var scientific_name_style = document.createAttribute("style");
                        var common_name_value = document.createAttribute("value");
                        var scientific_name_value = document.createAttribute("value");
                        var common_name_id = document.createAttribute("id");
                        var scientific_name_id = document.createAttribute("id");

                        var common_name_title = document.createAttribute("style");
                        var scientific_name_title = document.createAttribute("style");
                        
                        var button_type = document.createAttribute("type");
                        var button_value = document.createAttribute("value");
                        var button_style = document.createAttribute("style");
                        var button_onclick = document.createAttribute("onclick");

                        //TextNode

                        var common_name_text = document.createTextNode("Common Name");
                        var scientific_name_text = document.createTextNode("Scientific Name");

                        common_name_p.appendChild(common_name_text);
                        scientific_name_p.appendChild(scientific_name_text);

                        //Assegnamento valore atrtributi

                        common_name_type.value="text";
                        scientific_name_type.value="text";
                        common_name_style.value="display: block; margin-top: 2em; height: 3em; width: 20em; margin-left: 3em;";
                        scientific_name_style.value="display: block; margin-top: 2em; height: 3em; width: 20em; margin-left: 3em;";
                        common_name_value.value=jsonResponse[0].common_name;
                        scientific_name_value.value=jsonResponse[0].scientific_name;
                        common_name_id.value = "common_nameID";
                        scientific_name_id.value = "scientific_nameID";

                        common_name_title.value="margin-top: 2em; font-weigth: bold; margin-left: 3em;";
                        scientific_name_title.value="margin-top: 2em; font-weigth: bold; margin-left: 3em;";

                        button_type.value = "button";
                        button_value.value = "Send";
                        button_style.value = "margin-top: 2em; margin-left: 3em;";
                        button_onclick.value = "updateRecord('species')";

                        //Assegnamento attributi -> elementi

                        common_name_field.setAttributeNode(common_name_type);
                        scientific_name_field.setAttributeNode(scientific_name_type);
                        common_name_field.setAttributeNode(common_name_style);
                        scientific_name_field.setAttributeNode(scientific_name_style);
                        common_name_field.setAttributeNode(common_name_value);
                        scientific_name_field.setAttributeNode(scientific_name_value);
                        common_name_field.setAttributeNode(common_name_id);
                        scientific_name_field.setAttributeNode(scientific_name_id);

                        common_name_p.setAttributeNode(common_name_title);
                        scientific_name_p.setAttributeNode(scientific_name_title);

                        button.setAttributeNode(button_type);
                        button.setAttributeNode(button_value);
                        button.setAttributeNode(button_style);
                        button.setAttributeNode(button_onclick);

                        //Costruzione elementi

                        document.getElementById("update_form").appendChild(common_name_p);
                        document.getElementById("update_form").appendChild(common_name_field);
                        document.getElementById("update_form").appendChild(scientific_name_p);
                        document.getElementById("update_form").appendChild(scientific_name_field);
                        document.getElementById("update_form").appendChild(button);
                        break;
                    case 'races':
                        //Creazione Elementi

                        var name_field = document.createElement("input");
                        var information_field = document.createElement("input");
                        var size_field = document.createElement("input");
                        var fur_type_field = document.createElement("input");
                        var url_field = document    .createElement("input");

                        var name_p = document.createElement("p");
                        var information_p = document.createElement("p");
                        var size_p = document.createElement("p");

                        var button = document.createElement("input");

                        //Creazione attributi

                        var name_type = document.createAttribute("type");
                        var information_type = document.createAttribute("type");
                        var size_type = document.createAttribute("type");
                        var name_style = document.createAttribute("style");
                        var information_style = document.createAttribute("style");
                        var size_style = document.createAttribute("style");
                        var name_value = document.createAttribute("value");
                        var information_value = document.createAttribute("value");
                        var size_value = document.createAttribute("value");
                        var name_id = document.createAttribute("id");
                        var information_id = document.createAttribute("id");
                        var size_id = document.createAttribute("id");

                        var name_title = document.createAttribute("style");
                        var information_title = document.createAttribute("style");
                        var size_title = document.createAttribute("style");
                        
                        var button_type = document.createAttribute("type");
                        var button_value = document.createAttribute("value");
                        var button_style = document.createAttribute("style");
                        var button_onclick = document.createAttribute("onclick");

                        //TextNode

                        var name_text = document.createTextNode("Name");
                        var information_text = document.createTextNode("information");
                        var size_text = document.createTextNode("size");

                        name_p.appendChild(name_text);
                        information_p.appendChild(information_text);
                        size_p.appendChild(size_text);

                        //Assegnamento valore atrtributi

                        //alert(jsonResponse);

                        name_type.value="text";
                        information_type.value="text";
                        size_type.value="text";
                        name_style.value="display: block; margin-top: 2em; height: 3em; width: 20em; margin-left: 3em;";
                        information_style.value="display: block; margin-top: 2em; height: 3em; width: 20em; margin-left: 3em;";
                        size_style.value="display: block; margin-top: 2em; height: 3em; width: 20em; margin-left: 3em;";
                        name_value.value=jsonResponse.name;
                        information_value.value=jsonResponse.information;
                        size_value.value=jsonResponse.size;
                        name_id.value = "nameID";
                        information_id.value = "informationID";
                        size_id.value = "sizeID";

                        name_title.value="margin-top: 2em; font-weigth: bold; margin-left: 3em;";
                        information_title.value="margin-top: 2em; font-weigth: bold; margin-left: 3em;";
                        size_title.value="margin-top: 2em; font-weigth: bold; margin-left: 3em;";

                        button_type.value = "button";
                        button_value.value = "Send";
                        button_style.value = "margin-top: 2em; margin-left: 3em;";
                        button_onclick.value = "updateRecord('races')";

                        //Assegnamento attributi -> elementi

                        name_field.setAttributeNode(name_type);
                        information_field.setAttributeNode(information_type);
                        size_field.setAttributeNode(size_type);
                        name_field.setAttributeNode(name_style);
                        information_field.setAttributeNode(information_style);
                        size_field.setAttributeNode(size_style);
                        name_field.setAttributeNode(name_value);
                        information_field.setAttributeNode(information_value);
                        size_field.setAttributeNode(size_value);
                        name_field.setAttributeNode(name_id);
                        information_field.setAttributeNode(information_id);
                        size_field.setAttributeNode(size_id);


                        name_p.setAttributeNode(name_title);
                        information_p.setAttributeNode(information_title);
                        size_p.setAttributeNode(size_title);

                        button.setAttributeNode(button_type);
                        button.setAttributeNode(button_value);
                        button.setAttributeNode(button_style);
                        button.setAttributeNode(button_onclick);

                        //Costruzione elementi

                        document.getElementById("update_form").appendChild(name_p);
                        document.getElementById("update_form").appendChild(name_field);
                        document.getElementById("update_form").appendChild(information_p);
                        document.getElementById("update_form").appendChild(information_field);
                        document.getElementById("update_form").appendChild(size_p);
                        document.getElementById("update_form").appendChild(size_field);
                        document.getElementById("update_form").appendChild(button);
                        break;
                }
            }
        }
    }
    xhttp.open("GET", URIAddr);
    xhttp.send(null);

    /*
    //alert(check.get('selected'));
    }*/
}

function updateRecord(type){
    //alert(type);
    var URIAddr;
    switch(type){
        case "users":
            var username = document.getElementById("usernameID").value;
            var email = document.getElementById("emailID").value;
            var phone_number = document.getElementById("phoneID").value;
            var id_user = (new URLSearchParams(window.location.search)).get('id');

            URIAddr = "../../../updateUser/"+username+"/"+email+"/"+phone_number+"/"+id_user;
            alert(URIAddr);
            break;
        case "species":
            var common_name = document.getElementById("common_nameID").value;
            var scientific_name = document.getElementById("scientific_nameID").value;
            var id_specie = (new URLSearchParams(window.location.search)).get('id');

            URIAddr = "../../../updateSpecie/"+common_name+"/"+scientific_name+"/"+id_specie;
            break;
        case "races":
            var name = document.getElementById("nameID").value;
            var information = document.getElementById("informationID").value;
            var size = document.getElementById("sizeID").value;
            var id_specie = (new URLSearchParams(window.location.search)).get('id');

            URIAddr = "../../../updateRace/"+name+"/"+information+"/"+size+"/"+id_specie;

            break;
    }
    //alert(URIAddr);

    var xhttp = new XMLHttpRequest();
    //var jsonResponse;

    xhttp.onreadystatechange = function() {
        if (this.status == 200 && xhttp.readyState == 4) {
            alert("Update done succesfully!");
        }else{
            //alert("Something wrong, retry!")
        }
    }

    xhttp.open("PUT", URIAddr);
    xhttp.send(null);
}