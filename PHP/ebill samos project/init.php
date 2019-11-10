<?php

    $host = "localhost";
    $user = "ebillsam_user";
    $password = "ZT1GN2vB}tg9";
    $db = "ebillsam_nera"; 

    $con = mysqli_connect($host,$user,$password,$db);
    
    if (!$con)
    {
    	die ("Error in connection" . mysqli_connect_error());
    }else{
        mysqli_set_charset($con,"utf8");
    }
?>