<?php
    session_start();
    $mail = $_GET["email"];
    $_SESSION["email"] = $mail;
?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>View Cereri</title>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
    <script src="viewCereri.js"></script>	
</head>
<body>
    <div id="mainDiv">

    </div>
</body>
</html>