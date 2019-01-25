<?php
   	session_start();
	include "validate.php";

   $option = isset($_POST['usersList']) ? $_POST['usersList'] : false;
   if ($option) {
   		echo 'Public photos for: ' . $option . '<br>';
	  	$dirname = "uploads/" . $option . "/";
		$images = glob($dirname."*");
		foreach($images as $image) {
		    echo '<img src="'.$image.'" /><br />';
		}
   } else {
     echo "No user found";
     exit; 
   }