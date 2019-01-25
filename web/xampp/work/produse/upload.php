<?php
    function upload() {

        $target_dir = "poze/";
    
        $target_file = $target_dir . basename($_FILES["file"]["name"]);
        $uploadOk = 0;
        $imageFileType = strtolower(pathinfo($target_file,PATHINFO_EXTENSION));
        // Check if image file is a actual image or fake image
        if(isset($_POST["submit"])) {
            $check = getimagesize($_FILES["file"]["tmp_name"]);
            if($check !== false) {
                $uploadOk = 1;
            } else {
                $uploadOk = 0;
            }
        }

        // Check if $uploadOk is set to 0 by an error
        if ($uploadOk == 0) {
            return $uploadOk;
        // if everything is ok, try to upload file
        } else {
            if (move_uploaded_file($_FILES["file"]["tmp_name"], $target_file)) {
                return 1;
            } else {
                return 0;
            }
        }
        
        return uploadOk;

    }
?>