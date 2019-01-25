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

    $.ajax({
        type: "GET",
        url: "pizzaList.php",
        success: function(data, status) {
            $('#listContainer').html(data);
            $('#pizzaList').on("click", "li", getDetalii);
        }
    })
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

    var url = "pizzaDetails.php?pizzaName=";
	url += pizzaName;

    $.ajax({
        type: "GET",
        url: url,
        success: function(data, status) {
            var obj = JSON.parse(data);
            $('#pizzaImg').attr("src", obj.url);
            $('#pizzaUrl').val(obj.url);
            $('#pizzaName').val(obj.numePizza);
            $('#pizzaPret').val(obj.pret);
            $('#pizzaDesc').val(obj.descriere);
        }
    })
}

function updatePizza() {

	var errMsg = validateInput();
	if (errMsg != "") {
		alert(errMsg);
		return;
	}


	var urlRequest = "updatePizza.php";
	var descriere = $('#pizzaDesc').val();
	var url = $('#pizzaUrl').val();
	var pret = $('#pizzaPret').val();
	var nume = $('#pizzaName').val();
	var data = "nume=" + nume + "&descriere=" + descriere + "&url=" + url + "&pret=" + pret;
    
    $.ajax({
        type: "POST",
        url: urlRequest,
        data: data,
        success: function(data, status) {
            hasChanges = false;
		    alert("The pizza has been updated!");
        }
    })
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