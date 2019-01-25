<?php
 
    session_start();
    include "dbconfig.php";
    include "upload.php";

    //id	nume	descriere	producator	pret	cantitate	fileToUpload
    // try to upload
    if (upload()) {
        
        $Connection = new mysqli($servername, $username, $password, $dbname);

        $nume = $_POST['nume'];
        $descriere = $_POST['descriere'];
        $producator = $_POST['producator'];
        $pret = $_POST['pret'];
        $cantitate = $_POST['cantitate'];
        $poza = basename($_FILES["file"]["name"]);

        $cmd = "INSERT into produse2(nume,descriere,producator,pret,cantitate,poza) values (?, ?, ?, ?, ?,?)";
        $stmt = $Connection->prepare($cmd);
        $stmt->bind_param("sssiis", $nume, $descriere, $producator, $pret, $cantitate, $poza);
        $stmt->execute();


        if ($stmt) {
            echo "Produsul a fost adaugat cu succes!";
        } else {
            echo "A aparut o eroare, reincercati!";
            sleep(1000);
            header("Location: produse.php");
        }

    }
    else {
        echo "Verificati ca fisierul sa fie poza!";
    }

?>