<?php 

require "init.php";

$pass = filter_input(INPUT_POST, 'password', FILTER_SANITIZE_SPECIAL_CHARS);  //'$ka!_3FU-W'; 

$sql = "SELECT pass FROM admin WHERE id = 1";
$result = mysqli_query($con,$sql);
$row = mysqli_fetch_assoc($result);


$hashCheckdB = password_hash($pass,PASSWORD_BCRYPT);

if(password_verify($hashCheckdB,$row['pass']));
   header("Location: managead.html");


?>