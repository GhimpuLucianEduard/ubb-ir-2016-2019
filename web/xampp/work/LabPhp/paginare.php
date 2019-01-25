<?php
        include "dbconfig.php";

        // create connection
        $Connection = new mysqli($servername, $username, $password, $dbname);

        // results per page
        $resultsPerPage = 5;

        // get number of rows in db
        $cmd = 'SELECT COUNT(*) FROM Produse';
        $stmt = $Connection->prepare($cmd);
        $stmt->execute();
        $stmt->bind_result($nrRows);
        $stmt->close();

        if (!isset($_GET['page'])) {
            $page = 1;
        } else {
            $page = $_GET['page'];
        }

        if (!isset($_GET['nr'])) {
            $resultsPerPage = 5;
        } else {
            $resultsPerPage = $_GET['nr'];
        }

        $firstResult = ($page-1)*$resultsPerPage;
        
        
        // get data rom db
        $cmd = 'SELECT * 
                FROM produse
                LIMIT ' . $firstResult . ',' . $resultsPerPage;

        $stmt = $Connection->prepare($cmd);
        $stmt->execute();
        $stmt->bind_result($col1,$col2,$col3,$col4);
        

        while ( $stmt->fetch() ) {
            echo $col1 . $col2 . $col3 . $col4 . '<br>';
        }
        //$stmt->close();
        
        echo '<form action="paginare.php" id="paginare">';
        echo '  <select name="nr">
                    <option value="1">1</option>
                    <option value="5">5</option>
                    <option value="10">10</option>
                    <option value="15">15</option>
                </select> ';
        echo '<input type="submit">';
        echo '</form>';

        $prevPage = $page - 1;
        $nextPage = $page + 1;
        if ($prevPage == 0) {
            $prevPage = 1;
        }
        echo '<a href="paginare.php?nr=' . $resultsPerPage . '&page=' . $prevPage . '">  PREV </a> ';
        echo '<a href="paginare.php?&nr=' . $resultsPerPage . '&page=' . $nextPage . '">  NEXT </a> ';
        $Connection->close();
?>