<?php

require 'init.php';

session_start();
$hydro = $_GET['hydro'];
$qprime = $_SESSION['qprime'];

$sql = "SELECT ebill FROM users_choice WHERE qprime = '$qprime' and hydro = '$hydro'";
$result = mysqli_query($con, $sql);
$row = mysqli_fetch_assoc($result);

if ($row == null) {
     echo '{"ebill":false}';
} else {
    echo '{"ebill":' . $row["ebill"] . '}';
}