package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class personal_display extends Activity {

    SQLiteDatabase dbex;
    TextView tv_name, tv_age, tv_height, tv_weight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_display_layout);
        openDB();
        findview();
    }

    private void openDB() {
        DatabaseHelper helper = new DatabaseHelper(this);
        dbex = helper.getWritableDatabase();
    }

    private void findview() {
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_age = (TextView) findViewById(R.id.tv_age);
        tv_height = (TextView) findViewById(R.id.tv_height);
        tv_weight = (TextView) findViewById(R.id.tv_weight);
        Button btn_set = (Button) findViewById(R.id.btn_set);

        if(check() == 0)
            change();

        btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set();
            }
        });
    }

    private void set() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        alert.setTitle("設定個人資料");
        // 使用你設計的layout
        final View inputView = inflater.inflate(R.layout.personal_layout, null);
        alert.setView(inputView);
        final EditText edit_name = (EditText) inputView.findViewById(R.id.edit_name);
        final EditText edit_age = (EditText) inputView.findViewById(R.id.edit_age);
        final EditText edit_height = (EditText) inputView.findViewById(R.id.edit_height);
        final EditText edit_weight = (EditText) inputView.findViewById(R.id.edit_weight);

        alert.setNegativeButton("新增 / 修改", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String name = edit_name.getText().toString().trim();
                String age = edit_age.getText().toString().trim();
                String height = edit_height.getText().toString().trim();
                String weight = edit_weight.getText().toString().trim();

                if (name.length() <= 0 || age.length() <= 0 || height.length() <= 0 || weight.length() <= 0) {
                    Toast.makeText(personal_display.this, "請確認每個欄位都已輸入", Toast.LENGTH_SHORT).show();
                } else {
                    if(check() == 1) {
                        insert(name, age, height, weight);
                        change();
                    }
                    else if(check() == 0){
                        updata(name, age, height, weight);
                        change();
                    }
                }
            }
        });
        alert.show();
    }

    public void insert(String name, String age, String height, String weight) {
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("age", age);
        cv.put("height", height);
        cv.put("weight", weight);
        long check = dbex.insert("personal_table", null, cv);
        if (check > 0) {
            Toast.makeText(personal_display.this, "新增成功", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(personal_display.this, "NO", Toast.LENGTH_SHORT).show();
    }

    public void updata(String name, String age, String height, String weight) {
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("age", age);
        cv.put("height", height);
        cv.put("weight", weight);
        long check = dbex.update("personal_table", cv, null, null);
        if (check > 0) {
            Toast.makeText(personal_display.this, "修改成功", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(personal_display.this, "NO", Toast.LENGTH_SHORT).show();
    }

    public void change() {
        String[] colum = {"name", "age", "height", "weight"};
        Cursor cursor = dbex.query("personal_table", colum, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                tv_name.setText(cursor.getString(0));
                tv_age.setText(cursor.getString(1) + " 歲");
                tv_height.setText(cursor.getString(2) + " 公分");
                tv_weight.setText(cursor.getString(3) + " 公斤");
            }
        }
    }

    public int check(){
        int check = 1;
        String[] colum = {"name", "age", "height", "weight"};
        Cursor cursor = dbex.query("personal_table",colum,null,null,null,null,null);
        if(cursor.getCount() > 0){
            check = 0;
        }
        else {
            insert("尚未設定","尚未設定","尚未設定","尚未設定");
            check = 0;
        }
        return check;
    }
}

