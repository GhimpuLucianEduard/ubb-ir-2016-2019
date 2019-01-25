<?php
	session_start();
	
	include 'db.php';

	$username = $_POST['username'];
	$password = $_POST['password'];
	$sql = "select password from users where name = '$username' and password = md5('$password')";
	$result = mysqli_query($link, $sql);
	
	if ($result == false) {
		echo '<a href="login.php">Error: cannot execute query</a>';
		exit;
	}
	
	$num_rows = mysqli_num_rows($result);

	if ($num_rows >= 1) {
		$_SESSION['login'] = "OK";
		$_SESSION['username'] = $username;
		header('Location: private.php');
		mysqli_close($link);
		die();
	}
	
	mysqli_close($link);
	
	header('Location: login.php');
?>