<?php
include_once('connection.php');

$idStud = $_POST['id_stud'];
$myArray = explode('|', $idStud);
$result = count($myArray);



$sql="";
for ($i = 0; $i < $result; $i++) {
    $sql .= "UPDATE student SET status ='1' WHERE id_student ='".$myArray[$i]."';INSERT INTO users(usersID,username,password,usersType)values('".$myArray[$i]."','".$myArray[$i]."','abc123','student');";
}
if(isset($sql)){
	$resultUpdate = mysqli_multi_query($conn,$sql);
	if($resultUpdate){
	    echo "success";
	}else{
	    echo "failed";
	}
	mysqli_close($conn);
}

?>