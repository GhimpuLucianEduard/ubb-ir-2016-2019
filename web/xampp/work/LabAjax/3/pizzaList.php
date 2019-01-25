<?php

    include "../dbconfig.php";

    // Create Connection
	$Connection = new mysqli($servername, $username, $password, $dbname);

	// Check Connection
	if($Connection->connect_error) {
		die("Connection failes: " . $Connection->connect_error);
	}

	// SQL command
	$cmd = "SELECT nume
			FROM Pizza";

	// Execute stmt
	$stmt = $Connection->prepare($cmd);
	$stmt->execute();
	$stmt->bind_result($numePizza);

	// Build select
	echo "<ol id=\"pizzaList\">";

	//Build options
	while ($stmt->fetch()) {
		echo "<li>" . $numePizza . "</li>";
	}

	echo "</ol>";
	
	// Close conn and stmt
	$stmt->close();
	// $Connection.close();
?>