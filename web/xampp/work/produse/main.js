
$(document).ready(Initialize);

function Initialize() {

    getProduse();
    $("#butonDelete").click(deleteProdus);
}

function getProduse() {

	$.ajax({
		type: "GET",
		url: "getProduseJson.php",
		success: function(data,status) {
			buildSelectProduse(data);
		}
	})
}

function deleteProdus()
{   
    var id = $( "#produseSelect option:selected" ).val();
    console.log(id);
    if (id==null)
    {
        return;
    }
    var uri = "deleteProdus.php?id=" + id;
    $.ajax({
		type: "GET",
		url: uri,
		success: function(status) {
            location.reload();
		}
	})
    
}

function buildSelectProduse(data) {

    JSON.parse(data).forEach(element => {
        console.log(element);

        // var option = "<option value=\"" + element.id + "\">" + element.nume + "-" + element.producator + "</option>";
        // $(produseSelect).append(divRaspuns);
        
        $('#produseSelect').append($('<option>', {
            value: element.id,
            text: element.nume + "-" + element.producator
        }));

    });

}
