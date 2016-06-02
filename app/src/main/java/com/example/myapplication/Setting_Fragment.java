package com.example.myapplication;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;

public class Setting_Fragment extends Fragment {

    View SettingLayout;
    ListView lv;
    private String [] arr = new String[]{"個人資料","關於我們"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SettingLayout = inflater.inflate(R.layout.setting_layout, container,  false );
        lv = (ListView) SettingLayout.findViewById(R.id.listView2);

        ArrayAdapter<String>adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,arr);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Intent intent = new Intent(getActivity(),personal_display.class);
                    Setting_Fragment.this.startActivity(intent);
                }
                else if(position == 1){
                    Intent intent = new Intent(getActivity(),us.class);
                    Setting_Fragment.this.startActivity(intent);
                }
            }
        });
        return  SettingLayout;
    }
}