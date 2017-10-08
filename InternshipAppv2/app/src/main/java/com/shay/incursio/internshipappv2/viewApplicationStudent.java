package com.shay.incursio.internshipappv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.shay.incursio.internshipappv2.bean.Application;
import com.shay.incursio.internshipappv2.bean.Student;
import com.shay.incursio.internshipappv2.bean.Conn;

import java.util.ArrayList;
import java.util.HashMap;

public class viewApplicationStudent extends AppCompatActivity {
    ListView lvVacan;
    ArrayList<Application> vacanList;
    String host = Conn.getHost();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_application_student);

        HashMap PostData = new HashMap();
        PostData.put("student", Student.getStudentId());
        lvVacan = (ListView)findViewById(R.id.lvStudent);
        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(viewApplicationStudent.this,PostData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.d("data",s);
                if(s.equalsIgnoreCase("no data")){
                    Toast.makeText(viewApplicationStudent.this,"No Data..",Toast.LENGTH_LONG).show();

                }else{
                    vacanList = new JsonConverter<Application>().toArrayList(s,Application.class);
                    BindDictionary<Application> dict = new BindDictionary<>();

                    dict.addStringField(R.id.textView8, new StringExtractor<Application>() {
                        @Override
                        public String getStringValue(Application item, int position) {
                            String vidView =  item.id_vacancy;
                            return vidView;
                        }
                    });

                    dict.addStringField(R.id.textView12, new StringExtractor<Application>() {
                        @Override
                        public String getStringValue(Application item, int position) {
                            String positionView = "POSITION: "+ item.position;
                            return positionView;
                        }
                    });

                    dict.addStringField(R.id.textView11, new StringExtractor<Application>() {
                        @Override
                        public String getStringValue(Application item, int position) {
                            String SIDView = item.COMP_NAME;
                            return SIDView;
                        }
                    });

                    dict.addStringField(R.id.textView9, new StringExtractor<Application>() {
                        @Override
                        public String getStringValue(Application item, int position) {
                            String statView = "Application Status: "+ item.application_status;
                            return statView;
                        }
                    });

                    FunDapter<Application> adapter = new FunDapter<>(viewApplicationStudent.this,vacanList,R.layout.layout_application_student,dict);
                    lvVacan.setAdapter(adapter);
                    registerForContextMenu(lvVacan);
                }

            }
        });
        taskRead.execute(host+"viewAppListStudent.php");
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
