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

public  class Achievement_Fragment extends Fragment {

    View AchieveLayout;
    ListView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AchieveLayout = inflater.inflate(R.layout.achievement_layout, container,  false );
        lv = (ListView) AchieveLayout.findViewById(R.id.listView);
        String [] arr = new String[]{"First","Second","Third"};
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,arr);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Intent intent = new Intent(getActivity(),First.class);
                    Achievement_Fragment.this.startActivity(intent);
                }
                else if (position == 1){
                    Intent intent = new Intent(getActivity(),Second.class);
                    Achievement_Fragment.this.startActivity(intent);
                }
                else if (position == 2) {
                    Intent intent = new Intent(getActivity(),Third.class);
                    Achievement_Fragment.this.startActivity(intent);
                }
            }
        });
        return  AchieveLayout;


    }
}