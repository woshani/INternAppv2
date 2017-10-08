<?php
include_once('connection.php');
$idcompany = $_POST['company'];
$select = "select a.id_student,a.id_vacancy,a.apply_date,a.application_status,v.id_company,v.position,v.description,s.name from application a inner join vacancy v on v.id_vacancy = a.id_vacancy inner join student s on s.id_student = a.id_student where v.id_company = '$idcompany';";
$resultSelect = mysqli_query($conn,$select);
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