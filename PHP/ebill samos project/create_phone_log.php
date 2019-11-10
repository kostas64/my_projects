<?php
include 'init.php';

    save_result();
    get_result();
    
    function save_result(){
        include 'init.php';
      $sql = "SELECT mobile,community FROM real_estate,real_estate_info,users_info,users_choice,users WHERE users_info.qprime = users_choice.qprime and users_choice.sms = 1 and users.qprime = real_estate.qprime and real_estate_info.hydro = real_estate.hydro";
    $result = mysqli_query($con,$sql);
    
    $previous = 0;
    $file = fopen('log/phone_log.txt','w');
    while($row = mysqli_fetch_assoc($result)){
        
        if ($previous != $row['mobile'] && strlen($row['community']) == strlen($_POST['commun'])+1){
            $previous = $row['mobile'];
            fwrite($file,$row['mobile']);
            fwrite($file,";");
        }
     
    }
    fclose($file);  
    }
    
    function get_result(){
        $file = 'log/phone_log.txt';
        
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