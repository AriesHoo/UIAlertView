package com.aries.alert;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.aries.ui.widget.alert.UIAlertView;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_NEUTRAL;
import static android.content.DialogInterface.BUTTON_POSITIVE;

public class MainActivity extends AppCompatActivity {

    UIAlertView uiAlertView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_alert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uiAlertView == null) {
                    uiAlertView = new UIAlertView(MainActivity.this).builder();
                }
                uiAlertView.setTitle("UIAlertView");
                uiAlertView.setMessage("1、本次更新修复多个重大BUG\n2、新增用户反馈接口", Gravity.LEFT);
                uiAlertView.setNegativeButton("否定", onAlertClick);
                uiAlertView.setPositiveButton("肯定", onAlertClick);
                uiAlertView.setDimAmount(0.5f);
                uiAlertView.setMessageTextColor(Color.RED);
                uiAlertView.setTitleTextColor(Color.BLUE);
                uiAlertView.setNegativeButtonTextColor(Color.BLUE);
                uiAlertView.setNeutralButtonTextColor(Color.RED);
                uiAlertView.setPositiveButtonTextColor(Color.BLACK);
                uiAlertView.setNeutralButton("中性", onAlertClick);
                uiAlertView.show();
            }
        });

        findViewById(R.id.btn_alertNoTitle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uiAlertView = new UIAlertView(MainActivity.this).builder();
                uiAlertView.setMinHeight(200);
                uiAlertView.setMessage("测试无Title AlertView");
                uiAlertView.setNegativeButton("否定", onAlertClick);
                uiAlertView.setPositiveButton("肯定", onAlertClick);
                uiAlertView.setDimAmount(0.5f);
                uiAlertView.setMessageTextColor(Color.RED);
                uiAlertView.setTitleTextColor(Color.BLUE);
                uiAlertView.setNegativeButtonTextColor(Color.BLUE);
                uiAlertView.setNeutralButtonTextColor(Color.RED);
                uiAlertView.setPositiveButtonTextColor(Color.BLACK);
                uiAlertView.show();
            }
        });

        findViewById(R.id.btn_alertNoMiddle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uiAlertView = new UIAlertView(MainActivity.this).builder();
                uiAlertView.setMessage("测试无Title 两个按钮 AlertView");
                uiAlertView.setNegativeButton("否定", onAlertClick);
                uiAlertView.setPositiveButton("肯定", onAlertClick);
                uiAlertView.setDimAmount(0.5f);
                uiAlertView.setTitleTextColor(Color.BLUE);
                uiAlertView.setNegativeButtonTextColor(Color.BLUE);
                uiAlertView.setNeutralButtonTextColor(Color.RED);
                uiAlertView.setPositiveButtonTextColor(Color.BLACK);
                uiAlertView.show();
            }
        });

        findViewById(R.id.btn_alertSingle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uiAlertView = new UIAlertView(MainActivity.this).builder();
                uiAlertView.setMessage("测试无Title 单个按钮按钮 AlertView");
                uiAlertView.setNegativeButton("否定", onAlertClick);
                uiAlertView.setDimAmount(0.5f);
                uiAlertView.setTitleTextColor(Color.BLUE);
                uiAlertView.setNegativeButtonTextColor(Color.BLUE);
                uiAlertView.setNeutralButtonTextColor(Color.RED);
                uiAlertView.setPositiveButtonTextColor(Color.BLACK);
                uiAlertView.show();
            }
        });
    }

    DialogInterface.OnClickListener onAlertClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            String msg = "";
            switch (which) {
                case BUTTON_NEGATIVE:
                    msg = "否定";
                    break;
                case BUTTON_POSITIVE:
                    msg = "肯定";
                    break;
                case BUTTON_NEUTRAL:
                    msg = "中性";
                    break;
            }
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    };
}
