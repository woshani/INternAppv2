<?php
include_once('connection.php');

$id_vacan = $_POST['id_vacancy'];

$sql = "select id_vacancy,id_company,position,no_of_vacancy,description,vcncy_ad_date,status from vacancy where id_vacancy='".$id_vacan."';";

$resultSelect = mysqli_query($conn,$sql);
if($resultSelect->num_rows > 0){
	while ($row = mysqli_fetch_array($resultSelect)){
		$id = $row['id_vacancy'];
		$company =  $row['id_company'];
		$position =  $row['position'];
		$noVacan = $row['no_of_vacancy'];
		$desc = $row['description'];
		$date = $row['vcncy_ad_date'];
		$status = $row['status'];
		echo ($id."|".$company."|".$position."|".$noVacan."|".$desc."|".$date."|".$status); 

	}
}
mysqli_close($conn);
?>