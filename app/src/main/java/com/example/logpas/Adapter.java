package com.example.logpas;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends ArrayAdapter<ListItemCl> {
    private LayoutInflater inflater;
    private List<ListItemCl> listItemCl = new ArrayList<>();



    public Adapter(@NonNull Context context, int resource, List<ListItemCl> listItemCl, LayoutInflater inflater) {
        super(context, resource, listItemCl);
        this.inflater = inflater;
        this.listItemCl = listItemCl;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        ListItemCl listItemClMain = listItemCl.get(position);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item, null, false);
            viewHolder = new ViewHolder();
            viewHolder.data1 = convertView.findViewById(R.id.tvData1);
            viewHolder.data2 = convertView.findViewById(R.id.tvData2);
            viewHolder.data3 = convertView.findViewById(R.id.tvData3);
            convertView.setTag(viewHolder);

        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.data1.setText(listItemClMain.getData_1());
        viewHolder.data2.setText(listItemClMain.getData_2());
        viewHolder.data3.setText(listItemClMain.getData_3());

        return convertView;
    }
    private class ViewHolder{
        TextView data1;
        TextView data2;
        TextView data3;

    }
}
