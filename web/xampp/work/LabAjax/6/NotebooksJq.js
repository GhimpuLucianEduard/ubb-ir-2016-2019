$(document).ready(Initialize);

function Initialize()
{
	getSelects();
	
	getNotebooks();
}

function filterNotebooks()
{
	var producator = $("#selectproducator").find(":selected").text();
	var procesor = $("#selectprocesor").find(":selected").text();
	var memorie = $("#selectmemorie").find(":selected").text();
	var placavideo = $("#selectplacavideo").find(":selected").text();
	
	var urlGenerated = "showNotebooksFiltered.php";
	urlGenerated +="?producator=";
	urlGenerated +=producator;
	urlGenerated +="&procesor=";
	urlGenerated +=procesor;
	urlGenerated +="&memorie=";
	urlGenerated +=memorie;
	urlGenerated +="&placavideo=";
	urlGenerated +=placavideo;
	
	$.ajax({
		type: "GET",
		url: urlGenerated,
		success: function(data,status) {
			$("#ContainerNotebooks").html(data);
		}
	})
}

function getNotebooks() {
	$.ajax({
		type: "GET",
		url: "showNotebooks.php",
		success: function(data,status) {
			$("#ContainerNotebooks").html(data);
		}
	})
}

function getSelects() {
	$.ajax({
		type: "GET",
		url: "showSelects.php",
		success: function(data,status) {
			$("#ContainerSelects").html(data);
		}
	})
}