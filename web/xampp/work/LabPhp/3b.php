<?php
    include "dbconfig.php";

    $Connection = new mysqli($servername, $username, $password, $dbname);

    $nota = $_POST['nota'];
    $materie = $_POST['materie'];
    $student = $_POST['student'];
    
    

    $cmd = "INSERT into note(nota,idStudent,idMaterie) values (?, ?, ?)";
    $stmt = $Connection->prepare($cmd);
    $stmt->bind_param("iii", $nota, $student, $materie);
    $stmt->execute();

    $cmd = "select st.nume, mt.nume, nt.nota
            from note nt
            join studenti st on st.id = nt.idStudent
            join materii mt on mt.id = nt.idMaterie";
    $stmt = $Connection->prepare($cmd);
    $stmt->execute();
    $stmt->bind_result($col1,$col2,$col3);

    echo '<table>
            <th>Student</th>
            <th>Materie</th>
            <th>Nota</th>';

    while ( $stmt->fetch() ) {
        echo '<tr>';
        echo '<td>' . $col1 . '</td>';
        echo '<td>' . $col2 . '</td>';
        echo '<td>' . $col3 . '</td>';
        echo '</tr>';
    }
?>