<?php

    include "../dbconfig.php"; 

    // Create Connection
    $Connection = new mysqli($servername, $username, $password, $dbname);
    
    $pizzaName = $_GET["pizzaName"];

    // SQL command
	$cmd = "SELECT *
            FROM pizza
            where nume = ?
            limit 1";

    $stmt = $Connection->prepare($cmd);
    $stmt->bind_param("s", $pizzaName);
    $stmt->execute();
    $stmt->bind_result($col1, $col2, $col3, $col4);


	$stmt->fetch();
	$details = new StdClass();
    $details->numePizza = $col1;
	$details->url = $col2;
    $details->descriere = $col3;
    $details->pret = $col4;

    echo json_encode($details);

    // Close conn and stmt
    $stmt->close();
?>