package com.example.myapplication;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class main_a extends Activity implements View.OnClickListener{

    private Google_map_Fragment googleMapFragment;
    private Record_Fragment recordFragment;
    private Achievement_Fragment achievementFragment;
    private Setting_Fragment settingFragment;

    private View google_map_layout;
    private View record_layout;
    private View achievement_layout;
    private View setting_layout;

    private TextView Gmap_text;
    private TextView recordtext;
    private TextView achievementtext;
    private TextView settingtext;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super .onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        // 初始化佈局元素
        initViews();
        fragmentManager = getFragmentManager();
        // 第一次啟動時選中第0個tab
        setTabSelection( 0 );
    }

    private void initViews(){
        google_map_layout = findViewById(R.id.google_map_layout);
        record_layout = findViewById(R.id.record_layout);
        achievement_layout = findViewById(R.id.achievement_layout);
        setting_layout = findViewById(R.id.setting_layout);
        Gmap_text = (TextView) findViewById(R.id.Gmap_text);
        recordtext = (TextView)findViewById(R.id.record_text);
        achievementtext  = (TextView)findViewById(R.id.achieve_text);
        settingtext  = (TextView)findViewById(R.id.settingtext);
        google_map_layout.setOnClickListener(this);
        record_layout.setOnClickListener(this);
        achievement_layout.setOnClickListener(this);
        setting_layout.setOnClickListener(this);
    }

    @Override
    public  void  onClick(View v) {
        switch  (v.getId()) {
            case  R.id.google_map_layout:
                // 當點擊了消息tab時，選中第1個tab
                setTabSelection( 0 );
                break ;
            case  R.id.record_layout:
                // 當點擊了聯繫人tab時，選中第2個tab
                setTabSelection( 1 );
                break ;
            case  R.id.achievement_layout:
                // 當點擊了動態tab時，選中第3個tab
                setTabSelection( 2 );
                break ;
            case  R.id.setting_layout:
                // 當點擊了設置tab時，選中第4個tab
                setTabSelection( 3 );
                break ;
            default :
                break ;
        }
    }

    private  void  setTabSelection( int  index) {
        // 每次選中之前先清楚掉上次的選中狀態
        clearSelection();
        // 開啟一個Fragment事務
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隱藏掉所有的Fragment，以防止有多個Fragment顯示在界面上的情況
        hideFragments(transaction);
        switch  (index) {
            case  0 :
                // 當點擊了消息tab時，改變控件的圖片和文字顏色
                Gmap_text.setTextColor(Color.BLUE);
                if  (googleMapFragment ==  null ) {
                    // 如果MessageFragment為空，則創建一個並添加到界面上
                    googleMapFragment =  new Google_map_Fragment();
                    transaction.add(R.id.content, googleMapFragment);
                }  else  {
                    // 如果MessageFragment不為空，則直接將它​​顯示出來
                    transaction.show(googleMapFragment);
                }
                break ;
            case  1 :
                // 當點擊了聯繫人tab時，改變控件的圖片和文字顏色
                recordtext.setTextColor(Color.BLUE);
                if  (recordFragment ==  null ) {
                    // 如果ContactsFragment為空，則創建一個並添加到界面上
                    recordFragment =  new Record_Fragment();
                    transaction.add(R.id.content, recordFragment);
                }  else  {
                    // 如果ContactsFragment不為空，則直接將它​​顯示出來
                    transaction.show(recordFragment);
                }
                break ;
            case  2 :
                // 當點擊了動態tab時，改變控件的圖片和文字顏色
                achievementtext.setTextColor(Color.BLUE);
                if  (achievementFragment ==  null ) {
                    // 如果NewsFragment為空，則創建一個並添加到界面上
                    achievementFragment =  new Achievement_Fragment();
                    transaction.add(R.id.content, achievementFragment);
                }  else  {
                    // 如果NewsFragment不為空，則直接將它​​顯示出來
                    transaction.show(achievementFragment);
                }
                break ;
            case  3 :
            default :
                // 當點擊了設置tab時，改變控件的圖片和文字顏色
                settingtext.setTextColor(Color.BLUE);
                if  (settingFragment ==  null ) {
                    // 如果SettingFragment為空，則創建一個並添加到界面上
                    settingFragment =  new Setting_Fragment();
                    transaction.add(R.id.content, settingFragment);
                }  else  {
                    // 如果SettingFragment不為空，則直接將它​​顯示出來
                    transaction.show(settingFragment);
                }
                break ;
        }
        transaction.commit();
    }

    private void clearSelection(){
        Gmap_text.setTextColor(Color.parseColor("#82858b"));
        recordtext.setTextColor(Color.parseColor("#82858b"));
        achievementtext.setTextColor(Color.parseColor("#82858b"));
        settingtext.setTextColor(Color.parseColor("#82858b"));
    }

    private void hideFragments(FragmentTransaction transaction){
        if (googleMapFragment != null){
            transaction.hide(googleMapFragment);
        }
        if (recordFragment != null){
            transaction.hide(recordFragment);
        }
        if (achievementFragment != null){
            transaction.hide(achievementFragment);
        }
        if (settingFragment != null){
            transaction.hide(settingFragment);
        }
    }
}
