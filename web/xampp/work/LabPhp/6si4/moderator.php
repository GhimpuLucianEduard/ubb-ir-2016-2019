<?php
  $link = mysqli_connect("localhost", "root", "", "diverseweb");

  if($link === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
  }

  $sql = "SELECT * FROM comments WHERE isApproved = 0";
  if($result = mysqli_query($link, $sql)){
    if(mysqli_num_rows($result) > 0){
        echo "<table>";
            echo "<tr>";
                echo "<th>id</th>";
                echo "<th>name</th>";
                echo "<th>message</th>";
                echo "<th>Approve</th>";
                echo "<th>Reject</th>";
            echo "</tr>";

        while($row = mysqli_fetch_array($result)){
            echo "<tr>";
                echo "<td>" . $row['id'] . "</td>";
                echo "<td>" . $row['name'] . "</td>";
                echo "<td>" . $row['message'] . "</td>";
                echo "<td><form action='' method='post'>
                          <input type='submit' name='approve_button' value='" . $row['id'] ."'/>
                          </form>
                      </td>";
                echo "<td><form action='' method='post'>
                          <input type='submit' name='reject_button' value='" . $row['id'] ."'/>
                          </form>
                      </td>";
            echo "</tr>";
        }
        echo "</table>";
        mysqli_free_result($result);
    } else{
        echo "No records matching your query were found.";
    }
} else{
    echo "ERROR: Could not able to execute $sql. " . mysqli_error($link);
}
  mysqli_close($link);
?>

<html>
  <head>
    <meta charset="UTF-8">
    <title>Moderator</title>
  </head>
  <body>
    <?php
        if (isset($_POST['approve_button'])) {
              $id = $_POST['approve_button'];
              $con = new mysqli('localhost', 'root', '', 'diverseweb');
              $con->query("UPDATE comments
                           SET isApproved = 1
                           WHERE id = '$id'");
              header('Location: moderator.php');
        }
        if (isset($_POST['reject_button'])) {
          $id = $_POST['reject_button'];
          $con = new mysqli('localhost', 'root', '', 'diverseweb');
          $con->query("DELETE FROM comments
                       WHERE id = '$id'");
          header('Location: moderator.php');
    }
    ?>

  </body>
</html>

