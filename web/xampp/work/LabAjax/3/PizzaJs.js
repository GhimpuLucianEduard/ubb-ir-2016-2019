var xmlhttp;
var isSelectedPizza;
var hasChanges;
$(document).ready(Init);

function Init() {
	isSelectedPizza = false;
	getPizza();
	$('#saveButton').on('click', updatePizza);
	$('#pizzaPret').change(enableSave);
	$('#pizzaUrl').change(enableSave);
	$('#pizzaDesc').bind('input propertychange', enableSave);
}

function enableSave() {

	if (!isSelectedPizza) {
		$('#saveButton').attr("disabled", true);
	}
	else {
		$('#saveButton').attr("disabled", false);
		hasChanges = true;
	}
	
}

function getPizza() {

	xmlhttp = getXmlHttpObject();

	var url = "pizzaList.php";
	xmlhttp.onreadystatechange = AddToPizzaList;
	xmlhttp.open("GET", url, true);
	xmlhttp.send(null);

}

function getXmlHttpObject() {

	if(window.XMLHttpRequest) {
	  return new XMLHttpRequest();
	}
	return null;
}

function getDetalii() {

	if (hasChanges) {
		if (confirm("There are unsaved changes, would you like to save them?")) {
			updatePizza();
		}
	}


	isSelectedPizza = true;
	hasChanges = false;
	$('#saveButton').attr("disabled", true);
	var pizzaName = $(this).html();
	xmlhttp = getXmlHttpObject();
	
	var url = "pizzaDetails.php?pizzaName=";
	url += pizzaName;
	xmlhttp.onreadystatechange = AddDetailsToForm;
	xmlhttp.open("GET", url, true);
	xmlhttp.send(null);
}

function AddToPizzaList() {

	if(xmlhttp.readyState == 4 && xmlhttp.status ==200) {
			$('#listContainer').html(xmlhttp.responseText);
			$('#pizzaList').on("click", "li", getDetalii);
	}
}

function AddDetailsToForm() {

	if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
		var obj = JSON.parse(xmlhttp.responseText);
		$('#pizzaImg').attr("src", obj.url);
		$('#pizzaUrl').val(obj.url);
		$('#pizzaName').val(obj.numePizza);
		$('#pizzaPret').val(obj.pret);
		$('#pizzaDesc').val(obj.descriere);
	}
}

function updatePizza() {

	var errMsg = validateInput();
	if (errMsg != "") {
		alert(errMsg);
		return;
	}

	xmlhttp = getXmlHttpObject();

	var urlRequest = "updatePizza.php";
	var descriere = $('#pizzaDesc').val();
	var url = $('#pizzaUrl').val();
	var pret = $('#pizzaPret').val();
	var nume = $('#pizzaName').val();
	var data = "nume=" + nume + "&descriere=" + descriere + "&url=" + url + "&pret=" + pret;
	console.log(urlRequest);
	xmlhttp.onreadystatechange = postPizzaUpdated;
	xmlhttp.open("POST", urlRequest, true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.send(data);


}

function postPizzaUpdated() {

	if(xmlhttp.readyState == 4 && xmlhttp.status ==200) {
		hasChanges = false;
		alert("The pizza has been updated!");
	}
}

function validateInput() {

	var errMsg = "";

	if ($('#pizzaUrl').val() == "") {
		errMsg += "Please type the img url! \n"
	}

	if ($('#pizzaDesc').val() == "") {
		errMsg += "The description cannot be empty! \n"
	}
	
	return errMsg;
}