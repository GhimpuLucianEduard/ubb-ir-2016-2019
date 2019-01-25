<html>
	<body>
		<?php
			
			$Plecare = $_GET["plecare"];
			$Destinatie = $_GET["destinatie"];

			//Init params
			$servername = "localhost";
			$username = "root";
			$password = "";
			$dbname = "DiverseWeb";

			//Create Connection
			$Connection = new mysqli($servername, $username, $password, $dbname);

			//Check Connection
			if($Connection->connect_error) {
				die("Connection failes: " . $Connection->connect_error);
			}
			
			$cmd = "SELECT *
			FROM trenuri
			where plecare = ? and sosire = ?";
			$stmt = $Connection->prepare($cmd);
			$stmt->bind_param("ss", $Plecare, $Destinatie);
			$stmt->execute();
			$stmt->bind_result($col1,$col2,$col3,$col4,$col5,$col6);

			//Build select
			echo "<table>";
			echo '<tr>';
			echo '  <th> Numar </th>';
			echo '  <th> Tip </th>';
			echo '  <th> Plecare </th>';
			echo '  <th> Destinatie </th>';
			echo '  <th> Data Plecare </th>';
			echo '  <th> Data Sosire </th>';
			echo '</tr>'; 

			//Build options
			while ($stmt->fetch()) {
					echo '<tr>';
					echo '  <td> ' . $col1 . ' </td>';
					echo '  <td> ' . $col2 . ' </td>';
					echo '  <td> ' . $col3 . ' </td>';
					echo '  <td> ' . $col4 . ' </td>';
					echo '  <td> ' . $col5 . ' </td>';
					echo '  <td> ' . $col6 . ' </td>';
					echo '</tr>'; 
			}
			
			if (isset($_GET["schimb"])) {
				$cmd = "SELECT p.plecare, p.sosire, d.sosire, p.oraPlecare, p.oraSosire, d.oraPlecare, d.oraSosire
						FROM trenuri AS p
						JOIN trenuri AS d
						ON p.sosire = d.plecare
						WHERE p.plecare = ? and d.sosire = ?
						Group by p.sosire";
				$stmt = $Connection->prepare($cmd);
				$stmt->bind_param("ss", $Plecare, $Destinatie);
				$stmt->execute();
				$stmt->bind_result($col1,$col2,$col3,$col4,$col5,$col6,$col7);

				//Build select
				echo "<table>";
				echo '<tr>';
				echo '  <th> Plecare </th>';
				echo '  <th> Schimb </th>';
				echo '  <th> Sosire </th>';
				echo '  <th> Ora plecare </th>';
				echo '  <th> Ora sosire la schimb </th>';
				echo '  <th> Ora plecare de la schimb </th>';
				echo '  <th> Ora sosire la destinatie </th>';
				echo '</tr>'; 

				//Build options
				while ($stmt->fetch()) {
						echo '<tr>';
						echo '  <td> ' . $col1 . ' </td>';
						echo '  <td> ' . $col2 . ' </td>';
						echo '  <td> ' . $col3 . ' </td>';
						echo '  <td> ' . $col4 . ' </td>';
						echo '  <td> ' . $col5 . ' </td>';
						echo '  <td> ' . $col6 . ' </td>';
						echo '  <td> ' . $col7 . ' </td>';
						echo '</tr>'; 
				}
			}


			$stmt->close();
			//$Connection.close();
		?>
	</body>
</html>

