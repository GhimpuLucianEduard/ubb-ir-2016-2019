<?php
	$con = new mysqli('localhost', 'root', '', 'diverseweb');

	$sql = $con->query("SELECT name, message FROM comments WHERE isApproved = 1");
	if ($sql->num_rows > 0) {
		while($row = $sql->fetch_assoc()) {
			echo $row["name"]. " said: " . $row["message"] . "<br>";
		}
	}
	
	$msg = "";

	if (isset($_POST['submit'])) {
		$con = new mysqli('localhost', 'root', '', 'diverseweb');

		$name = $con->real_escape_string($_POST['name']);
		$message = $con->real_escape_string($_POST['message']);

		if ($name == "" || $message == "")
			$msg = "Please check your inputs!";
		else {
			$sql = $con->query("INSERT INTO comments(name, message, isApproved) VALUES('$name', '$message', 0)");
			echo "<meta http-equiv='refresh' content='0'>";
		}
	}


?>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Log In</title>
</head>
<body>

				<?php if ($msg != "") echo $msg . "<br><br>" ?>
				<form method="post" action="problema6.php">
					<input class="form" name="name" type="name" placeholder="name..."><br>
					<input class="form" name="message" type="message" placeholder="max 250 message..."><br>
					<input class="btn" type="submit" name="submit" value="post">
				</form>

</body>
</html>