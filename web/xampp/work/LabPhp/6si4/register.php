<?php
	$msg = "";

	if (isset($_POST['submit'])) {
		$con = new mysqli('localhost', 'root', '', 'diverseweb');

		$name = $con->real_escape_string($_POST['name']);
		$email = $con->real_escape_string($_POST['email']);
		$password = $con->real_escape_string($_POST['password']);
		$cPassword = $con->real_escape_string($_POST['cPassword']);

		if ($name == "" || $email == "" || $password != $cPassword)
			$msg = "Please check your inputs!";
		else {
			$sql = $con->query("SELECT id FROM users WHERE email='$email'");
				$token = 'qwertzuiopasdfghjklyxcvbnmQWERTZUIOPASDFGHJKLYXCVBNM0123456789!$/()*';
				$token = str_shuffle($token);
				$token = substr($token, 0, 10);

				$hashedPassword = password_hash($password, PASSWORD_BCRYPT);

				$con->query("INSERT INTO users (name,email,password,isEmailConfirmed,token)
					VALUES ('$name', '$email', '$hashedPassword', '0', '$token');
				");


                $msg2 = "
                    Please click on the link below:<br><br>
                    
                    http://localhost:8090/work/LabPhp/6si4/confirm.php?email=" . $email . "&token=" . $token;

				$msg2 = wordwrap($msg2,70);
				$headers =  'MIME-Version: 1.0' . "\r\n"; 
				$headers .= 'From: Your name <info@address.com>' . "\r\n";
				$headers .= 'Content-type: text/html; charset=iso-8859-1' . "\r\n"; 
				
				
				mail($email,"lawl",$msg2, $headers);
		}
	}
?>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Register</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
</head>
<body>
	<div class="container" style="margin-top: 100px;">
		<div class="row justify-content-center">
			<div class="col-md-6 col-md-offset-3" align="center">


				<?php if ($msg != "") echo $msg . "<br><br>" ?>

				<form method="post" action="register.php">
					<input class="form-control" name="name" placeholder="Name..."><br>
					<input class="form-control" name="email" type="email" placeholder="Email..."><br>
					<input class="form-control" name="password" type="password" placeholder="Password..."><br>
					<input class="form-control" name="cPassword" type="password" placeholder="Confirm Password..."><br>
					<input class="btn btn-primary" type="submit" name="submit" value="Register">
				</form>

			</div>
		</div>
	</div>
</body>
</html>