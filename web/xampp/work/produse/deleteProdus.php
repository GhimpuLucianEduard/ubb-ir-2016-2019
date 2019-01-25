<?php
 
    session_start();
    include "dbconfig.php";

    $Connection = new mysqli($servername, $username, $password, $dbname);

    $id = $_GET['id'];

    $cmd = "DELETE 
            from produse2
            where id = ?";
    $stmt = $Connection->prepare($cmd);
    $stmt->bind_param("i", $id);
    $stmt->execute();


?>