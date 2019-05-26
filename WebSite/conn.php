<?php

$server = "localhost";
$user = "root";
$pas = "Abcd1234!@#$";
$db = "ComNetIOT";
$conn = mysqli_connect($server, $user, $pas, $db);

if($conn){
	//echo "connection established";
}
?>
