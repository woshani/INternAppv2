package com.shay.incursio.internshipappv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shay.incursio.internshipappv2.bean.Student;

public class studentMenu extends AppCompatActivity {
    TextView idStud,NameStud,IC;
    Button btnViewAll,btnviewstat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);

        idStud = (TextView)findViewById(R.id.tvStudID);
        IC = (TextView)findViewById(R.id.tvIC);
        NameStud = (TextView)findViewById(R.id.tvStudName);
        btnViewAll = (Button)findViewById(R.id.btnViewAll);
        btnviewstat = (Button)findViewById(R.id.button2);

        idStud.setText(Student.getStudentId());
        NameStud.setText(Student.getName());
        IC.setText(Student.getIcNo());

        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(studentMenu.this,viewVacancyStud.class);
                startActivity(i);
            }
        });

        btnviewstat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(studentMenu.this,viewApplicationStudent.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
