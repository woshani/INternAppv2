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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.shay.incursio.internshipappv2.bean.Application;
import com.shay.incursio.internshipappv2.bean.Company;
import com.shay.incursio.internshipappv2.bean.Conn;

import java.util.ArrayList;
import java.util.HashMap;

public class viewApplicationCom extends AppCompatActivity {
    ListView lvVacan;
    ArrayList<Application> vacanList;
    String host = Conn.getHost();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_application_com);
        HashMap PostData = new HashMap();
        PostData.put("company", Company.getCompanyId());
        lvVacan = (ListView)findViewById(R.id.lvApp);
        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(viewApplicationCom.this,PostData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.d("data xxxx",s);
                if(s.equalsIgnoreCase("no data")){
                    Toast.makeText(viewApplicationCom.this,"No Data..",Toast.LENGTH_LONG).show();
                }else{
                    vacanList = new JsonConverter<Application>().toArrayList(s,Application.class);
                    BindDictionary<Application> dict = new BindDictionary<>();

                    dict.addStringField(R.id.textView, new StringExtractor<Application>() {
                        @Override
                        public String getStringValue(Application item, int position) {
                            String vidView =  item.id_vacancy;
                            return vidView;
                        }
                    });

                    dict.addStringField(R.id.textView2, new StringExtractor<Application>() {
                        @Override
                        public String getStringValue(Application item, int position) {
                            String positionView = "POSITION: "+ item.position;
                            return positionView;
                        }
                    });

                    dict.addStringField(R.id.textView3, new StringExtractor<Application>() {
                        @Override
                        public String getStringValue(Application item, int position) {
                            String SIDView =  item.id_student;
                            return SIDView;
                        }
                    });

                    dict.addStringField(R.id.textView4, new StringExtractor<Application>() {
                        @Override
                        public String getStringValue(Application item, int position) {
                            String snView = "Student Name: "+ item.name;
                            return snView;
                        }
                    });

                    dict.addStringField(R.id.textView5, new StringExtractor<Application>() {
                        @Override
                        public String getStringValue(Application item, int position) {
                            String statView = "Application Status: "+ item.application_status;
                            return statView;
                        }
                    });

                    FunDapter<Application> adapter = new FunDapter<>(viewApplicationCom.this,vacanList,R.layout.layout_application,dict);
                    lvVacan.setAdapter(adapter);
                    registerForContextMenu(lvVacan);
                }

            }
        });
        taskRead.execute(host+"viewAppListCompany.php");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId()==R.id.lvApp) {
            menu.setHeaderTitle("Options");
            String[] menuItems = getResources().getStringArray(R.array.applicationMenu);
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String[] menuItems = getResources().getStringArray(R.array.applicationMenu);
        String menuItemName = menuItems[menuItemIndex];
        String selectedWord = ((TextView)info.targetView.findViewById(R.id.textView)).getText().toString();
        String studentID = ((TextView)info.targetView.findViewById(R.id.textView3)).getText().toString();
        String status = "";
        if(menuItemName.equalsIgnoreCase("Accept")){
            status="ACCEPTED";
        }else if(menuItemName.equalsIgnoreCase("Reject")){
            status="REJECTED";
        }
        Log.d("Item id: ", selectedWord);
        Log.d("Comp id: ", Company.getCompanyId());
        HashMap PostDataVac = new HashMap();
        PostDataVac.put("appID",selectedWord);
        PostDataVac.put("studID",studentID);
        PostDataVac.put("stat",status);

        Log.d("Item id: ", selectedWord);
        Log.d("stud id: ", studentID);
        Log.d("stat : ", status);
        PostResponseAsyncTask taskApply = new PostResponseAsyncTask(viewApplicationCom.this, PostDataVac, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.d("result",s);
                if(s.equalsIgnoreCase("success")){
                    Toast.makeText(viewApplicationCom.this,"Application successfully sent.",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(viewApplicationCom.this,viewApplicationCom.class);
                    startActivity(i);
                    finish();
                }else if(s.equalsIgnoreCase("failed")){
                    Toast.makeText(viewApplicationCom.this,"Application failed to sent.",Toast.LENGTH_LONG).show();
                }
            }
        });
        taskApply.execute(host+"updateStatusApplication.php");

        return true;
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
