<?php
	$msg = "";

	if (isset($_POST['submit'])) {
		$con = new mysqli('localhost', 'root', '', 'diverseweb');

		$name = $con->real_escape_string($_POST['name']);
		$message = $con->real_escape_string($_POST['message']);

		if ($email == "" || $password == "")
			$msg = "Please check your inputs!";
		else {
			$sql = $con->query("SELECT id, password, isEmailConfirmed FROM users WHERE email='$email'");
			if ($sql->num_rows > 0) {
                $data = $sql->fetch_array();
                if (password_verify($password, $data['password'])) {
                    if ($data['isEmailConfirmed'] == 0)
	                    $msg = "Please verify your email!";
                    else {
	                    $msg = "You have been logged in";
                    }
                } else
	                $msg = "Please check your inputs!";
			} else {
				$msg = "Please check your inputs!";
			}
		}
	}
?>