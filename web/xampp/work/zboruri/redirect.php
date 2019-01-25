<?php

    include "dbconfig.php";

    $nume = $_POST["nume"];
    $prenume = $_POST["prenume"];
    $zbor= $_POST["zbor"];
    $data = $_POST["data"];

    //echo "Am primit" . $nume . $prenume . $zbor;

?>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <link rel="stylesheet" type="text/css" href="main.css">
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>


    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css">

    <script src="main2.js"></script>

    <title>Hello, world!</title>
  </head>
  <body>
  <button id="btnPlus">+</button><button id="btnMinus">-</button>
<form  method="POST" action="addRez.php">

  <br>
    Zbor Ales:
            <br>
            <input name="zbor" value=<?php echo $zbor?>  readonly type="number" >
            <br>
            Data:
            <br>
            <input name="data" value=<?php echo $data?>  type="text" readonly >
            <br>
        Nume Initiator:
    <br>
    <input name="persoane[]" type="text" value=<?php echo $nume?>  readonly>
    <br>
    Prenume Initiator:
    <br>
    <input name="persoane[]" type="text" value=<?php echo $prenume?> readonly>
    <br>
    <br>
    <br>

    

    <div id="containerInputuri">

        <ul id="listaInuturi">
        </ul>

    </div>

    <input type="submit">

</form>

  </body>
</html>
