<?php
include_once('connection.php');

$studId = $_POST['idStudent'];

$sql = "SELECT a.id_student,c.comp_name,a.application_status from application a join vacancy v on v.id_vacancy = a.id_vacancy join company c on c.id_company = v.id_company where a.id_student = '".$studId."';";

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