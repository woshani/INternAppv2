<?php
include_once('connection.php');
$return_arr = array();
$sql = "SELECT batch_id,batch,sem FROM batch;";
$resultSelect = mysqli_query($conn,$sql);
if($resultSelect->num_rows > 0){
	while ($row = mysqli_fetch_array($resultSelect)){
		$array_row['id'] = $row['batch_id'];
		$array_row['batch']  = $row['batch'];
		$array_row['sem']  = $row['sem'];
		array_push($return_arr,$array_row);
		
	}
	echo json_encode($return_arr);
}else{
	echo "no data";
}
mysqli_close($conn);
?>