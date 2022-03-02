function Menu_Selection(){
    var check = new URLSearchParams(window.location.search);
    //alert(check.get('selected'));
    document.getElementById(check.get('selected')).style.background = "#ffffff91";
    document.getElementById(check.get('selected')).style.color = "#524a4a"
}

function checkAlert(){
    alert("ok");
}