<?php
include_once('connection.php');
$studID = $_POST['studentID'];
$vacanID = $_POST['vacanID'];

$select = "select * from application where id_student ='$studID' and id_vacancy='$vacanID';";
$resultSelect = mysqli_query($conn,$select);
if($resultSelect->num_rows <= 0){
    $insert = "INSERT into application(id_student,id_vacancy,apply_date,application_status) values('$studID','$vacanID',now(),'NEW APPLICATION');";
    $resultIns = mysqli_query($conn,$insert);
    if($resultIns){
        echo "inserted";

    }else{
        echo "fail";
    }

}else{
    echo "already";
}
mysqli_close($conn);
?>