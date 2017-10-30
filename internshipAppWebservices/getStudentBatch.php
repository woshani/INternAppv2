<?php
include_once('connection.php');

$idBatch = $_POST['id'];

$sql = "SELECT id_student,ic_no,name,email,no_tel,address,course,status,batch_id FROM student WHERE batch_id = '".$idBatch."' and status = 0;";

$resultSelect = mysqli_query($conn,$sql);
if($resultSelect->num_rows > 0){
    while($row = mysqli_fetch_assoc($resultSelect)){
    $data[]=$row;
    }
    echo json_encode($data);
}else{
    echo "no data";
}

mysqli_close($conn);
?>