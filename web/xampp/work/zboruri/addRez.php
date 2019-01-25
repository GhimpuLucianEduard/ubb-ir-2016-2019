<?php

    $zbor = $_POST["zbor"];
    $data = $_POST["data"];
    $persoane = $_POST["persoane"];


    //echo $zbor;
    //echo "\n";
    //echo $data;
    //echo "\n";

    $cine = "";
    $index = 1;
    foreach ($persoane as $item) {
        $cine = $cine . " " . $item;
        if ($index % 2 == 0) 
        {
            $cine = $cine . ",";
        }
        $index = $index +1;
        //echo $item;
        //echo "\n";

    }
    //echo "lista:" . $cine;

    include "dbconfig.php";

    $Connection = new mysqli($servername, $username, $password, $dbname);

    $cmd = "INSERT into rezervari(dataPlecare,persoane,zbor) values (?, ?, ?)";
    $stmt = $Connection->prepare($cmd);
    $stmt->bind_param("ssi", $data, $cine, $zbor);
    $stmt->execute();


    $last_id = $Connection->insert_id;

    echo "Rezervare cu nuamrul " . $last_id . " a fost inregistrata cu succes!";

?>