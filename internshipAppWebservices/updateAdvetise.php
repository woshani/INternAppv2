<?php
include_once('connection.php');

$position = $_POST['position'];
$numberVacancy = $_POST['number'];
$desc = $_POST['description'];
$vacan = $_POST['IdVacan'];
$sqlSequence = "UPDATE vacancy set position='".$position."',no_of_vacancy='".$numberVacancy."',description='".$desc."' where id_vacancy='".$vacan."'";
$resultSeq = mysqli_query($conn,$sqlSequence);
if($resultSeq){
    echo "success";
}else{
    echo "fail";
}
mysqli_close($conn);

?>