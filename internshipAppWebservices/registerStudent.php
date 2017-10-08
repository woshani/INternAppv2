<?php
include_once('connection.php');

$idStud = $_POST['id_stud'];

$sql = "UPDATE student SET status ='1' WHERE id_student ='".$idStud."';";
$resultUpdate = mysqli_query($conn,$sql);
if($resultUpdate){
    echo "success";
}else{
    echo "failed";
}
mysqli_close($conn);
?>