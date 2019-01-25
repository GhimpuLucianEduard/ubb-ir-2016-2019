<?php

    include "dbconfig.php";

    $id = $_GET["id"];

	$Connection = new mysqli($servername, $username, $password, $dbname);

	$cmd = "select r.text, u.email, u.avatar, i.id from raspunsuri r inner join intrebari i on i.id = r.intrebare inner join users u on u.id = r.user where i.id = ?";

	$stmt = $Connection->prepare($cmd);
    $stmt->bind_param("i",$id);
    $stmt->execute();
    $stmt->bind_result($col1, $col2, $col3, $col4);
    
    $count = 0;
    $data = array();
	while ($stmt->fetch()) {

        $myObj = new stdClass();
        $myObj->text = $col1;
        $myObj->email = $col2;
        $myObj->avatar = $col3;
        $myObj->idInt = $col4;

        $data[$count] = $myObj;
        $count += 1;
	}

    echo json_encode($data);
	$stmt->close();
?>