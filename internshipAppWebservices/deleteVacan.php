<?php
include_once('connection.php');

$id_vacan = $_POST['id_vacancy'];

$sqldel = "DELETE from vacancy where id_vacancy ='".$id_vacan."';";
$sqldel .= "DELETE from application where id_vacancy ='".$id_vacan."';";
if(mysqli_multi_query($conn,$sqldel)){
    echo "success";
}else{
    echo "fail";
}
mysqli_close($conn);
?>