<?php
include_once('connection.php');
$idCompany = $_POST['ID'];
//$idCompany="COM0000002";

$sqlGetData = "SELECT v.id_vacancy,v.id_company,v.position,v.no_of_vacancy,v.description,v.vcncy_ad_date,v.status,c.comp_name,c.comp_addrss,c.comp_postcode,c.comp_location,c.comp_state,c.comp_tel,c.comp_email FROM vacancy v
join company c on c.id_company = v.id_company";

if($idCompany !== "all"){
    $sqlGet = $sqlGetData." WHERE v.id_company='$idCompany';";
}else{
    $sqlGet = $sqlGetData.";";
}

$result = mysqli_query($conn,$sqlGet);
if($result->num_rows > 0){

    while($row = mysqli_fetch_assoc($result)){
    $data[]=$row;
    }
    echo json_encode($data);
}else{
    echo "no data";
}
mysqli_close($conn);
?>