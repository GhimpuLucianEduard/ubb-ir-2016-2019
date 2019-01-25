<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Page Title</title>
</head>
<body>
<form action="result.php" METHOD="POST">

      <?php

            include "dbconfig.php";

            $Connection = new mysqli($servername, $username, $password, $dbname);

            $cmd = "SELECT * from poze order by voturi desc";
            $stmt = $Connection->prepare($cmd);
            $stmt->execute();
            $stmt->bind_result($col1, $col2, $col3);

            echo '<ul>';

            while ( $stmt->fetch() ) {
                echo '<li>' . $col1 . '  Numar voturi:' . $col3 .  '</li> <br>';
                echo '<image src="' . $col2 . '"> <br>';
            }
            
            echo '</ul>';
            
            $cmd = "SELECT id from poze";
            $stmt = $Connection->prepare($cmd);
            $stmt->execute();
            $stmt->bind_result($col1);
            
            echo 'Alege numarul pozei pe care doresti sa o votezi:';
            echo '<br>';
            echo '<select name="poza">';

            
            while ( $stmt->fetch() ) {
                echo '<option value="' . $col1 . '">' . $col1 .'</option>'; 
            }

            echo '</select> <br>';

      ?>

      <p><img src="imagebuilder.php" border="1">  </p>
      <p>Please enter the code shown above and click Submit.<br>
        <input MAXLENGTH=8 SIZE=8 name="userstring" type="text" value="">
        <br>
        <input type="submit" value="Submit">
      </p>
</form>
</body>
</html>