package com.shay.incursio.internshipappv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.shay.incursio.internshipappv2.bean.Committee;
import com.shay.incursio.internshipappv2.bean.Company;
import com.shay.incursio.internshipappv2.bean.Conn;
import com.shay.incursio.internshipappv2.bean.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etUsername,etPassword;
    String username,password;
    Button btnLogin;
    String host = Conn.getHost();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();

                HashMap postData = new HashMap();
                postData.put("username",username);
                postData.put("password",password);
                Log.d("username",username);
                Log.d("password",password);

                PostResponseAsyncTask loginTask = new PostResponseAsyncTask(MainActivity.this,postData, new AsyncResponse() {
                    @Override
                    public void processFinish(String result) {
                        String [] myList = result.split("\\|",-1);
                        Log.d("login result",result);
                        if(!result.equalsIgnoreCase("fail")){
                            //Toast.makeText(MainActivity.this,"Login success",Toast.LENGTH_SHORT).show();
                            if(myList[4].equalsIgnoreCase("student")){
                                Student.setUserId(myList[1]);
                                Student.setUsername(myList[2]);
                                Student.setPassword(myList[3]);
                                Student.setUsertype(myList[4]);
                                Student.setStudentId(myList[5]);
                                Student.setIcNo(myList[6]);
                                Student.setName(myList[7]);
                                Student.setEmail(myList[8]);
                                Student.setTelNo(myList[9]);
                                Student.setAddress(myList[10]);
                                Student.setCourse(myList[11]);
                                Student.setStatus(myList[12]);
                                Student.setBatchId(myList[13]);

                                if(Student.getStatus().equalsIgnoreCase("1")){
                                    Intent studentPage = new Intent(MainActivity.this,studentMenu.class);
                                    startActivity(studentPage);
                                    finish();
                                }else if(Student.getStatus().equalsIgnoreCase("0")){
                                    Toast.makeText(MainActivity.this,"You are not registered,Please contact committee..",Toast.LENGTH_SHORT).show();
                                }



                            }else if(myList[4].equalsIgnoreCase("company")){
                                Company.setCompanyId(myList[1]);
                                Company.setUsername(myList[2]);
                                Company.setPassword(myList[3]);
                                Company.setCompanyId(myList[5]);
                                Company.setName(myList[6]);
                                Company.setPostcode(myList[7]);
                                Company.setAddress(myList[8]);
                                Company.setState(myList[9]);
                                Company.setContactNumber(myList[10]);
                                Company.setEmail(myList[11]);

                                Intent companyPage = new Intent(MainActivity.this,companyMenu.class);
                                startActivity(companyPage);
                                finish();

                            }else if(myList[4].equalsIgnoreCase("committee")){
                                Committee.setIduser(myList[1]);
                                Committee.setUsername(myList[2]);
                                Committee.setPassword(myList[3]);
                                Committee.setUsertype(myList[4]);
                                Committee.setIdStaff(myList[5]);
                                Committee.setStaffIc(myList[6]);
                                Committee.setStaffName(myList[7]);
                                Committee.setEmail(myList[8]);
                                Committee.setTelNo(myList[9]);
                                Committee.setPosition(myList[10]);
                                Intent committePage = new Intent(MainActivity.this,CommitteMenu.class);
                                startActivity(committePage);
                                finish();
                            }

                        }else{
                            Toast.makeText(MainActivity.this,"Login failed!, please check your USERNAME or PASSWORD",Toast.LENGTH_LONG).show();
                        }
                    }
                });

                loginTask.execute(host+"login.php");
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
