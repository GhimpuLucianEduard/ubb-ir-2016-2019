var baseUrl = "http://localhost:52008/api/";
$(document).ready(Init);

function Init() {
    getAllCurse();
    $('#table').on('click','tr',onSelect);
    $('#delete').on('click', onDelete);
    $('#add').on('click', onAdd);
}

function getAllCurse() {

    var uri = baseUrl + "Curse";

    $.ajax({
        type: "GET",
        url: uri,
        dataType:"json",
        success: function(response){
                buildTable(response);        
      },
      error: function(jqXHR, textStatus, errorThrown){
          alert('error');
      }         
    });

}

function onDelete() {

    var uri = baseUrl + "Curse/";
    uri += $('#inputId').val();

    $.ajax({
        type: "DELETE",
        url: uri,
        success: function(response){
            location.reload();              
        }
    });
}

function onAdd() {

    var uri = baseUrl + "Curse";

    var json = JSON.stringify({ "IdDestinatie": $('#inputIdDest').val(), "DataPlecare" : $('#inputData').val(), "LocPlecare": $('#inputLocPlecare').val(), "NrLocuriDisponibile": $('#inputNrLocuri').val()});
    

    $.ajax({
        type: "POST",
        dataType:"json",
        url: uri,
        contentType: 'application/json',
        data: json,
        success: function(response){
            location.reload();              
        }
    });
}

function buildTable(data) {
    $.each(data, function (i, item) {
        var trHTML = "";
        trHTML += '<tr><td id="tdId">' + item.id + '</td><td id="tdIdDest">' + item.idDestinatie + '</td><td id="tdData">' + item.dataPlecare + '</td><td id="tdPlecare">' + item.locPlecare + '</td><td id="tdLocuri">' + item.nrLocuriDisponibile + '</td></tr>';
        $('#table').append(trHTML);
    });
    
}

function onSelect() {
    var id = $(this).find('#tdId').text();
    var idDest = $(this).find('#tdIdDest').text();
    var data = $(this).find('#tdData').text();
    var loc = $(this).find('#tdPlecare').text();
    var nr = $(this).find('#tdLocuri').text();

    $('#inputId').val(id);
    $('#inputIdDest').val(idDest);
    $('#inputData').val(data);
    $('#inputLocPlecare').val(loc);
    $('#inputNrLocuri').val(nr);
}