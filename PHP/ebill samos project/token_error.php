<?php
if (isset($_GET["token"])) {
    $token = $_GET["token"];

    require "init.php";
    $sql = "SELECT qprime FROM mail_verify WHERE token = '$token';";
    $result = mysqli_query($con, $sql);
    if ($result) {
        $row = $result->fetch_assoc();
        $qprime = $row["qprime"];
        $sql = "UPDATE mail_verify SET token = '' , active = 1 WHERE qprime = '$qprime'";
        mysqli_query($con, $sql);
    }
} elseif (isset($_GET["error"]))
    $error = $_GET["error"];
?>