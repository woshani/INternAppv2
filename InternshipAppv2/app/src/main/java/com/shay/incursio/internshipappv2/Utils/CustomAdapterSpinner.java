package com.shay.incursio.internshipappv2.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shay.incursio.internshipappv2.R;

/**
 * Created by User on 08/10/2017.
 */

public class CustomAdapterSpinner extends BaseAdapter {
    Context context;
    String batchs[];
    String[] idBatchs;
    LayoutInflater inflter;

    public CustomAdapterSpinner(Context applicationContext, String[] batch, String[] idBatch) {
        this.context = applicationContext;
        this.batchs = batch;
        this.idBatchs = idBatch;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return batchs.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.batch, null);
        TextView batches = (TextView) view.findViewById(R.id.tvBatchSpinner);
        TextView idBatches = (TextView)view.findViewById(R.id.idBatch);
        batches.setText(batchs[i]);
        idBatches.setText(idBatchs[i]);
        return view;
    }
}
