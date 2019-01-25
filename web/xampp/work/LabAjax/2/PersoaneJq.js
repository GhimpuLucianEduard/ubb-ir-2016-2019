var id = 0;

$(document).ready(Initialize);

function Initialize() {

	getPersoane();
}

function getPersoane() {
	id = id + 3;
	var url = "showPersoane.php" + "?id=" + id;

	$.ajax({
		type: "GET",
		url: url,
		success: function(data,status) {
			$("#ContainerPersoane").html(data);
		}
	})
}

function getPersoanePrev() {
	id = id - 3;
	var url = "showPersoane.php" + "?id=" + id;

	$.ajax({
		type: "GET",
		url: url,
		success: function(data,status) {
			$("#ContainerPersoane").html(data);
		}
	})
}