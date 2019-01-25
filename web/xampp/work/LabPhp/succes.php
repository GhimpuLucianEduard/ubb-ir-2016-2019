<?php

    echo "Votul a fost registrat!";

    include "dbconfig.php";

    $Connection = new mysqli($servername, $username, $password, $dbname);

    $poza = $_GET['poza'];

    $cmd = "update poze set voturi = voturi + 1 where id = ?";
    $stmt = $Connection->prepare($cmd);
    $stmt->bind_param("i", $poza);
    $stmt->execute();
?>