<?php
	$link = mysqli_connect("localhost:3306" , "root", "");
	
	if ($link == false) {
		echo "Error: can't connect to database server";
		exit;
	}
	
	if (mysqli_select_db($link, "pb5db") == false) {
		echo "Error: can't connect to database";
		exit;
	}
?>