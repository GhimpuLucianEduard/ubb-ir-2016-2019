$(document).ready(Initialize);
var lista;
function displayModala() 
{  
    $('#myModal').css('display', 'block');
    buidModal($(this));
}

function hideModel() {
    $('#myModal').css('display', 'none');
}

function Initialize() {
    $('#myModal').css('display', 'none');
    $(".close").click(hideModel);
    $("#cauta").on("keyup", getProduse);
    $("#listaRezultate").on("click", "li", displayModala);
}

function getProduse() {

    var text = $(this).val();
    console.log(text);
	$.ajax({
		type: "POST",
        url: "getProduseJson2.php",
        data: {"text": text},
		success: function(data,status) {
            console.log(data);
            buildList(data);
		}
	})
}

function buildList(data) {

    $('#listaRezultate').empty();
    JSON.parse(data).forEach(element => {
        console.log(element);

        var src = "poze/"+element.poza;
        var image = "<image class=\"image\" src=\"" + src + "\">";
        var li = "<li id=\"" + element.id + "\">" + image + element.nume + "</li>";

        $('#listaRezultate').append(li);
    });
    lista = data;
}

function buidModal(li) {
    
    console.log(li);
    JSON.parse(lista).forEach(element => {
        if (element.id == li.attr("id")) {
            var html = "Descriere:" + element.descriere + "\n" + element.producator;
            $("#mc").html(html);
        }
    });

}
