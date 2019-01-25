<?php

    include "dbconfig.php";

    session_start();
    include "dbconfig.php";

	$Connection = new mysqli($servername, $username, $password, $dbname);

    $cmd = "SELECT * from produse2";

	$stmt = $Connection->prepare($cmd);
	$stmt->execute();
	$stmt->bind_result($col1,$col2,$col3,$col4,$col5,$col6,$col7);

    $count = 0;
    $data = array();
	while ($stmt->fetch()) {

        $myObj = new stdClass();
        $myObj->id = $col1;
        $myObj->nume = $col2;
        $myObj->descriere = $col3;
        $myObj->producator = $col4;
        $myObj->pret = $col5;
        $myObj->cantitate = $col6;
        $myObj->poza = $col7;

        $data[$count] = $myObj;
        $count += 1;
	}

    echo json_encode($data);
	$stmt->close();

?>