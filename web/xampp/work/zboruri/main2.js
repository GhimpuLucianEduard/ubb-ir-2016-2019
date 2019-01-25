
$(document).ready(Initialize);

var index = 0;

function Initialize() {

    $("#btnPlus").on("click", handleAdd);
    $("#btnMinus").on("click", handleDelete);

}

function handleAdd() {

    index++;
    var input1 = "<input name=\"persoane[]\"  placeholder=\"Nume\" type=\"text\" >";
    var input2 = "<input name=\"persoane[]\" placeholder=\"Prenume\" type=\"text\" >";
    var li = "<li>" + input1 + input2 + "</li>";
    $("#listaInuturi").append(li);
}

function handleDelete() {

    $('#listaInuturi li:last-child').remove();
    index--;
}