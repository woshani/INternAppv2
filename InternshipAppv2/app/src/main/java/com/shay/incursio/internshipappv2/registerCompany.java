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
import com.shay.incursio.internshipappv2.bean.Conn;

import java.util.HashMap;

public class registerCompany extends AppCompatActivity {
    EditText companyName2,companyAddress2,companyPostcode2,companyLocation2,companyState2,companyTel2,companyEmail2,username2,password2;
    String host = Conn.getHost();
    validator val = new validator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register_company);
        companyName2 = (EditText)findViewById(R.id.etCN);
        companyAddress2 = (EditText)findViewById(R.id.etCADD);
        companyPostcode2 = (EditText)findViewById(R.id.etCPOS);
        companyLocation2 = (EditText)findViewById(R.id.etCLO);
        companyState2 = (EditText)findViewById(R.id.etCSTAT);
        companyTel2 = (EditText)findViewById(R.id.etCTEL);
        companyEmail2 = (EditText)findViewById(R.id.etCEMAIL);
        username2 = (EditText)findViewById(R.id.eetCUSER);
        password2 = (EditText)findViewById(R.id.etCPASS);


        Button register = (Button)findViewById(R.id.btnCREGIS);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap postData = new HashMap();
                postData.put("username",username2.getText().toString());
                postData.put("password",password2.getText().toString());
                postData.put("name",companyName2.getText().toString());
                postData.put("address",companyAddress2.getText().toString());
                postData.put("postcode",companyPostcode2.getText().toString());
                postData.put("location",companyLocation2.getText().toString());
                postData.put("state",companyState2.getText().toString());
                postData.put("tel",companyTel2.getText().toString());
                postData.put("email",companyEmail2.getText().toString());

                if(val.validate(companyName2) && val.validate(username2)&& val.validate(password2)&& val.validate(companyAddress2)){
                    PostResponseAsyncTask registerTask = new PostResponseAsyncTask(registerCompany.this,postData, new AsyncResponse(){

                        @Override
                        public void processFinish(String s) {
                            Log.d("AAAAA",s);
                            if(s.trim().equalsIgnoreCase("fail")){
                                Toast.makeText(registerCompany.this,"FAIL",Toast.LENGTH_LONG).show();

                            }else{
                                Toast.makeText(registerCompany.this,"SUCCESS",Toast.LENGTH_LONG).show();
                                Intent mainMenu = new Intent(registerCompany.this,CommitteMenu.class);
                                startActivity(mainMenu);
                                finish();
                            }

                        }
                    });

                    registerTask.execute(host+"registerCompany.php");
                }



            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
