<?php

    session_start();
    include "dbconfig.php";

    $email = $_POST['username'];
    $pass = $_POST['password'];
    
    $Connection = new mysqli($servername, $username, $password, $dbname);


    $cmd = "SELECT id
            FROM users 
            WHERE email = ? AND password = ?";
    echo $cmd;
    $stmt = $Connection->prepare($cmd);
    $stmt->bind_param("ss", $email, $pass);
    $stmt->execute();  
    $stmt->bind_result($res); 
    $stmt->fetch();

    if ($res!=0) {
        $_SESSION["username"] = $email;
        header("Location: produse.php");
    } else {
         echo '<a href="login.php">Invalid credentials!</a>';
	    exit;
    }

?>