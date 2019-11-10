<?php
include 'init.php';

    save_result();
    get_result();
    
    function save_result(){
        include 'init.php';
      $sql = "SELECT qprime,hydro FROM `users_choice` WHERE ebill = 1";
    $result = mysqli_query($con,$sql);
    
    $previous = 0;
    $file = fopen('log/ebill_log.txt','w');
    while($row = mysqli_fetch_assoc($result)){
        
       
            fwrite($file,$row['qprime']);
            fwrite($file,' ');
            fwrite($file,$row['hydro']. "\r\n");
          //  fwrite($file,";");
        
     
    }
    fclose($file);  
    }
    
    function get_result(){
        $file = 'log/ebill_log.txt';
        
        if(!file_exists($file)){ // file does not exist
              die('file not found');
        } else {
             list($folder,$name) = explode("/", $file);
             header("Cache-Control: public");
             header("Content-Description: File Transfer");
             header("Content-Disposition: attachment; filename=$name");
             header("Content-Type: application/zip");
             header("Content-Transfer-Encoding: binary");

    // read the file from disk
    readfile($file);
}
    }
    
    
    

?>