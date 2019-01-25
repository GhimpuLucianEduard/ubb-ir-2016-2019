<?php

    include "../dbconfig.php"; 

    // Create Connection
    $Connection = new mysqli($servername, $username, $password, $dbname);
    $_SERVER['CONTENT_TYPE'] = "application/x-www-form-urlencoded";
    $nume = $_POST["nume"] ;
    $url = $_POST["url"];
    $descriere = $_POST["descriere"];
    $pret = $_POST["pret"];
    
    // SQL command
	$cmd = "UPDATE pizza
            SET poza = ?, pret = ?, descriere = ?
            WHERE nume = ?";

    $stmt = $Connection->prepare($cmd);
    $stmt->bind_param("siss", $url, $pret, $descriere, $nume);
    $stmt->execute();

    // Close conn and stmt
    $stmt->close();
?>