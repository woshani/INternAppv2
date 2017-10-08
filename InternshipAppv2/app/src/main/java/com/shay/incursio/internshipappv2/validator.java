package com.shay.incursio.internshipappv2;

import android.widget.TextView;

/**
 * Created by User on 26/09/2017.
 */

public class validator {
    public boolean validate(TextView textView){
        if(textView.getText().toString().isEmpty()){
            textView.setError(textView.getHint()+" is required!");
            return false;
        }else{
            textView.setError(null);
            return true;
        }
    }

}
