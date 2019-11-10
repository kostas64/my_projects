<?php

include 'init.php';
include 'send_mail.php';
$qprime = filter_input(INPUT_POST, 'qprime', FILTER_SANITIZE_NUMBER_INT);
$name = filter_input(INPUT_POST, 'name', FILTER_SANITIZE_SPECIAL_CHARS);
$surname = filter_input(INPUT_POST, 'surname', FILTER_SANITIZE_SPECIAL_CHARS);
$email = filter_input(INPUT_POST, 'email', FILTER_SANITIZE_EMAIL);
$mobile = filter_input(INPUT_POST, 'mobile', FILTER_SANITIZE_SPECIAL_CHARS);
$username = filter_input(INPUT_POST, 'username', FILTER_SANITIZE_SPECIAL_CHARS);
$password = filter_input(INPUT_POST, 'password', FILTER_SANITIZE_SPECIAL_CHARS);
$password = md5($password);


$sql = "SELECT username FROM users, users_info WHERE users.qprime = users_info.qprime AND email = '$email';";
$result = mysqli_query($con, $sql);
$sql2 = "SELECT qprime FROM real_estate WHERE '$qprime' = qprime";
$result2 = mysqli_query($con,$sql2);
$sql3 = "SELECT qprime FROM `users` WHERE qprime = '$qprime' ";
$result3 = mysqli_query($con,$sql3);
if (mysqli_num_rows($result) == 0 and mysqli_num_rows($result2) > 0 and mysqli_num_rows($result3) == 0) {
    $sql = "INSERT INTO `users` (qprime,username,password) VALUES ('$qprime','$username','$password');";

    if (mysqli_query($con, $sql)) {
        $sql = "INSERT INTO `users_info` (qprime,name,surname,mobile,email) values ('$qprime','$name','$surname','$mobile','$email');";
        mysqli_query($con, $sql);
		
		$sql = "SELECT qprime FROM password_reset WHERE qprime = '$qprime'";
		$result = mysqli_query($con, $sql);
		if (mysqli_num_rows($result)>0) {
			$sql = "UPDATE `password_reset` SET token = NULL WHERE qprime = '$qprime'";
			mysqli_query($con, $sql);
		}else{
        $sql = "INSERT INTO `password_reset` (qprime) values('$qprime');";
        mysqli_query($con, $sql);
        }
        
        $token = md5(uniqid(rand(), true));
		$sql = "SELECT qprime FROM mail_verify WHERE qprime = '$qprime'";
		$result = mysqli_query($con, $sql);
		if (mysqli_num_rows($result)>0) {
			$sql = "UPDATE `mail_verify` SET token = '$token' WHERE qprime = '$qprime'";
			mysqli_query($con, $sql);
		}else{
        $sql = "INSERT INTO `mail_verify` (qprime,token) values ('$qprime','$token')";
		$result3 = mysqli_query($con, $sql);
		}
        if ($result3) {
           
            if (send_acc_verification($email, $token)) {
                header('Location: success_reg.html');
                die();
            }
        }
    } else {

        echo("Error description: " . mysqli_error($con));
    }
} else if(mysqli_num_rows($result) > 0){
     header("Location: register.php?error=email_exist");
}else if(mysqli_num_rows($result2) == 0){
    header("Location: register.php?error=no_qprime");
}else if(mysqli_num_rows($result3) > 0){
    header("Location: register.php?error=user_exist");
}

die();
?>