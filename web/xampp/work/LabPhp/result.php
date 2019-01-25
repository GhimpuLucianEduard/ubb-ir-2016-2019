<?php

include "settings.php";

session_start();
$string = strtoupper($_SESSION['string']);
$userstring = strtoupper($_POST['userstring']);
$poza = $_POST['poza'];
$success = $success . $poza;
session_destroy();   

if (($string == $userstring) && (strlen($string) > 4)) {
	header("Location: $success");
	exit();
} else {
	header("Location: $failure");
	exit();
}
?>