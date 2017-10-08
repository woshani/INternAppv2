package com.shay.incursio.internshipappv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.shay.incursio.internshipappv2.bean.Conn;
import com.shay.incursio.internshipappv2.bean.Student;
import com.shay.incursio.internshipappv2.bean.Vacan;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shay on 7/23/2017.
 */

public class viewVacancyStud  extends AppCompatActivity{

    ListView lvVacan;
    ArrayList<Vacan> vacanList;
    String host = Conn.getHost();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vacancy);
        HashMap PostData = new HashMap();
        PostData.put("ID", "all");
        lvVacan = (ListView)findViewById(R.id.lvVacan);
        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(viewVacancyStud.this, PostData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.d("data",s);
                if(s.equalsIgnoreCase("no data")){
                    Toast.makeText(viewVacancyStud.this,"No Data..",Toast.LENGTH_LONG).show();

                }else{
                    vacanList = new JsonConverter<Vacan>().toArrayList(s,Vacan.class);

                    BindDictionary<Vacan> dict = new BindDictionary<>();
                    dict.addStringField(R.id.tvPosition, new StringExtractor<Vacan>() {
                        @Override
                        public String getStringValue(Vacan item, int position) {
                            String positionView = "POSITION: "+ item.position;
                            return positionView;
                        }
                    });

                    dict.addStringField(R.id.tvDesc, new StringExtractor<Vacan>() {
                        @Override
                        public String getStringValue(Vacan item, int position) {
                            String descView = "Description : "+item.description +'\n'+
                                    "Position Available : "+item.no_of_vacancy+'\n'+
                                    "By : "+item.comp_name+'\n'+
                                    "Location : "+item.comp_addrss+","+item.comp_postcode+","+item.comp_location+","+item.comp_state+'\n'+
                                    "Contact No : "+item.comp_tel+'\n'+
                                    "Email : "+item.comp_email+'\n'+'\n'+
                                    "Posted on : "+item.vcncy_ad_date;
                            return descView;
                        }
                    });

                    dict.addStringField(R.id.tvIDvacan, new StringExtractor<Vacan>() {
                        @Override
                        public String getStringValue(Vacan item, int position) {
                            String id = item.id_vacancy;
                            return id;
                        }
                    });

                    FunDapter<Vacan> adapter = new FunDapter<>(viewVacancyStud.this,vacanList,R.layout.layout_list,dict);
                    lvVacan.setAdapter(adapter);
//                lvVacan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                    }
//                });
                }

            }
        });
        taskRead.execute(host+"getVacan.php");
        registerForContextMenu(lvVacan);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId()==R.id.lvVacan) {
            menu.setHeaderTitle("Options");
            String[] menuItems = getResources().getStringArray(R.array.menu);
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String[] menuItems = getResources().getStringArray(R.array.menu);
        String menuItemName = menuItems[menuItemIndex];
        if(menuItemName.equalsIgnoreCase("Apply")){
            String selectedWord = ((TextView)info.targetView.findViewById(R.id.tvIDvacan)).getText().toString();


            Log.d("Stud id: ", Student.getUserId());
            HashMap PostDataVac = new HashMap();
            PostDataVac.put("studentID",Student.getUserId());
            PostDataVac.put("vacanID",selectedWord);
            PostResponseAsyncTask taskApply = new PostResponseAsyncTask(viewVacancyStud.this, PostDataVac, new AsyncResponse() {
                @Override
                public void processFinish(String s) {
                    Log.d("result",s);
                    if(s.equalsIgnoreCase("inserted")){
                        Toast.makeText(viewVacancyStud.this,"Application successfully sent.",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(viewVacancyStud.this,viewVacancyStud.class);
                        startActivity(i);
                        finish();
                    }else if(s.equalsIgnoreCase("fail")){
                        Toast.makeText(viewVacancyStud.this,"Application failed to sent.",Toast.LENGTH_LONG).show();
                    }else if(s.equalsIgnoreCase("already")){
                        Toast.makeText(viewVacancyStud.this,"Application already sent.",Toast.LENGTH_LONG).show();
                    }
                }
            });
            taskApply.execute(host+"applyVacan.php");
        }

        return true;
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
    



