package com.shay.incursio.internshipappv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.shay.incursio.internshipappv2.bean.Batch;
import com.shay.incursio.internshipappv2.bean.Conn;
import com.shay.incursio.internshipappv2.bean.Student;
import com.shay.incursio.internshipappv2.bean.StudentNS;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.R.id.list;

public class manageStudent extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button filter;
    String batch[];
    String idBatch[];
    String ids = "-";
    Spinner batchSelect;
    SpinnerAdapter sp;
    String host = Conn.getHost();
    JSONArray jsonArray;
    JSONArray jsonArrayStudent;
    ArrayList<Batch> batchList;
    ListView lvMG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_student);
        filter = (Button) findViewById(R.id.btnfilterStud);
        batchSelect = (Spinner)findViewById(R.id.spinnerBatch);
        lvMG = (ListView)findViewById(R.id.lvMg);

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

                        filter.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //String id = batchSelect.getSelectedItem().toString();
                                batchSelect.setOnItemSelectedListener(manageStudent.this);
                                if(ids.equalsIgnoreCase("-") || ids.equalsIgnoreCase("")){
                                    Toast.makeText(getApplicationContext(), "Please choose batch first", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), ids, Toast.LENGTH_SHORT).show();
                                    HashMap idss = new HashMap();
                                    idss.put("id",ids);
                                    PostResponseAsyncTask getByBatch = new PostResponseAsyncTask(manageStudent.this,idss, new AsyncResponse() {

                                        @Override
                                        public void processFinish(String s) {
                                            Log.d("data return",s);
                                            if(s.equalsIgnoreCase("no data")){
                                                Toast.makeText(manageStudent.this,"No Data..",Toast.LENGTH_LONG).show();
                                            }else{
                                                ArrayList<StudentNS> lS = new ArrayList<StudentNS>();
                                                BindDictionary<StudentNS> dict = new BindDictionary<>();
                                                try {

                                                    jsonArrayStudent = new JSONArray(s);
                                                    for(int i=0; i<jsonArrayStudent.length(); i++) {
                                                        StudentNS stud = new StudentNS();
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

                                                    dict.addStringField(R.id.tvStudName, new StringExtractor<StudentNS>() {
                                                        @Override
                                                        public String getStringValue(StudentNS item, int position) {
                                                            String nameView = "Name : "+ item.getName();
                                                            return nameView;
                                                        }
                                                    });

                                                    dict.addStringField(R.id.tvStudID, new StringExtractor<StudentNS>() {
                                                        @Override
                                                        public String getStringValue(StudentNS item, int position) {
                                                            String idStud = "Matric : "+item.getStudentId();
                                                            return  idStud;
                                                        }
                                                    });
                                                    FunDapter<StudentNS> adapter = new FunDapter<>(manageStudent.this,lS,R.layout.student_by_batch,dict);
                                                    lvMG.setAdapter(adapter);
                                                    registerForContextMenu(lvMG);


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
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String[] menuItems = getResources().getStringArray(R.array.manageStud);
        String menuItemName = menuItems[menuItemIndex];
        String selectedWord = ((TextView)info.targetView.findViewById(R.id.tvStudID)).getText().toString();
        Log.d("Item id", selectedWord);
        selectedWord = selectedWord.replace("Matric :","");
        Log.d("New word ", selectedWord.trim());
        HashMap PostData = new HashMap();
        PostData.put("id_stud", selectedWord.trim());
        if(menuItemName.equalsIgnoreCase("Register")){
            PostResponseAsyncTask getDetail = new PostResponseAsyncTask(manageStudent.this, PostData, new AsyncResponse() {
                @Override
                public void processFinish(String s) {
                    Log.d("ReturnData ", s);
                    if(s.equalsIgnoreCase("success")){
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Failed,Try Again..", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            getDetail.execute(host+"registerStudent.php");
        }

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
