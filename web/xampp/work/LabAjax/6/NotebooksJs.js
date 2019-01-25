var xmlhttpForNotebooks;
var xmlhttpForSelects;

window.addEventListener('load', Initialize);

function Initialize()
{
	getSelects();
	
	getNotebooks();
}

function filterNotebooks()
{
	var producator = document.getElementById("selectproducator").value;
	var procesor = document.getElementById("selectprocesor").value;
	var memorie = document.getElementById("selectmemorie").value;
	var placavideo = document.getElementById("selectplacavideo").value;
	
	xmlhttpForNotebooks = getXmlHttpObject();
	
	var url = "showNotebooksFiltered.php";
	url +="?producator=";
	url +=producator;
	url +="&procesor=";
	url +=procesor;
	url +="&memorie=";
	url +=memorie;
	url +="&placavideo=";
	url +=placavideo;
	
	xmlhttpForNotebooks.onreadystatechange = AddToContainerNotebooksFiltered;
	xmlhttpForNotebooks.open("GET", url, true);
	xmlhttpForNotebooks.send(null);
}

function getNotebooks() {
	xmlhttpForNotebooks = getXmlHttpObject();
	
	var url = "showNotebooks.php";

	xmlhttpForNotebooks.onreadystatechange = AddToContainerNotebooks;
	xmlhttpForNotebooks.open("GET", url, true);
	xmlhttpForNotebooks.send(null);
}

function AddToContainerNotebooks() {
	if(xmlhttpForNotebooks.readyState == 4 && xmlhttpForNotebooks.status == 200) {
		document.getElementById("ContainerNotebooks").innerHTML = xmlhttpForNotebooks.responseText;
	}
}

function AddToContainerNotebooksFiltered() {
	if(xmlhttpForNotebooks.readyState == 4 && xmlhttpForNotebooks.status == 200) {
		document.getElementById("ContainerNotebooks").innerHTML = xmlhttpForNotebooks.responseText;
	}
}

function getSelects() {
	xmlhttpForSelects = getXmlHttpObject();
	
	var url = "showSelects.php";

	xmlhttpForSelects.onreadystatechange = AddToContainerSelects;
	xmlhttpForSelects.open("GET", url, true);
	xmlhttpForSelects.send(null);
}

function AddToContainerSelects() {
	if(xmlhttpForSelects.readyState == 4 && xmlhttpForSelects.status ==200) {
		document.getElementById("ContainerSelects").innerHTML = xmlhttpForSelects.responseText;
	}
}

function getXmlHttpObject() {
	if(window.XMLHttpRequest) {
	  return new XMLHttpRequest();
	}
	return null;
}