package com.pronoymukherjee.indianrailwaysenquiry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mukhe on 17-Feb-18.
 */

public class GridAdapter extends BaseAdapter {
    private String data[];
    private int imgId[];
    private Context context;
    public GridAdapter(Context context,String data[],int imgId[]){
        this.context=context;
        this.data=data;
        this.imgId=imgId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View grid=view;
        if(view==null){
            grid=layoutInflater.inflate(R.layout.grid_single,null);
            TextView textView=grid.findViewById(R.id.textGrid);
            textView.setText(data[i]);
            ImageView imageView=grid.findViewById(R.id.imageGrid);
            imageView.setImageResource(imgId[i]);
        }
        return grid;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}
