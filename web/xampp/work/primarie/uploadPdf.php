<?php

    function uploadPdf() {

        $targetfolder = "uploads/";
        $userfolder = $_POST["nume"];

        if (!file_exists($targetfolder . $userfolder)) {
            mkdir($targetfolder . $userfolder, 0777, true);
        }

        $userfolder = $userfolder . "/";

        $targetfile = $targetfolder . $userfolder . basename($_FILES["file"]["name"]);
    
        $filetype=$_FILES['file']['type'];
    
        if ($filetype=="application/pdf") {
    
            if(move_uploaded_file($_FILES['file']['tmp_name'], $targetfile)) {
                return true;
            }
            else {
                return false;;
            }
        }
        else {
            return false;
        }
    }
    
?>