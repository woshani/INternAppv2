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
import com.shay.incursio.internshipappv2.R;
import com.shay.incursio.internshipappv2.bean.StudentNS;

import java.util.ArrayList;

/**
 * Created by wosha on 31/10/2017.
 */

public class ListAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<StudentNS> objects;
    ArrayList<String> selectedStrings = new ArrayList<String>();
    CheckBox cbBuy;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.student_by_batch, parent, false);
        }

        StudentNS p = getStud(position);

        ((TextView) view.findViewById(R.id.tvStudName)).setText(p.getName());
        cbBuy = (CheckBox)view.findViewById(R.id.cbxStud);
        cbBuy.setOnCheckedChangeListener(myCheckChangList);
        cbBuy.setText(p.getStudentId());
        cbBuy.setTag(position);
        return view;
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
}
