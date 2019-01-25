<?php

    include "uploadPdf.php";
    include "dbconfig.php";
    include "sendEmail.php";

    if (uploadPdf()) {
        
        $Connection = new mysqli($servername, $username, $password, $dbname);

        $nume = $_POST['nume'];
        $email = $_POST['email'];
        $tel = $_POST['tel'];
        
        $date = date('Y-m-d H:i:s');
        $fisier = basename($_FILES["file"]["name"]);

        $cmd = "INSERT into cereriprimarie(nume,email,tel,dataDepunere,fisier) values (?, ?, ?, ?, ?)";
        $stmt = $Connection->prepare($cmd);
        $stmt->bind_param("ssiss", $nume, $email, $tel, $date, $fisier);
        $stmt->execute();
        $last_id = $Connection->insert_id;

        $text = "Cererea cu nuamrul " . $last_id . " a fost inregistrata cu succes! Poti vedea statusul cererilor: " . "http://localhost:8090/work/primarie/confirmare.php?email=" . $email;

        SendEmail($email, "Confirmare cerere", $text);

        echo "Cererea cu nuamrul " . $last_id . " a fost inregistrata cu succes!";
    }
    else {
        echo "Verificati ca fisierul sa fie PDF!";
    }

?>