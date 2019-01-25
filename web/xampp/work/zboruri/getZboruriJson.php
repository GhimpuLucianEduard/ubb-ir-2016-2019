<?php

    include "dbconfig.php";

	$Connection = new mysqli($servername, $username, $password, $dbname);

    $cmd = "SELECT * from zboruri";

	$stmt = $Connection->prepare($cmd);
	$stmt->execute();
	$stmt->bind_result($col1,$col2,$col3,$col4,$col5,$col6);

    $count = 0;
    $data = array();
	while ($stmt->fetch()) {

        $myObj = new stdClass();
        $myObj->id = $col1;
        $myObj->plecare = $col2;
        $myObj->sosire = $col3;
        $myObj->oraPlecare = $col4;
        $myObj->oraSosire = $col5;
        $myObj->zi = $col6;

        $data[$count] = $myObj;
        $count += 1;
	}

    echo json_encode($data);
	$stmt->close();

?>  