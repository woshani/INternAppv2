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
import com.shay.incursio.internshipappv2.bean.Company;
import com.shay.incursio.internshipappv2.bean.Conn;

import java.util.HashMap;
import java.util.StringTokenizer;

public class newVacancy extends AppCompatActivity {
    EditText position,numbersVacancy,desc;
    Button newVacancy;
    String host = Conn.getHost();
    public String idS,companyS,positionS,noVacanS,descS,dateS,statusS;
    public String method;
    validator val = new validator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_vacancy);
        method = getIntent().getStringExtra("method");
        String data = getIntent().getStringExtra("data");

        position = (EditText)findViewById(R.id.etCompPosition);
        numbersVacancy = (EditText)findViewById(R.id.etCompNumberVacancy);
        desc = (EditText)findViewById(R.id.etCompDesc);
        newVacancy = (Button)findViewById(R.id.btnCompAdvertise);

        if(method.equalsIgnoreCase("update")){
            StringTokenizer tokens = new StringTokenizer(data, "|");
             idS = tokens.nextToken();
             companyS = tokens.nextToken();
             positionS = tokens.nextToken();
             noVacanS = tokens.nextToken();
             descS = tokens.nextToken();
             dateS = tokens.nextToken();
             statusS = tokens.nextToken();

            position.setText(positionS);
            numbersVacancy.setText(noVacanS);
            desc.setText(descS);
            newVacancy.setText("Update");
        }

        newVacancy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap postData = new HashMap();
                postData.put("position",position.getText().toString());
                postData.put("number",numbersVacancy.getText().toString());
                postData.put("description",desc.getText().toString());
                Log.d("method",method);

                if(method.equalsIgnoreCase("add")){
                    if(val.validate(position) && val.validate(desc)){
                        postData.put("IdCompany", Company.getCompanyId());
                        PostResponseAsyncTask newVacancyTask = new PostResponseAsyncTask(newVacancy.this,postData,new AsyncResponse(){

                            @Override
                            public void processFinish(String result) {
                                if(result.equalsIgnoreCase("success")){
                                    Toast.makeText(newVacancy.this,"Insert Successfull!", Toast.LENGTH_LONG).show();
                                    Intent mainMenu = new Intent(newVacancy.this,companyMenu.class);
                                    startActivity(mainMenu);
                                    finish();
                                }else if(result.equalsIgnoreCase("fail")){
                                    Toast.makeText(newVacancy.this,"Insert failed!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                        newVacancyTask.execute(host+"newAdvertise.php");
                    }
                }else if(method.equalsIgnoreCase("update")){
                    if(val.validate(position) && val.validate(desc)){
                        postData.put("IdVacan", idS);
                        PostResponseAsyncTask updVacancyTask = new PostResponseAsyncTask(newVacancy.this,postData,new AsyncResponse(){

                            @Override
                            public void processFinish(String result) {
                                if(result.equalsIgnoreCase("success")){
                                    Toast.makeText(newVacancy.this,"Update Successfull!", Toast.LENGTH_LONG).show();
                                    Intent viewVacan = new Intent(newVacancy.this,viewVacancy.class);
                                    startActivity(viewVacan);
                                    finish();
                                }else if(result.equalsIgnoreCase("fail")){
                                    Toast.makeText(newVacancy.this,"Update failed!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                        updVacancyTask.execute(host+"updateAdvetise.php");
                    }
                }
            }
        });


    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
