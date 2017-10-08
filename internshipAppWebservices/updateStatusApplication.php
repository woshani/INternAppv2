<?php
include_once('connection.php');
$idapplication = $_POST['appID'];
$idstudent = $_POST['studID'];
$STATUS = $_POST['stat'];

$updatesql ="UPDATE application set APPLICATION_STATUS = '".$STATUS."' WHERE id_student ='".$idstudent."' AND id_vacancy='".$idapplication."';";
$resultUpdate = mysqli_query($conn,$updatesql);
if($resultUpdate){
    echo "success";
}else{
    echo "failed";
}
mysqli_close($conn);
?>