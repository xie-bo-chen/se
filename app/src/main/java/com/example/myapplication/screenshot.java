package com.example.myapplication;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.database.Cursor;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;

public class screenshot extends Activity{

    Bitmap bmp_list;
    Button btn_save, btn_get;
    private static Toast toast;
    ImageView mImg,img_shot;
    SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String today = sDateFormat.format(new java.util.Date());
    SQLiteDatabase dbex;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screenshot);

        btn_get = (Button) findViewById(R.id.btn_get);
        btn_save = (Button) findViewById(R.id.btn_save);
        mImg = (ImageView) findViewById(R.id.img);
        img_shot = (ImageView) findViewById(R.id.img_shot);

        openDB();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getScreenShot();
            }
        });

        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_shot.setImageBitmap(getBitmap());
            }
        });
    }

    //截圖
    public void getScreenShot() {
        //藉由View來Cache全螢幕畫面後放入Bitmap
        View mView = getWindow().getDecorView();
        mView.setDrawingCacheEnabled(true);
        mView.buildDrawingCache();
        Bitmap mFullBitmap = mView.getDrawingCache();
        //取得系統狀態列高度
        Rect mRect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(mRect);
        int mStatusBarHeight = mRect.top;
        //取得手機螢幕長寬尺寸
        int mPhoneWidth = getWindowManager().getDefaultDisplay().getWidth();
        int mPhoneHeight = getWindowManager().getDefaultDisplay().getHeight();
        //將狀態列的部分移除並建立新的Bitmap
        Bitmap mBitmap = Bitmap.createBitmap(
                mFullBitmap, 0,
                mStatusBarHeight, mPhoneWidth,
                mPhoneHeight - mStatusBarHeight);
        //將Cache的畫面清除
        mView.destroyDrawingCache();

        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        // 設置想要的大小
        int newWidth = (int)(width * 0.7);
        int newHeight = (int)(height * 0.7);
        // 計算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix參數
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的圖片
        Bitmap new_bitmap = Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, true);
        // 先把 bitmap 轉成 byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        new_bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte bytes[] = stream.toByteArray();
        // Android 2.2以上才有內建Base64，其他要自已找Library或是用Blob存入SQLite
        // 把byte變成base64
        String base64 = Base64.encodeToString(bytes, Base64.DEFAULT);
        insert(base64);

    }

    //開啟SQL
    private void openDB(){
        DatabaseHelper helper = new DatabaseHelper(this);
        dbex = helper.getWritableDatabase();
    }


    //新增
    private void insert(String base64){
        ContentValues cv = new ContentValues();
        cv.put("date", today);
        cv.put("base", base64);
        long check = dbex.insert("table_name", null, cv);
        if(check > 0){
            Toast.makeText(this, today + " - GOOD", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "NO", Toast.LENGTH_SHORT).show();
    }


    //查詢並轉換
    public Bitmap getBitmap(){
        String[] colum = {"date","base"};
        Cursor cursor = dbex.query("table_name", colum, "date",null,null,null,null);
        int count = cursor.getCount();

        if(count > 0) {
            cursor.moveToFirst();
            do{
                toast.makeText(screenshot.this, cursor.getString(0), Toast.LENGTH_SHORT).show();
                byte bytes[] = Base64.decode(cursor.getString(1), Base64.DEFAULT);
                bmp_list = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            }while(cursor.moveToNext());
        }
        else
            toast.makeText(this,"nothing in here",Toast.LENGTH_SHORT).show();

        return bmp_list;
    }


}