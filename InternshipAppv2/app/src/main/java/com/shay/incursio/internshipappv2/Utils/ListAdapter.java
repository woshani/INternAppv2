package com.shay.incursio.internshipappv2.Utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.shay.incursio.internshipappv2.R;
import com.shay.incursio.internshipappv2.bean.ApplicationStatusStudent;
import com.shay.incursio.internshipappv2.bean.Conn;
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
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.shay.incursio.internshipappv2.manageStudent;


/**
 * Created by wosha on 31/10/2017.
 */

public class ListAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<StudentNS> objects;
    ArrayList<String> selectedStrings = new ArrayList<String>();
    CheckBox cbBuy;
    String StatusStud = "";
    String statusApplication="";
    ArrayList<ApplicationStatusStudent> vectAppStat;
    JSONArray jsonArrayAppStat;
    String host = Conn.getHost();
    StudentNS p;
    InputStream is=null;
    String result=null;
    String line=null;
    HttpURLConnection urlConnection = null;

    String text;


    public ListAdapter(Context context, ArrayList<StudentNS> student) {
        ctx = context;
        objects = student;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    StudentNS getStud(int position) {
        return ((StudentNS) getItem(position));
    }

    public ArrayList<String> getSelectedString(){
        return selectedStrings;
    }

    CompoundButton.OnCheckedChangeListener myCheckChangList = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
            if (isChecked) {
                selectedStrings.add(getStud((Integer) buttonView.getTag()).getStudentId());
            }else{
                selectedStrings.remove(getStud((Integer) buttonView.getTag()).getStudentId());
            }

        }
    };

    public String status(String id){
        HashMap idStud = new HashMap();
        idStud.put("idStudent",id);
        PostResponseAsyncTask getStat = new PostResponseAsyncTask(ctx, idStud, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                try{
                    if(!s.equalsIgnoreCase("no data")){
                        jsonArrayAppStat = new JSONArray(s);
                        for(int i=0; i<jsonArrayAppStat.length(); i++) {
                            result = jsonArrayAppStat.getJSONObject(i).getString("comp_name") + ": " + jsonArrayAppStat.getJSONObject(i).getString("application_status")+"\n";
                            Log.d("test id stud",p.getStudentId());
                            Log.d("test",String.valueOf(jsonArrayAppStat.length()));
                            Log.d("test name",jsonArrayAppStat.getJSONObject(i).getString("comp_name"));
                        }
                    }else{
                        result = "No Application";
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        getStat.execute(host+"getStatusApplication.php");
        return result;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.student_by_batch, parent, false);
        }
        p = getStud(position);

        if(p.getStatus().equalsIgnoreCase("1")){
            StatusStud = "Registered";
        }else if(p.getStatus().equalsIgnoreCase("0")){
            StatusStud = "Not Registered";
        }

        ((TextView) view.findViewById(R.id.tvStudStatusApp)).setText(statusApplication);
        ((TextView) view.findViewById(R.id.tvStudName)).setText(p.getName());
        ((TextView) view.findViewById(R.id.tvStudStatus)).setText(StatusStud);

        cbBuy = (CheckBox)view.findViewById(R.id.cbxStud);
        cbBuy.setOnCheckedChangeListener(myCheckChangList);
        cbBuy.setText(p.getStudentId());
        cbBuy.setTag(position);
        return view;
    }

}
