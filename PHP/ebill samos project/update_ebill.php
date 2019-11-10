<?php

require 'init.php';
session_start();

$estate = filter_input(INPUT_POST, 'estate2', FILTER_SANITIZE_SPECIAL_CHARS);
$qprime = $_SESSION['qprime'];

if (isset($_POST['chkEBillOnly'])){
    
    if($_POST['chkEBillOnly'] == 'ebill'){
        $ebill = 1;
    }
}else
    $ebill=0;

$sql = "UPDATE `users_choice` SET ebill = $ebill WHERE hydro = '$estate' and qprime = $qprime;";  
$result = mysqli_query($con, $sql);


if (mysqli_affected_rows($con) == 0 or mysqli_affected_rows($con) == -1) { 
    
    $sql = "INSERT INTO `users_choice`( `qprime`, `hydro`, `ebill`) VALUES ($qprime,'$estate',$ebill)";
    mysqli_query($con, $sql);
    
}

header('Location: myaccount.php');