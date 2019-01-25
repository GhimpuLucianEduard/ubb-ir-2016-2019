<?php
	include "../dbconfig.php";
	
	$id = $_GET["id"];
	
	$Connection = new mysqli($servername, $username, $password, $dbname);

	if($Connection->connect_error) {
		die("Connection failes: " . $Connection->connect_error);
	}

	$cmd = "SELECT id, nume, prenume, telefon, email 
			FROM (	SELECT * FROM persoane
					WHERE id <= ?
					ORDER BY id DESC 
					LIMIT 3) t
			ORDER BY id ASC;";

	$stmt = $Connection->prepare($cmd);
	$stmt->bind_param("i", $id);
	$stmt->execute();
	$stmt->bind_result($idFromDb, $nume, $prenume, $telefon, $email);

	//Build list
	$counterRows = 0;
	echo "<ul>";
	while ($stmt->fetch()) {
		echo "<li>" . $nume . " " .$prenume . " " .$telefon . " " .$email ."</li>";
		$counterRows = $counterRows + 1;
		$maxid = $idFromDb;
	}
	echo "</ul>";
	// Build Buttons
	// Daca id-ul primit din js e 3, inseamna ca sunt afisate primele 3 intrari 
	//din db deci nu avem nevoie de prev button
	if($id == 3)
	{
		echo "<button disabled onclick=getPersoanePrev()>Prev</button>";
	}
	else
	{
		echo "<button onclick=getPersoanePrev()>Prev</button>";
	}
	// Daca cel mai mare dintre id-urile afisate e mai mic decat id-ul primit din js
	// nu avem nevoie de next button
	if($id > $maxid)
	{
		echo "<button disabled onclick=getPersoane()>Next</button>";
	}
	else
	{
		echo "<button onclick=getPersoane()>Next</button>";
	}

	
	$stmt->close();
?>
