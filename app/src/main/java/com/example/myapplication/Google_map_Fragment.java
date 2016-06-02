package com.example.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public  class Google_map_Fragment  extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View GoogleMapLayout = inflater.inflate(R.layout.google_map_layout,
                container,  false );
        return GoogleMapLayout;
    }

}