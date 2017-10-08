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
import com.shay.incursio.internshipappv2.bean.Company;
import com.shay.incursio.internshipappv2.bean.Conn;
import com.shay.incursio.internshipappv2.bean.Student;
import com.shay.incursio.internshipappv2.bean.Vacan;

import java.util.ArrayList;
import java.util.HashMap;

public class viewVacancy extends AppCompatActivity {
    ListView lvVacan;
    ArrayList<Vacan> vacanList;
    String host = Conn.getHost();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vacancy);
        HashMap PostData = new HashMap();
        PostData.put("ID", Company.getCompanyId());
        lvVacan = (ListView)findViewById(R.id.lvVacan);
        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(viewVacancy.this, PostData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.d("data",s);
                if(s.equalsIgnoreCase("no data")){
                    Toast.makeText(viewVacancy.this,"No Data..",Toast.LENGTH_LONG).show();

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
                            String idVacan = item.id_vacancy;
                            return  idVacan;
                        }
                    });

                    FunDapter<Vacan> adapter = new FunDapter<>(viewVacancy.this,vacanList,R.layout.layout_list,dict);
                    lvVacan.setAdapter(adapter);
                    registerForContextMenu(lvVacan);
                }

            }
        });
        taskRead.execute(host+"getVacan.php");


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId()==R.id.lvVacan) {
            menu.setHeaderTitle("Options");
            String[] menuItems = getResources().getStringArray(R.array.compVacanOption);
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String[] menuItems = getResources().getStringArray(R.array.compVacanOption);
        String menuItemName = menuItems[menuItemIndex];
        String selectedWord = ((TextView)info.targetView.findViewById(R.id.tvIDvacan)).getText().toString();
        Log.d("Item id: ", selectedWord);
        HashMap PostData = new HashMap();
        PostData.put("id_vacancy", selectedWord);
        if(menuItemName.equalsIgnoreCase("Edit")){
            PostResponseAsyncTask getDetail = new PostResponseAsyncTask(viewVacancy.this, PostData, new AsyncResponse() {
                @Override
                public void processFinish(String s) {
                    Log.d("ReturnData ", s);
                    Intent i = new Intent(viewVacancy.this,newVacancy.class);
                    i.putExtra("method","update");
                    i.putExtra("data",s);
                    startActivity(i);
                    finish();
                }
            });
            getDetail.execute(host+"getDetailVacan.php");
        }else if(menuItemName.equalsIgnoreCase("Delete")){
            PostResponseAsyncTask taskDelete = new PostResponseAsyncTask(viewVacancy.this, PostData, new AsyncResponse() {
                @Override
                public void processFinish(String s) {
                    if(s.equalsIgnoreCase("success")){
                        Toast.makeText(viewVacancy.this,"Successfully delete.",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(viewVacancy.this,viewVacancy.class);
                        startActivity(i);
                    }else if(s.equalsIgnoreCase("fail")){
                        Toast.makeText(viewVacancy.this,"failed to delete.",Toast.LENGTH_LONG).show();
                    }
                }
            });
            taskDelete.execute(host+"deleteVacan.php");
        }

        return true;
    }
    @Override
    public void onBackPressed() {
        finish();
    }


}
