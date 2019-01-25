var xmlhttp;
var id = 0;
window.addEventListener('load', getPersoane);

function getPersoane() {
	xmlhttp = getXmlHttpObject();
	
	id = id + 3;
	var url = "showPersoane.php";
	url +="?id=";
	url +=id;

	
	xmlhttp.onreadystatechange = AddToContainerPersoane;
	xmlhttp.open("GET", url, true);
	xmlhttp.send(null);
}

function getPersoanePrev() {
	xmlhttp = getXmlHttpObject();

		id = id - 3;
	var url = "showPersoane.php";
	url +="?id=";
	url +=id;

	
	xmlhttp.onreadystatechange = AddToContainerPersoane;
	xmlhttp.open("GET", url, true);
	xmlhttp.send(null);
}

function AddToContainerPersoane() {

	if(xmlhttp.readyState == 4 && xmlhttp.status ==200) {
		document.getElementById("ContainerPersoane").innerHTML = xmlhttp.responseText;
	}
}

function getXmlHttpObject() {

	if(window.XMLHttpRequest) {
	  return new XMLHttpRequest();
	}
	return null;
}