package com.shay.incursio.internshipappv2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.shay.incursio.internshipappv2.bean.Conn;
import com.shay.incursio.internshipappv2.bean.Vacan;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shay on 8/3/2017.
 */

public class viewVacancyCommitte extends AppCompatActivity implements AsyncResponse {


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
        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(viewVacancyCommitte.this,PostData,this);
        taskRead.execute(host+"getVacan.php");


    }

    @Override
    public void processFinish(String s) {
        Log.d("data",s);
        if(s.equalsIgnoreCase("no data")){
            Toast.makeText(viewVacancyCommitte.this,"No Data..",Toast.LENGTH_LONG).show();

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

            FunDapter<Vacan> adapter = new FunDapter<>(viewVacancyCommitte.this,vacanList,R.layout.layout_list,dict);
            lvVacan.setAdapter(adapter);
        }

    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
