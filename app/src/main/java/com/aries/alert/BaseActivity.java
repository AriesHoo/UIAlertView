package com.aries.alert;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.aries.ui.view.title.StatusBarUtil;
import com.aries.ui.view.title.TitleBarView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created: AriesHoo on 2017/7/3 16:04
 * Function: title 基类
 * Desc:
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected TitleBarView titleBar;
    protected Activity mContext;
    protected boolean mIsFirstShow = true;
    private Unbinder mUnBinder;
    protected int type = 0;
    protected boolean isWhite = true;

    protected abstract void setTitleBar();

    protected boolean isShowLine() {
        return true;
    }

    @LayoutRes
    protected abstract int getLayout();

    protected void loadData() {
    }

    protected void beforeSetView() {
    }

    protected void beforeInitView() {
    }

    protected abstract void initView(Bundle var1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        this.beforeSetView();
        this.setContentView(this.getLayout());
        mUnBinder = ButterKnife.bind(this);
        initTitle();
        this.beforeInitView();
        this.initView(savedInstanceState);
    }

    protected void initTitle() {
        titleBar = (TitleBarView) findViewById(R.id.titleBar);
        if (titleBar == null) {
            return;
        }
        type = StatusBarUtil.StatusBarLightMode(mContext);
        if (type <= 0) {//无法设置白底黑字
            //titleBar.setStatusAlpha(102);//5.0 半透明模式alpha-102
            titleBar.setImmersible(mContext,true,false);
        }
        titleBar.setTitleMainText(mContext.getClass().getSimpleName());
        setTitleBar();
        titleBar.setOnLeftTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setTitleLine(isShowLine());
    }

    public void setTitleLine(boolean enable) {
        titleBar.setDividerVisible(enable);
    }

    public View getRootView() {
        return ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }

    protected void onResume() {
        if (this.mIsFirstShow) {
            this.mIsFirstShow = false;
            this.loadData();
        }

        super.onResume();
    }

}
