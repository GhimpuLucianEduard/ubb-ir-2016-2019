<?php
	
	//Get plecare name
	$Plecare = $_GET["plecare"];

	include "../dbconfig.php";

	//Create Connection
	$Connection = new mysqli($servername, $username, $password, $dbname);

	//Check Connection
	if($Connection->connect_error) {
		die("Connection failes: " . $Connection->connect_error);
	}

	//SQL command
	$cmd = "SELECT Destinatie
			FROM curse
			WHERE Plecare = ?
			GROUP BY Destinatie";

	//Execute stmt
	$stmt = $Connection->prepare($cmd);
	$stmt->bind_param("s", $Plecare);
	$stmt->execute();
	$stmt->bind_result($destinatie);

	//Build select
	echo "<ul>";

	//Build options
	while ($stmt->fetch()) {
		echo "<li>" . $destinatie . "</li>";
	}

	echo "</ul>";
	
	$stmt->close();
	//$Connection.close();
?>