
$(document).ready(Initialize);

var zboruri;

function Initialize() {

    getZboruri();
    

}

function getZboruri() {

	$.ajax({
		type: "GET",
		url: "getZboruriJson.php",
		success: function(data,status) {
            console.log(data);
            zboruri=data;
            buildTable(data);
		}
	})
}

function buildTable(data) {

    JSON.parse(data).forEach(element => {

        // var key2 = element.plecare+"/"+element.sosire;

        // if(dict[key2]!=undefined){
        //     dict[key2].push(element);
        // }else {
        //     dict[key2]=[];
        //     dict[key2].push(element);
        // }
        var zboruri = data;
        var td0 = "<td>" + element.id + "</td>";
        var td1 = "<td>" + element.plecare + "</td>";
        var td2 = "<td>" + element.sosire + "</td>";

        var idDatepicker = "datepicker"+element.id;
        var picker = "<input type=\"text\" id=\""+idDatepicker+"\">"
        $( function() {
            $( "#"+idDatepicker ).datepicker();
        } );


        console.log(element.zi);
        $("#"+idDatepicker).datepicker("option", {
            beforeShowDay: function (date)
            {
                return [date.getDay() == element.zi, ''];
            }
        });
        
        // prevent changing weeks and months
        var weekOptions = { beforeShowDay: function (date) {
                return [date.getDay() == element.zi, ''];
            } 
        };
        $(function () {
            $("#"+idDatepicker).datepicker("option", weekOptions);
            $("#"+idDatepicker).datepicker()

            
            .on("input change", function (e) {
            
            $("#dataAleasa").val(e.target.value);
            console.log("Date changed: ", e.target.value);
        });
        });

        // $("#"+idDatepicker).datepicker({
        //     minDate: 0
        // });
        // var minDate = $("#"+idDatepicker).datepicker('getDate');
        // minDate.setDate(minDate.getDate()); //add two days
        // $("#"+idDatepicker).datepicker( "option", "minDate", minDate);


        var td3 = "<td>" + picker + "</td>";
        var tr = "<tr id=\"" + element.id + "\">"+ td0+td1+td2+td3+ "</tr>"; 


        $('#tableZboruri tr:last').after(tr);

        // var idDatepicker = "datepicker"+element.id;
        // var picker = "<input type=\"date\" id=\""+idDatepicker+"\">";

        // var td3 = "<td>" + picker + "</td>";

        // var tr = "<tr id=\"" + element.id + "\">" +td1+td2+td3+ "</tr>"; 
        // $('#tableZboruri tr:last').after(tr);



    });

    $("#tableZboruri tr").click(function() {

        $("#ales").val($(this).attr("id"));
        
        var selected = $(this).hasClass("highlight");
        $("#tableZboruri tr").removeClass("highlight");
        if(!selected)
                $(this).addClass("highlight");
    });

}