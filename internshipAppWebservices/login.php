<?php
include_once('connection.php');
    $username = $_POST['username'];
    $password = $_POST['password'];

    //  $username = "committee1";
    //  $password = "abc123";

    $query = "SELECT usersID,username,password,usersType FROM users WHERE username='$username' AND password='$password';";
    $result = mysqli_query($conn,$query);

    if($result->num_rows > 0){
        while ($row = mysqli_fetch_array($result)){ 
            $rid=$row['usersID'];
            $rusername=$row['username'];
            $rpassword=$row['password'];
            $rusertype = $row['usersType'];
        }


        if($rusertype=="company"){
            $getDetail="SELECT id_company,comp_name,comp_postcode,comp_location,comp_state,comp_tel,comp_email FROM company WHERE id_company='$rid';";
            $resultDetails = mysqli_query($conn,$getDetail);
            while($rowDetails = mysqli_fetch_assoc($resultDetails)){
                $idCompany = $rowDetails['id_company'];
                $com_name = $rowDetails['comp_name'];
                $postcode = $rowDetails['comp_postcode'];
                $location = $rowDetails['comp_location'];
                $state = $rowDetails['comp_state'];
                $tel = $rowDetails['comp_tel'];
                $email = $rowDetails['comp_email'];

            };
            echo "success|".$rid."|".$rusername."|".$rpassword."|".$rusertype."|".$idCompany."|".$com_name."|".$postcode."|".$location."|".$state."|".$tel."|".$email;
        }else if($rusertype=="student"){
            $getDetail="SELECT id_student,ic_no,name,email,no_tel,address,course,status,batch_id FROM student WHERE id_student='$rid';";
            $resultDetails = mysqli_query($conn,$getDetail);
            while($rowDetails = mysqli_fetch_assoc($resultDetails)){
                $idStudent = $rowDetails['id_student'];
                $icNo = $rowDetails['ic_no'];
                $name = $rowDetails['name'];
                $email = $rowDetails['email'];
                $tel = $rowDetails['no_tel'];
                $address = $rowDetails['address'];
                $course = $rowDetails['course'];
                $status = $rowDetails['status'];
                $batch_id = $rowDetails['batch_id'];
            };
            echo "success|".$rid."|".$rusername."|".$rpassword."|".$rusertype."|".$idStudent."|".$icNo."|".$name."|".$email."|".$tel."|".$address."|".$course."|".$status."|".$batch_id;
        }else if($rusertype=="committee"){
            $getDetail="SELECT id_staff,staff_ic,staff_name,staff_email,staff_no_tel,staff_position FROM committee WHERE id_staff='$rid';";
            $resultDetails = mysqli_query($conn,$getDetail);
            while($rowDetails = mysqli_fetch_assoc($resultDetails)){
                $idStaff = $rowDetails['id_staff'];
                $staffIc = $rowDetails['staff_ic'];
                $staffName = $rowDetails['staff_name'];
                $staffEmail = $rowDetails['staff_email'];
                $staffTelNo = $rowDetails['staff_no_tel'];
                $staffPosition = $rowDetails['staff_position'];
            };
            echo "success|".$rid."|".$rusername."|".$rpassword."|".$rusertype."|".$idStaff."|".$staffIc."|".$staffName."|".$staffEmail."|".$staffTelNo."|".$staffPosition;
        }
        exit;
    }else{
        echo "fail";
        exit;
    }

    mysqli_close($conn);
?>