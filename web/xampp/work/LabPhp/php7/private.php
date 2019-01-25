<?php
	session_start();
	include "validate.php";
	include "db.php";
?>
	<!DOCTYPE html>
	<html>
	<body>
	<?php
		echo 'Logged in as: ' . $_SESSION['username'];
	?>
	<form action="upload.php" method="post" enctype="multipart/form-data">
	    Select image to upload:
	    <input type="file" name="fileToUpload" id="fileToUpload">
	    <input type="submit" value="Upload Image" name="submit">
	</form>

	<form method="post" action="public_profile.php">
		<?php
			$sql = "select name from users"; //where name != '$_SESSION['username']"
			$result = mysqli_query($link, $sql);
		
			if ($result == false) {
				echo '<a href="private.php">Error: cannot execute query</a>';
				exit;
			}
		
			$num_rows = mysqli_num_rows($result);

			echo '<select name="usersList" id="usersList">';
			while($row = mysqli_fetch_array($result))
		    {
				echo '<option value="'. $row['name'] . '">'. $row['name'] . '</option>';;
		    } 
		    echo '</select>';
		
		mysqli_close($link);
		?>
		<input type="submit" value="See profile"/>
	</form>

	<form method="post" action="delete_pic.php">
	<?php
		$dirname = "uploads/" . $_SESSION['username'] . "/";
		$images = glob($dirname."*");

		foreach($images as $image) 
		{
			echo 'Image name: ' . explode("/", $image)[2] . '<br>';
		    echo '<img src="'. $image .'" /><br />';
		}
		echo '<select name="picList" id="picList">';

		foreach($images as $image)
	    {
			echo '<option value="'. $image . '">'. explode("/", $image)[2] . '</option>';
	    } 
	    echo '</select>';
	?>
	<input type="submit" value="Delete pic"/>
	</form>

	</body>

	<p><a href="exit.php">Exit</a></p>
</html>