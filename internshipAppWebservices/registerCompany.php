<?php
include_once('connection.php');

$username = $_POST['username'];
$password = $_POST['password'];
$name = $_POST['name'];
$address = $_POST['address'];
$postcode = $_POST['postcode'];
$location = $_POST['location'];
$state = $_POST['state'];
$tel = $_POST['tel'];
$email = $_POST['email'];
$resultCounter = 0;

$sqlSequence = "SELECT lastSeqNumber FROM sequencenumber WHERE module = 'company'";
$resultSeq = mysqli_query($conn,$sqlSequence);

if($resultSeq->num_rows > 0){
        while ($row = mysqli_fetch_array($resultSeq)){ 
            $rseq =  intval($row['lastSeqNumber']) + 1;
        }
        $companyID = "COM".date("Y").$rseq;

        $sqlInsert = "INSERT INTO company (ID_COMPANY,COMP_NAME,COMP_ADDRSS,COMP_POSTCODE,COMP_LOCATION,COMP_STATE,COMP_TEL,COMP_EMAIL) VALUES('$companyID','$name','$address','$postcode','$location','$state','$tel','$email');INSERT INTO users(usersID,username,password,usersType)VALUES('$companyID','$username','$password','company');UPDATE sequencenumber SET lastSeqNumber='$rseq' WHERE module = 'company';";
        $sqlInsertUser = "";
        $insert = mysqli_multi_query($conn,$sqlInsert);
        if($insert){
             echo "success";
        }else{
            echo "fail";
        }
}
mysqli_close($conn);
?>