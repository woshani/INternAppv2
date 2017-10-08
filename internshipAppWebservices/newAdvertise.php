<?php
include_once('connection.php');

$position = $_POST['position'];
$numberVacancy = $_POST['number'];
$desc = $_POST['description'];
$idCompany = $_POST['IdCompany'];
$sqlSequence = "SELECT lastSeqNumber FROM sequencenumber WHERE module = 'vacancy_number'";
$resultSeq = mysqli_query($conn,$sqlSequence);

if($resultSeq->num_rows > 0){
        while ($row = mysqli_fetch_array($resultSeq)){ 
            $rseq =  intval($row['lastSeqNumber']) + 1;
        }
        $vacancyID = "VAC".date("Y").$rseq;

        $sqlInsert = "INSERT INTO vacancy (id_vacancy,id_company,position,no_of_vacancy,description,vcncy_ad_date,status) VALUES('$vacancyID','$idCompany','$position','$numberVacancy','$desc',now(),'available');";
        $insert = mysqli_query($conn,$sqlInsert);
        if($insert){
            $sqlUpdate = "UPDATE sequencenumber SET lastSeqNumber='$rseq' WHERE module = 'vacancy_number';";
            $update=mysqli_query($conn,$sqlUpdate);
            if($update){
                echo "success";
            }else{
                echo "error";
            }
        }else{
            echo "error";
        }
}
mysqli_close($conn);
?>