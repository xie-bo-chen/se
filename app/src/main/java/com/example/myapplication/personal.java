package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;


public class personal extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_layout);
        findView();
    }

    private void findView() {
        final EditText edit_name = (EditText) findViewById(R.id.edit_name);
        final EditText edit_age = (EditText) findViewById(R.id.edit_age);
        final EditText edit_height = (EditText) findViewById(R.id.edit_height);
        final EditText edit_weight = (EditText) findViewById(R.id.edit_weight);
    }
}
