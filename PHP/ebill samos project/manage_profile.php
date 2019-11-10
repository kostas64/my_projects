<?php

session_start();
include 'init.php';
$username = $_SESSION['username'];

$sql = "SELECT qprime FROM users WHERE username = '$username';";
$result = mysqli_query($con, $sql);
$row = $result->fetch_assoc();
$qprime = $row['qprime'];

if ($_SERVER['REQUEST_METHOD'] === 'POST') {


    if ( isset($_POST['password']) && isset($_POST['retype']) && (empty($_POST['password']) == FALSE) && (empty($_POST['retype']) == FALSE) && ($_POST['retype'] == $_POST['password'])) {

        $password = $_POST['password'];
        $password = md5($password);
        $sql = "UPDATE users SET password ='$password' WHERE qprime = '$qprime' ;";
        if (mysqli_query($con, $sql)) {
          header('Refresh: 1; URL=login.php?success=pass_change');
        }
    } elseif (isset($_POST['email']) && (empty($_POST['email']) == FALSE)) {
        $mail = $_POST['email'];
        $sql = "UPDATE users_info SET email = '$mail' WHERE qprime = '$qprime';";
        if (mysqli_query($con, $sql)) {
            header('Refresh: 1; URL=login.php?success=email_change');
        }
    } elseif (isset($_POST['phone']) && (empty($_POST['phone']) == FALSE)) {
        $mobile = $_POST['phone'];
        $sql = "UPDATE users_info SET mobile = '$mobile' WHERE qprime = '$qprime';";
        if (mysqli_query($con, $sql)) {
            header('Refresh: 1; URL=login.php?success=mobile_change');
        }
    } elseif($_POST['password'] != $_POST['retype']){
        header('Refresh: 1; URL=login.php?error=pass_change_fail');
    } 
}
