package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

//import static com.example.myapplication.DatabaseHelper.table_name;

public class Record_Fragment  extends Fragment {

    Bitmap bmp_list;
    private DatabaseHelper db = null;
    private static Toast toast;
    SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String today = sDateFormat.format(new java.util.Date());
    SQLiteDatabase dbex;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View RecordLayout = inflater.inflate(R.layout.record_layout, container, false );
        CalendarView calendarView = (CalendarView) RecordLayout.findViewById(R.id.calendar);
        TextView all_cal = (TextView) RecordLayout.findViewById(R.id.all_cal);
        TextView all_distance = (TextView) RecordLayout.findViewById(R.id.all_distance);
        TextView all_time = (TextView) RecordLayout.findViewById(R.id.all_time);
        Button btn = (Button) RecordLayout.findViewById(R.id.btn);

        openDB();

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),screenshot.class);
                Record_Fragment.this.startActivity(intent);
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String date = year + "年" + (month + 1) + "月" + dayOfMonth + "日";
                getBitmap(year,month,dayOfMonth);
                makeTextAndShow(getActivity(), date, Toast.LENGTH_LONG);

            }
        });
        return  RecordLayout;
    }

    //顯示選取日期
    private void makeTextAndShow(final Context context, final String text, final int duration) {
        if (toast == null) {
            toast = Toast.makeText(context,text,duration);
        }
        toast.setText(text);
        toast.show();
    }

    //顯示紀錄圖片
    public void show_image(Bitmap bit) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setImageBitmap(bit);
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.setView(imageView);
        dialog.show();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        //取得系統狀態列高度
        Rect mRect = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(mRect);
        int mStatusBarHeight = mRect.top;
        params.width = (int)((getActivity().getWindowManager().getDefaultDisplay().getWidth())*0.7);
        params.height = (int)((getActivity().getWindowManager().getDefaultDisplay().getHeight()-mStatusBarHeight)*0.7);
        dialog.getWindow().setAttributes(params);
    }

    //開啟SQL
    private void openDB(){
        DatabaseHelper helper = new DatabaseHelper(getActivity());
        dbex = helper.getWritableDatabase();
    }

    //查詢並轉換
    public void getBitmap(int year, int month, int day){
        bmp_list = null;
        String string = null;
        String[] colum = {"date","base"};
        Cursor cursor = dbex.query("table_name", colum, "date", null,null,null,null);
        int i = 0;
        int a = cursor.getCount();

        if(month < 9)
            string = year + "-0" + (month+1) + "-0" + day;
        else
            string = year + "-" + (month+1) + "-0" + day;

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
                do {
                    if ((cursor.getString(0)).equals(string) == true) {
                        byte bytes[] = Base64.decode(cursor.getString(1), Base64.DEFAULT);
                        bmp_list = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        show_image(bmp_list);
                        i += 1;
                    }
                }while (cursor.moveToNext());
        }
        if(i == 0) {
            AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
            dialog.setMessage("這天沒運動唷~");
            dialog.show();
        }
        cursor.close();
    }
}