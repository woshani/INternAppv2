package com.shay.incursio.internshipappv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CommitteMenu extends AppCompatActivity {
    Button viewVacan,registerCom,mngStud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committe_menu);
        viewVacan = (Button)findViewById(R.id.btnViewVacanCommittee);
        registerCom = (Button)findViewById(R.id.btnRegisterCompany);
        mngStud = (Button)findViewById(R.id.btnManageStudent);

        viewVacan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CommitteMenu.this,viewVacancyCommitte.class);
                startActivity(i);
            }
        });

        registerCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CommitteMenu.this,registerCompany.class);
                startActivity(i);
            }
        });

        mngStud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CommitteMenu.this,manageStudent.class);
                startActivity(i);
            }
        });

    }
    @Override
    public void onBackPressed() {
        finish();
    }
}

