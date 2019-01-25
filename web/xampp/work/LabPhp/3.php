<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Page Title</title>
</head>
<body>

    <form action="http://localhost:8090/work/LabPhp/3b.php" method="post">

        <?php
            include "dbconfig.php";
            $Connection = new mysqli($servername, $username, $password, $dbname);

            $cmd = 'select * from studenti';
            $stmt = $Connection->prepare($cmd);
            $stmt->execute();
            $stmt->bind_result($col1, $col2);
            

            echo '<select name="student">';
            
            while ( $stmt->fetch() ) {
                echo '<option value="' . $col1 . '">' . $col2 . '.</option>';
            }

            echo '</select>';

            $cmd = 'select * from materii';
            $stmt = $Connection->prepare($cmd);
            $stmt->execute();
            $stmt->bind_result($col1, $col2);
            
            echo '<select name="materie">';
            
            while ( $stmt->fetch() ) {
                echo '<option value="' . $col1 . '">' . $col2 . '.</option>';
            }
            
            echo '</select>';
        ?>
        <select name="nota">
            <option value="1"> 1 </option>
            <option value="2"> 2 </option>
            <option value="3"> 3 </option>
            <option value="4"> 4 </option>
            <option value="5"> 5 </option>
            <option value="6"> 6 </option>
            <option value="7"> 7 </option>
            <option value="8"> 8 </option>
            <option value="9"> 9 </option>
            <option value="10"> 10   </option>
        <input type="submit">
    </form>
</body>
</html>
