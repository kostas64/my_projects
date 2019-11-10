<?php

require 'init.php';
session_start();

$estate = filter_input(INPUT_POST, 'estate', FILTER_SANITIZE_SPECIAL_CHARS);

$qprime = $_SESSION['qprime'];


if (isset($_POST['box'])) {
    $sms = in_array('sms', $_POST['box']);
    $email = in_array('email', $_POST['box']);
} else {
    $sms = FALSE;
    $email = FALSE;
}

if (!$sms) {
    $sms = 0;
}
if (!$email) {
    $email = 0;
}

$sql = "UPDATE `users_choice` SET `sms` = $sms WHERE hydro = '$estate' AND qprime = $qprime;"
        . " UPDATE `users_choice` SET `email` = $email WHERE hydro = '$estate' AND qprime = $qprime;";
$result = mysqli_multi_query($con, $sql);

if (mysqli_affected_rows($con) == 0 or mysqli_affected_rows($con) == -1) {
   while (mysqli_next_result($con)) {;}
    $sql = "INSERT INTO `users_choice`( `qprime`, `hydro`, `sms`, `email`) VALUES ($qprime,'$estate',$sms,$email)";
   mysqli_query($con, $sql);
  

}



header('Location: myaccount.php');

