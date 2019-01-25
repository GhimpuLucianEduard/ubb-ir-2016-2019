<?php
    session_start();
    include "dbconfig.php";

	$Connection = new mysqli($servername, $username, $password, $dbname);

    $cmd = "SELECT * from cereriprimarie
            where verificat is null or email = ?";

	$stmt = $Connection->prepare($cmd);
	$stmt->bind_param("s", $_SESSION["email"]);
	$stmt->execute();
	$stmt->bind_result($col1,$col2,$col3,$col4,$col5,$col6,$col7);

    $count2 = 0;
    $data = array();
	while ($stmt->fetch()) {

        $myObj = new stdClass();
        $myObj->id = $col1;
        $myObj->nume = $col2;

        if ($_SESSION["email"] != $col3) {
            $count = strlen($col3);
            $output = substr_replace($col3, str_repeat('*', $count-4), 5);
            $myObj->email = $output;
        } else {
            $myObj->email = $col3;
        }


        if ($_SESSION["email"] != $col3) {
            $count = strlen($col4);
            $output = substr_replace($col4, str_repeat('*', $count-4), 5);
            $myObj->tel = $output;
        } else {
            $myObj->tel = $col4;
        }

        $myObj->data = $col5;
        $myObj->fisier = $col6;
        $myObj->verificat = $col7;


        $data[$count2] = $myObj;
        $count2 += 1;
	}

    echo json_encode($data);
	$stmt->close();

?>