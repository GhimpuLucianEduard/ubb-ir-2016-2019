<?php

    include "dbconfig.php";

	$Connection = new mysqli($servername, $username, $password, $dbname);

	$cmd = "select i.text, i.data, u.email, u.avatar, i.id from intrebari i inner join users u on i.user = u.id ORDER by i.data";

	$stmt = $Connection->prepare($cmd);
	$stmt->execute();
    $stmt->bind_result($col1, $col2, $col3, $col4, $col5);
    
    $count = 0;
    $data = array();
	while ($stmt->fetch()) {

        $myObj = new stdClass();
        $myObj->text = $col1;
        $myObj->data = $col2;
        $myObj->email = $col3;
        $myObj->avatar = $col4;
        $myObj->id = $col5;

        $data[$count] = $myObj;
        $count += 1;
	}

    echo json_encode($data);
	$stmt->close();
?>