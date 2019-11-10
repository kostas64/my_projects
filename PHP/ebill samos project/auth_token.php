<?php

require "init.php";

$email = filter_input(INPUT_POST, 'email', FILTER_SANITIZE_SPECIAL_CHARS);

$sql = "SELECT qprime FROM users_info WHERE email='$email'";

$result = mysqli_query($con,$sql);
$count = mysqli_num_rows($result);
  
    if ($count == 1) {
		
        $token = md5(uniqid(rand(), true));
        
        // Delete any existing tokens for this user
        $row = $result->fetch_assoc();
        
        $qprime = $row["qprime"];
        
        $sql = "UPDATE password_reset SET token='$token' WHERE qprime = '$qprime'";
        $result = mysqli_query($con,$sql);

        if ( $result ) {
            
            require "send_mail.php";
            
            if(send_pass_reset($email,$token)){
                header("Location: check_mail_pass.html");
                die();
               
            }  
        }
    } 
    

?>