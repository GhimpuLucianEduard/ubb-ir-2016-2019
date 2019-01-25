<?php
	include "../dbconfig.php";
	
	$producatorFromJS = $_GET["producator"];
	$procesorFromJS = $_GET["procesor"];
	$memorieFromJS = $_GET["memorie"];
	$placavideoFromJS = $_GET["placavideo"];
	
	$Connection = new mysqli($servername, $username, $password, $dbname);

	if($Connection->connect_error) {
		die("Connection failes: " . $Connection->connect_error);
	}

	$cmd = "SELECT * FROM notebooks WHERE producator = ? AND
										  procesor = ? AND
										  memorie = ? AND
										  placavideo = ?";

	$stmt = $Connection->prepare($cmd);
	$stmt->bind_param("ssss", $producatorFromJS, $procesorFromJS, $memorieFromJS, $placavideoFromJS);
	$stmt->execute();
	$stmt->bind_result($id, $producator, $procesor, $memorie, $placavideo);

	//Build list
	$counterRows = 0;
	echo "<table border=1>";
		echo "<tr>";
			echo "<th>id</th>";
			echo "<th>producator</th>";
			echo "<th>procesor</th>";
			echo "<th>memorie</th>";
			echo "<th>placavideo</th>";
        echo "</tr>";
	while ($stmt->fetch()) {
        echo "<tr>";
			echo "<td>" . $id . "</td>";
            echo "<td>" . $producator . "</td>";
            echo "<td>" . $procesor . "</td>";
            echo "<td>" . $memorie . "</td>";
            echo "<td>" . $placavideo . "</td>";
        echo "</tr>";
        }
    echo "</table>";
	
	$stmt->close();
?>