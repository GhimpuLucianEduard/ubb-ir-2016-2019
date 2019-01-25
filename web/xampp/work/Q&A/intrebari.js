$(document).ready(Initialize);
var last;
function Initialize() {

	getIntrebari();
}

function getIntrebari() {

	$.ajax({
		type: "GET",
		url: "getIntrebari.php",
		success: function(data,status) {
			BuildListaIntrebari(data);
		}
	})

}

function BuildListaIntrebari(data) {

    JSON.parse(data).forEach(element => {
        console.log(element);

        var idIntrebare = "i"+element.id;
        var idRaspunsuri = "r"+element.id;
        var olRaspunsuri = "<ol id=\""+idRaspunsuri+"\"></ol>";
        var text  = "Adaugata de: " + element.user + " La data de: " + element.data;
        var li = "<li id=\""+idIntrebare+"\" data-toggle=\"collapse\" data-target=\""+idRaspunsuri+"\">"+ text +  olRaspunsuri+"</li>";
        console.log(li);
        $("#listaIntrebari").append(li);
    });

    $("#listaIntrebari").on("click", "li", getRaspunsuri);
}

function getRaspunsuri()
{  
    
    var id = $(this).attr('id').match(/\d+/)[0];
    console.log(id);
    var uri = "showRaspunsuri.php?id="+id;
    $.ajax({
		type: "GET",
		url: uri,
		success: function(data,status) {
			BuildListaRaspunsuri(data);
		}
	})
}

function BuildListaRaspunsuri(data) {
    
    if (last!=null) {
        $(last).html("");
    }
    var idr = 0;
    JSON.parse(data).forEach(element => {
        console.log(element);

        var divRaspuns = "<div>" +
            "<image src=\"" + element.avatar + "\">" +
            element.text + "By: " + element.email + "</div>";
        
        idr = "#r" + element.idInt;
        $(idr).append(divRaspuns);
    });

    
    last = idr;
}