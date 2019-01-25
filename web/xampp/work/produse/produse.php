<?php

    session_start();
    if(!isset($_SESSION["username"])) {
        header("Location: login.php");
    }

    //id	nume	descriere	producator	pret	cantitate	poza

?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Produse</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="main.css" />
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
    <script src="main.js"></script>
</head>
<body>

    <p><a href="exit.php">Log Out</a></p>
    
    <select id="produseSelect">
    </select>
    <br>
    <br>
    <button id="butonDelete" >Sterge Produs</button>
    <br>
    <br>

    <form method="Post" action="addProdus.php" enctype="multipart/form-data">

        Nume:
        <br>
        <input type="text" name="nume">
        <br>
        Descriere:
        <br>
        <textarea name="descriere"></textarea>
        <br>
        Producator:
        <br>
        <input type="text" name="producator">
        <br>
        Pret:
        <br>
        <input type="number" name="pret">
        <br>
        Cantitate:
        <br>
        <input type="number" name="cantitate">
        <br>
        Poza:
        <br>
        <input type="file" name="file">

        <br>
        <br>
        <input type="submit" name="submit">

    </form>

</body>
</html>