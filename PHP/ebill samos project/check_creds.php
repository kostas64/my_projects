<?php

require "init.php";

$name = filter_input(INPUT_POST, 'username', FILTER_SANITIZE_SPECIAL_CHARS);
$password = filter_input(INPUT_POST, 'password', FILTER_SANITIZE_SPECIAL_CHARS);
$password = md5($password);

$sql = "SELECT users.qprime,username,name,surname,email,mobile FROM users,users_info,mail_verify WHERE username ='$name' and password='$password' and users.qprime = users_info.qprime and users.qprime = mail_verify.qprime and active=1";
$sql2 = "SELECT username FROM users,users_info,mail_verify WHERE username ='$name' and password='$password' and users.qprime = mail_verify.qprime and active IS NULL";
$result = mysqli_query($con, $sql);
$count = mysqli_num_rows($result);
$result2 = mysqli_query($con, $sql2);
$count2 = mysqli_num_rows($result2);
    
if ($count == 1) {
    session_start();
    $row = mysqli_fetch_assoc($result);
    $_SESSION['qprime'] = $row['qprime'];
    $_SESSION['username'] = $row['username'];
    $_SESSION['name'] = $row['name'];
    $_SESSION['surname'] = $row['surname'];
    $_SESSION['email'] = $row['email'];
    $_SESSION['mobile'] = $row['mobile'];
    header("Location: myaccount.php");
} elseif ($count2 > 0) {
    header("Location: login.php?error=failed_validation");
} else {
    
    if (isset($_POST["token"])) {
    $token = $_POST["token"];
    header("Location: login.php?error=invalid_creds&token=$token");
    }
    else{
        header("Location: login.php?error=invalid_creds");
    }
}

?>