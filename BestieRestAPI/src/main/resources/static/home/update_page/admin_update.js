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

                        //Creazione Elementi

                        var username_field = document.createElement("input");
                        var email_field = document.createElement("input");
                        var phone_field = document.createElement("input");

                        var username_p = document.createElement("p");
                        var email_p = document.createElement("p");
                        var phone_p = document.createElement("p");

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

                        var username_title = document.createAttribute("style");
                        var email_title = document.createAttribute("style");
                        var phone_title = document.createAttribute("style");
                        
                        //TextNode

                        var username_text = document.createTextNode("Username");
                        var email_text = document.createTextNode("Email");
                        var phone_text = document.createTextNode("Phone Number");

                        username_p.appendChild(username_text);
                        email_p.appendChild(email_text);
                        phone_p.appendChild(phone_text);

                        //Assegnamento valore atrtributi

                        username_type.value="text";
                        email_type.value="text";
                        phone_type.value="text";
                        username_style.value="display: block; margin-top: 2em; height: 3em; width: 20em; margin-left: 3em;";
                        email_style.value="display: block; margin-top: 2em; height: 3em; width: 20em; margin-left: 3em;";
                        phone_style.value="display: block; margin-top: 2em; height: 3em; width: 20em; margin-left: 3em;";
                        username_value.value=jsonResponse[0].username;
                        email_value.value=jsonResponse[0].email;
                        phone_value.value=jsonResponse[0].phone_number;

                        username_title.value="margin-top: 2em; font-weigth: bold; margin-left: 3em;";
                        email_title.value="margin-top: 2em; font-weigth: bold; margin-left: 3em;";
                        phone_title.value="margin-top: 2em; font-weigth: bold; margin-left: 3em;";
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

                        username_p.setAttributeNode(username_title);
                        email_p.setAttributeNode(email_title);
                        phone_p.setAttributeNode(phone_title);

                        //Costruzione elementi

                        document.getElementById("update_form").appendChild(username_p);
                        document.getElementById("update_form").appendChild(username_field);
                        document.getElementById("update_form").appendChild(email_p);
                        document.getElementById("update_form").appendChild(email_field);
                        document.getElementById("update_form").appendChild(phone_p);
                        document.getElementById("update_form").appendChild(phone_field);

                        break;
                    case 'species':
                        break;
                    case 'races':
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