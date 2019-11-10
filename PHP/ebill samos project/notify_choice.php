<?php

require 'init.php';

session_start();

$hydro = $_GET['hydro'];
$sql = "SELECT email,sms FROM users_choice WHERE qprime = '" . $_SESSION["qprime"] . "' and hydro = '$hydro';";
$result = mysqli_query($con, $sql);
$row = mysqli_fetch_assoc($result);

if ($row == null) {
     echo '{"sms":false,"email":false}';
} else {
    echo '{"sms":' . $row["sms"] . ',"email":' . $row["email"] . '}';
  
}