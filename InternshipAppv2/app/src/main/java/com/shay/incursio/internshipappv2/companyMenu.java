package com.shay.incursio.internshipappv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shay.incursio.internshipappv2.bean.Company;

public class companyMenu extends AppCompatActivity {
    TextView id,name;
    Button goTonewVacan,goToViewVacan,goToViewApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_menu);
        id = (TextView)findViewById(R.id.tvComID);
        name = (TextView)findViewById(R.id.tvComName);
        name.setText(Company.getName());
        id.setText(Company.getCompanyId());
        id.setText(Company.getCompanyId());

        goTonewVacan = (Button)findViewById(R.id.btnNewVacancy);
        goToViewVacan = (Button)findViewById(R.id.btnViewVacancy);
        goToViewApp = (Button)findViewById(R.id.button);

        goTonewVacan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addVacan = new Intent(companyMenu.this,newVacancy.class);
                addVacan.putExtra("method","add");
                addVacan.putExtra("data","none");
                startActivity(addVacan);
            }
        });

        goToViewVacan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewVacan = new Intent(companyMenu.this,viewVacancy.class);
                startActivity(viewVacan);
            }
        });

        goToViewApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewApp = new Intent(companyMenu.this,viewApplicationCom.class);
                startActivity(viewApp);
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
