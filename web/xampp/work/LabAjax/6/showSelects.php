<?php
	include "../dbconfig.php";
	
	$Connection = new mysqli($servername, $username, $password, $dbname);

	if($Connection->connect_error) {
		die("Connection failes: " . $Connection->connect_error);
	}

	// Select Producator
	$cmd = "SELECT producator FROM notebooks group by producator";
	$stmt = $Connection->prepare($cmd);
	$stmt->execute();
	$stmt->bind_result($producator);
	echo "<select id=selectproducator onchange=filterNotebooks()> ";
	while ($stmt->fetch()) {
			echo "<option value=" . $producator . ">" . $producator . "</option>";
        }
    echo "</select> ";
	
	// Select Procesor
	$cmd = "SELECT procesor FROM notebooks group by procesor";
	$stmt = $Connection->prepare($cmd);
	$stmt->execute();
	$stmt->bind_result($procesor);
	echo "<select id=selectprocesor onchange=filterNotebooks()> ";
	while ($stmt->fetch()) {
			echo "<option value=" . $procesor . ">" . $procesor . "</option>";
        }
    echo "</select> ";
	
	// Select memorie
	$cmd = "SELECT memorie FROM notebooks group by memorie";
	$stmt = $Connection->prepare($cmd);
	$stmt->execute();
	$stmt->bind_result($memorie);
	echo "<select id=selectmemorie onchange=filterNotebooks()> ";
	while ($stmt->fetch()) {
			echo "<option value=" . $memorie . ">" . $memorie . "</option>";
        }
    echo "</select> ";
	
	// Select placavideo
	$cmd = "SELECT placavideo FROM notebooks group by placavideo";
	$stmt = $Connection->prepare($cmd);
	$stmt->execute();
	$stmt->bind_result($placavideo);
	echo "<select id=selectplacavideo onchange=filterNotebooks()> ";
	while ($stmt->fetch()) {
			echo "<option value=" . $placavideo . ">" . $placavideo . "</option>";
        }
    echo "</select> ";
	
	$stmt->close();
?>