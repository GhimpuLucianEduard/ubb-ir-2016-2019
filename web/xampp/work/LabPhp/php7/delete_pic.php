<?php
	session_start();
	include "validate.php";

	$option = isset($_POST['picList']) ? $_POST['picList'] : false;
   	if ($option) {
		$list = explode("/", $option);
		if ($list[1] != $_SESSION['username'])
		{
			exit;
		}

		unlink($option);
	}

	header('Location: private.php');
	exit;
?>