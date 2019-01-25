$(document).ready(Initialize);

function Initialize() {
    setInterval ( getCereri, 1000 );
    getCereri();
}

function getXmlHttpObject() {

	if(window.XMLHttpRequest) {
	  return new XMLHttpRequest();
	}
	return null;
}

function getCereri() {

    xmlhttp = getXmlHttpObject();
    var url = "showCereri.php";
	xmlhttp.onreadystatechange = AddToMainDiv;
	xmlhttp.open("GET", url, true);
	xmlhttp.send(null);

}

function AddToMainDiv() {

	if(xmlhttp.readyState == 4 && xmlhttp.status ==200) {
        console.log(xmlhttp.responseText);
	}
}

