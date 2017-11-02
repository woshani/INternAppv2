package com.shay.incursio.internshipappv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.shay.incursio.internshipappv2.Utils.CustomAdapterSpinner;
import com.shay.incursio.internshipappv2.Utils.ListAdapter;
import com.shay.incursio.internshipappv2.bean.ApplicationStatusStudent;
import com.shay.incursio.internshipappv2.bean.Batch;
import com.shay.incursio.internshipappv2.bean.Conn;
import com.shay.incursio.internshipappv2.bean.Student;
import com.shay.incursio.internshipappv2.bean.StudentNS;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import static android.R.id.list;

public class manageStudent extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button filter,register;
    String batch[];
    String idBatch[];
    String ids = "-";
    Spinner batchSelect;
    String host = Conn.getHost();
    JSONArray jsonArray;
    JSONArray jsonArrayStudent,jsonArrayAppStat;
    ListView lvMG;
    ListAdapter studAdapter;
    ArrayList<StudentNS> lS = new ArrayList<StudentNS>();
    ArrayList<ApplicationStatusStudent> vappStats;
    StudentNS stud;
    InputStream is=null;
    String result=null;
    String line=null;
    String statusApplication;
    HttpURLConnection urlConnection = null;

    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_student);
        filter = (Button) findViewById(R.id.btnfilterStud);
        batchSelect = (Spinner)findViewById(R.id.spinnerBatch);
        lvMG = (ListView)findViewById(R.id.lvMg);
        register = (Button)findViewById(R.id.btnReigster);


        PostResponseAsyncTask getBatch = new PostResponseAsyncTask(manageStudent.this, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                if(s.equalsIgnoreCase("no data")){
                    Toast.makeText(manageStudent.this,"No Data..",Toast.LENGTH_LONG).show();
                }else{
                Log.d("Batch-----",s);

                List<Batch> lB =new ArrayList<Batch>();
                    try {
                        jsonArray = new JSONArray(s);

                        for(int i=0; i<jsonArray.length(); i++) {
                            Batch b = new Batch();
                            b.setId(jsonArray.getJSONObject(i).getString("id"));
                            b.setBatch(jsonArray.getJSONObject(i).getString("batch"));
                            b.setSem(jsonArray.getJSONObject(i).getString("sem"));
                            lB.add(b);
                        }
                        batch = new String[lB.size()];
                        idBatch = new String[lB.size()];
                        List<String> listBatch = new ArrayList<String>();
                        List<String> listIdBatch = new ArrayList<String>();

                        for(int i = 0;i< lB.size();i++){
                            listBatch.add("Sem "+lB.get(i).getSem()+"/"+lB.get(i).getBatch());
                            listIdBatch.add(lB.get(i).getId());
                        }

                        listBatch.toArray(batch);
                        listIdBatch.toArray(idBatch);

                        CustomAdapterSpinner customAdapter=new CustomAdapterSpinner(getApplicationContext(),batch,idBatch);
                        batchSelect.setAdapter(customAdapter);


                        // function when buton filter is clicked
                        filter.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //String id = batchSelect.getSelectedItem().toString();
                                batchSelect.setOnItemSelectedListener(manageStudent.this);
                                if(ids.equalsIgnoreCase("-") || ids.equalsIgnoreCase("")){
                                    Toast.makeText(getApplicationContext(), "Please choose batch first", Toast.LENGTH_SHORT).show();
                                }else{
                                    HashMap idss = new HashMap();
                                    idss.put("id",ids);
                                    PostResponseAsyncTask getByBatch = new PostResponseAsyncTask(manageStudent.this,idss, new AsyncResponse() {

                                        @Override
                                        public void processFinish(String s) {
                                            if(s.equalsIgnoreCase("no data")){
                                                Toast.makeText(manageStudent.this,"No Data..",Toast.LENGTH_LONG).show();
                                                lS.clear();
                                                studAdapter = new ListAdapter(manageStudent.this, lS);
                                                lvMG.setAdapter(studAdapter);
                                            }else{

                                                try {
                                                    lS.clear();
                                                    jsonArrayStudent = new JSONArray(s);
                                                    for(int i=0; i<jsonArrayStudent.length(); i++) {
                                                        stud = new StudentNS();
                                                        vappStats = new ArrayList<>();
                                                        stud.setStudentId(jsonArrayStudent.getJSONObject(i).getString("id_student"));
                                                        stud.setName(jsonArrayStudent.getJSONObject(i).getString("name"));
                                                        stud.setEmail(jsonArrayStudent.getJSONObject(i).getString("email"));
                                                        stud.setTelNo(jsonArrayStudent.getJSONObject(i).getString("no_tel"));
                                                        stud.setAddress(jsonArrayStudent.getJSONObject(i).getString("address"));
                                                        stud.setCourse(jsonArrayStudent.getJSONObject(i).getString("course"));
                                                        stud.setStatus(jsonArrayStudent.getJSONObject(i).getString("status"));
                                                        stud.setBatchId(jsonArrayStudent.getJSONObject(i).getString("batch_id"));
                                                        stud.setIcNo(jsonArrayStudent.getJSONObject(i).getString("ic_no"));
                                                        lS.add(stud);
                                                    }
                                                    studAdapter = new ListAdapter(manageStudent.this, lS);
                                                    lvMG.setAdapter(studAdapter);
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                    });
                                    getByBatch.execute(host+"getStudentBatch.php");
                                }


                            }
                        });
//                      button when register is clicked
                        register.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ArrayList<String> studIDs = studAdapter.getSelectedString();
                                if(studIDs.size() < 0){

                                }else{
                                    HashMap PostData = new HashMap();
                                    for(int i =0;i < studIDs.size();i++){
                                        Log.d("matric "+i,studIDs.get(i));
                                    }

                                    String joined = TextUtils.join("|", studIDs);
                                    PostData.put("id_stud", joined.trim());
                                    PostResponseAsyncTask getDetail = new PostResponseAsyncTask(manageStudent.this, PostData, new AsyncResponse() {
                                    @Override
                                            public void processFinish(String s) {
                                                Log.d("ReturnData ", s);
                                                if(s.equalsIgnoreCase("success")){
                                                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                                                    Intent i = new Intent(manageStudent.this,CommitteMenu.class);
                                                    startActivity(i);
                                                    finish();

                                                }else{
                                                    Toast.makeText(getApplicationContext(), "Failed,Try Again..", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                        getDetail.execute(host+"registerStudent.php");
                                }
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        getBatch.execute(host+"getBatch.php");

    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId()==R.id.lvMg) {
            menu.setHeaderTitle("Manage Student");
            String[] menuItems = getResources().getStringArray(R.array.manageStud);
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return true;
    }
    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        //Toast.makeText(getApplicationContext(), idBatch[position], Toast.LENGTH_LONG).show();
        ids = idBatch[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
