<?php

require "init.php";

$pass1 = filter_input(INPUT_POST, 'password', FILTER_SANITIZE_SPECIAL_CHARS);
$pass2 = filter_input(INPUT_POST, 'password2', FILTER_SANITIZE_SPECIAL_CHARS);
$token = filter_input(INPUT_POST, 'token', FILTER_SANITIZE_SPECIAL_CHARS);

if ($pass1 == $pass2) {
    $password = $pass1;
    $password = md5($password);
    
    $sql = "SELECT qprime FROM password_reset WHERE token='$token';";
    $result = mysqli_query($con, $sql);
    $row = $result->fetch_assoc();
    if (mysqli_num_rows($result) == 0) {
        header("Location: password_reset.php?error=token_invalid");
    }
    $qprime = $row["qprime"];
    $sql = "UPDATE users SET password = '$password' WHERE qprime = '$qprime';";
    if(mysqli_query($con, $sql))
    {
         $sql = "UPDATE password_reset SET token='' WHERE qprime='$qprime'";
         mysqli_query($con, $sql);
         header("Location: login.php?success=pass_change");
    }
   
} else {
    header("Location: password_reset.php?error=pass_missmatch");
}
