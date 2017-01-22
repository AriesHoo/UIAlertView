package com.aries.ui.widget.alert;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created: AriesHoo on 2017-01-19 14:16
 * Function: 自定义AlertDialog 弹出提示框
 * Desc:
 */
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("InflateParams")
public class UIAlertView {
    private Context context;
    private AlertDialog dialog;
    private TextView txt_title;
    private TextView txt_msg;
    //addView 父容器
    private LinearLayout dialog_Group;
    private LinearLayout linearLayoutGroup;
    private TextView btn_left;
    private TextView btn_middle;
    private TextView btn_right;
    private View mViewLine;
    private View mViewLineRight;
    private View mViewLineHorizontal;
    private boolean showTitle = false;
    private boolean showMsg = false;
    private boolean showLayout = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;
    private boolean showNeuBtn = false;

    private int gravity = Gravity.CENTER;
    private Window window;
    private WindowManager.LayoutParams lp;

    public UIAlertView(Context context) {
        this.context = context;
    }

    public UIAlertView builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.layout_alert_view, null);
        // 获取自定义Dialog布局中的控件
        txt_title = (TextView) view.findViewById(R.id.tv_titleAlertView);
        txt_title.setVisibility(View.GONE);
        txt_msg = (TextView) view.findViewById(R.id.tv_msgAlertView);
        txt_msg.setVisibility(View.GONE);
        dialog_Group = (LinearLayout) view.findViewById(R.id.lLayout_viewAlertView);
        dialog_Group.setVisibility(View.GONE);
        btn_left = (TextView) view.findViewById(R.id.tv_leftAlertView);
        btn_left.setVisibility(View.GONE);
        btn_middle = (TextView) view.findViewById(R.id.tv_middleAlertView);
        btn_middle.setVisibility(View.GONE);
        btn_right = (TextView) view.findViewById(R.id.tv_rightAlertView);
        btn_right.setVisibility(View.GONE);
        mViewLine = view.findViewById(R.id.v_lineAlertView);
        mViewLine.setVisibility(View.GONE);
        mViewLineHorizontal = view.findViewById(R.id.v_lineHorizontalAlertView);
        mViewLineHorizontal.setVisibility(View.GONE);
        mViewLineRight = view.findViewById(R.id.v_lineRightAlertView);
        mViewLineRight.setVisibility(View.GONE);
        linearLayoutGroup = (LinearLayout) view.findViewById(R.id.lLayout_groupAlertView);

        // 定义Dialog布局和参数
        dialog = new AlertDialog.Builder(context, R.style.AlertViewDialogStyle).create();
        dialog.show();
        dialog.setContentView(view);
        window = dialog.getWindow();
        lp = window.getAttributes();
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {
                dialog_Group.removeAllViews();
            }
        });
        return this;
    }

    public AlertDialog getDialog() {
        return dialog;
    }

    /**
     * 设置窗口透明度
     *
     * @param alpha
     * @return
     */
    public UIAlertView setAlpha(float alpha) {
        lp.alpha = alpha;// 透明度
        window.setAttributes(lp);
        return this;
    }

    /**
     * 设置背景黑暗度
     *
     * @param dimAmount
     * @return
     */
    public UIAlertView setDimAmount(float dimAmount) {
        lp.dimAmount = dimAmount;// 黑暗度
        window.setAttributes(lp);
        return this;
    }

    public UIAlertView setContentView(int layoutResID) {
        dialog.show();
        dialog.setContentView(layoutResID);
        return this;
    }

    public UIAlertView setBackgroundResource(@DrawableRes int resId) {
        linearLayoutGroup.setBackgroundResource(resId);
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public UIAlertView setBackground(Drawable background) {
        linearLayoutGroup.setBackground(background);
        return this;
    }

    /**
     * 是否设置点击dialog区域外，dialog消失
     *
     * @param cancel
     */
    public void setCanceledOnTouchOutside(boolean cancel) {
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(cancel);
        }
    }

    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public UIAlertView setTitle(String title) {
        if (!title.isEmpty()) {
            showTitle = true;
            txt_title.setText(Html.fromHtml(title));
        }
        return this;
    }

    /**
     * 设置title textSize参考 TextView.setTextSize(unit, textSize)方法
     *
     * @param unit
     * @param textSize
     * @return
     */
    public UIAlertView setTitleTextSize(int unit, float textSize) {
        txt_title.setTextSize(unit, textSize);
        return this;
    }

    /**
     * 设置Message textSize参考 TextView.setTextSize(unit, textSize)方法
     *
     * @param unit
     * @param textSize
     * @return
     */
    public UIAlertView setMessageTextSize(int unit, float textSize) {
        txt_msg.setTextSize(unit, textSize);
        return this;
    }

    /**
     * 设置message最低高度
     *
     * @param minHeight
     * @return
     */
    public UIAlertView setMessageMinHeight(final int minHeight) {
        txt_msg.setMinimumHeight(minHeight);
        return this;
    }

    /**
     * 设置Button textSize参考 TextView.setTextSize(unit, textSize)方法
     *
     * @param unit
     * @param textSize
     * @return
     */
    public UIAlertView setButtonTextSize(int unit, float textSize) {
        btn_left.setTextSize(unit, textSize);
        btn_right.setTextSize(unit, textSize);
        return this;
    }

    public UIAlertView setTitle(int title) {
        return setTitle(context.getString(title));
    }

    /**
     * 设置提示语
     *
     * @param msg
     * @return
     */
    public UIAlertView setMessage(String msg) {
        showMsg = true;
        txt_msg.setText(msg);
        if (msg.contains("<br />")) {
            txt_msg.setText(Html.fromHtml(msg));
        }
        txt_msg.post(new Runnable() {

            @Override
            public void run() {
                if (txt_msg.getLineCount() > 4) {
                    txt_msg.setMaxWidth((int) context.getResources()
                            .getDimension(R.dimen.alert_max_width));
                }
            }
        });
        return this;
    }

    public UIAlertView setMessage(String msg, int gravity) {
        this.gravity = gravity;
        return setMessage(msg);
    }

    public UIAlertView setMessage(int msg, int gravity) {
        this.gravity = gravity;
        return setMessage(msg);
    }

    public UIAlertView setMessage(int msg) {
        return setMessage(context.getString(msg));
    }


    /**
     * 添加视图
     *
     * @param view
     * @return
     */
    public UIAlertView setView(View view) {
        showLayout = true;
        if (view == null) {
            showLayout = false;
        } else {
            dialog_Group.addView(view,
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        }
        return this;
    }

    /**
     * 师傅返回键弹框可消失
     *
     * @param cancel
     * @return
     */
    public UIAlertView setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    /**
     * 设置左边按钮
     *
     * @param text
     * @param listener
     * @return
     */
    public UIAlertView setNegativeButton(String text,
                                         final DialogInterface.OnClickListener listener) {
        showNegBtn = true;
        btn_left.setText(Html.fromHtml(text));
        btn_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                }
                dialog.dismiss();
            }
        });
        return this;
    }

    public UIAlertView setNegativeButtonBackground(Drawable background) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            btn_left.setBackground(background);
        }
        return this;
    }

    /**
     * 修改左边button背景
     *
     * @param resId
     * @return
     */
    public UIAlertView setNegativeButtonBackgroundResource(@DrawableRes int resId) {
        btn_left.setBackgroundResource(resId);
        return this;
    }

    public UIAlertView setNegativeButtonBackgroundColor(@ColorInt int color) {
        btn_left.setBackgroundColor(color);
        return this;
    }

    public UIAlertView setNeutralButton(String text,
                                        final DialogInterface.OnClickListener listener) {
        showNeuBtn = true;
        btn_middle.setText(Html.fromHtml(text));
        btn_middle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(dialog, DialogInterface.BUTTON_NEUTRAL);
                }
                dialog.dismiss();
            }
        });
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public UIAlertView setPositiveButtonBackground(Drawable background) {
        btn_left.setBackground(background);
        return this;
    }

    /**
     * 设置右边button背景样式
     *
     * @param resId
     * @return
     */
    public UIAlertView setPositiveButtonBackgroundResource(@DrawableRes int resId) {
        btn_left.setBackgroundResource(resId);
        return this;
    }

    public UIAlertView setPositiveButtonBackgroundColor(@ColorInt int color) {
        btn_left.setBackgroundColor(color);
        return this;
    }

    public UIAlertView setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return false;
            }
        });
        return this;
    }

    public UIAlertView setOnDismissListener(OnDismissListener onDismissListener) {
        dialog.setOnDismissListener(onDismissListener);
        return this;
    }

    public UIAlertView setNegativeButton(int text,
                                         final DialogInterface.OnClickListener listener) {
        return setNegativeButton(context.getString(text), listener);
    }

    public UIAlertView setPositiveButton(String text,
                                         final DialogInterface.OnClickListener listener) {
        return setPositiveButton(text, listener, true);
    }

    public UIAlertView setPositiveButton(String text,
                                         final DialogInterface.OnClickListener listener, final boolean isDismiss) {
        showPosBtn = true;
        btn_right.setText(Html.fromHtml(text));
        btn_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                }
                if (isDismiss)
                    dialog.dismiss();
            }
        });
        return this;
    }

    /**
     * 设置右边按钮
     *
     * @param text
     * @param listener
     * @return
     */
    public UIAlertView setPositiveButton(int text,
                                         final DialogInterface.OnClickListener listener) {
        return setPositiveButton(context.getString(text), listener);
    }

    public UIAlertView setPositiveButton(int text,
                                         final DialogInterface.OnClickListener listener, final boolean isDismiss) {
        return setPositiveButton(context.getString(text), listener, isDismiss);
    }

    private void setLayout() {
        if (showTitle) {
            txt_title.setVisibility(View.VISIBLE);
        } else {
            linearLayoutGroup.setGravity(Gravity.CENTER);
            txt_msg.setGravity(Gravity.CENTER);
        }
        linearLayoutGroup.setGravity(gravity);
        if (showMsg) {
            txt_msg.setVisibility(View.VISIBLE);
        }
        if (showLayout) {
            dialog_Group.setVisibility(View.VISIBLE);
        }
        if (showPosBtn || showNegBtn || showNeuBtn) {//都没有
            mViewLineHorizontal.setVisibility(View.VISIBLE);
        }
        if (!showPosBtn && showNegBtn && !showNeuBtn) {//左一个
            btn_left.setVisibility(View.VISIBLE);
            btn_left.setBackgroundResource(R.drawable.alert_btn_single_selector);
            mViewLine.setVisibility(View.GONE);
            mViewLineRight.setVisibility(View.GONE);
        } else if (showPosBtn && !showNegBtn & !showNeuBtn) {//右一个
            btn_right.setVisibility(View.VISIBLE);
            btn_right.setBackgroundResource(R.drawable.alert_btn_single_selector);
            mViewLine.setVisibility(View.GONE);
            mViewLineRight.setVisibility(View.GONE);
        } else if (!showPosBtn && !showNegBtn & showNeuBtn) {//中一个
            btn_middle.setVisibility(View.VISIBLE);
            btn_middle.setBackgroundResource(R.drawable.alert_btn_single_selector);
            mViewLine.setVisibility(View.GONE);
            mViewLineRight.setVisibility(View.GONE);
        } else if (showPosBtn && showNegBtn && !showNeuBtn) {//左右两个
            btn_right.setVisibility(View.VISIBLE);
            btn_right.setBackgroundResource(R.drawable.alert_btn_right_selector);
            btn_left.setVisibility(View.VISIBLE);
            btn_left.setBackgroundResource(R.drawable.alert_btn_left_selector);
            mViewLine.setVisibility(View.VISIBLE);
            mViewLineRight.setVisibility(View.GONE);
        } else if (!showPosBtn && showNegBtn && showNeuBtn) {//左中两个
            btn_middle.setVisibility(View.VISIBLE);
            btn_middle
                    .setBackgroundResource(R.drawable.alert_btn_right_selector);
            btn_left.setVisibility(View.VISIBLE);
            btn_left.setBackgroundResource(R.drawable.alert_btn_left_selector);
            mViewLine.setVisibility(View.VISIBLE);
            mViewLineRight.setVisibility(View.GONE);
        } else if (showPosBtn && !showNegBtn && showNeuBtn) {//中右两个
            btn_right.setVisibility(View.VISIBLE);
            btn_right
                    .setBackgroundResource(R.drawable.alert_btn_right_selector);
            btn_middle.setVisibility(View.VISIBLE);
            btn_middle.setBackgroundResource(R.drawable.alert_btn_left_selector);
            mViewLine.setVisibility(View.VISIBLE);
            mViewLineRight.setVisibility(View.GONE);
        } else if (showPosBtn && showNegBtn && showNeuBtn) {//三个
            btn_right.setVisibility(View.VISIBLE);
            btn_right.setBackgroundResource(R.drawable.alert_btn_right_selector);
            btn_left.setVisibility(View.VISIBLE);
            btn_left.setBackgroundResource(R.drawable.alert_btn_left_selector);
            btn_middle.setVisibility(View.VISIBLE);
            btn_middle.setBackgroundResource(R.drawable.alert_btn_middle_selector);
            mViewLine.setVisibility(View.VISIBLE);
            mViewLineRight.setVisibility(View.VISIBLE);
        }

    }

    public void show() {
        setLayout();
        if (!dialog.isShowing())
            dialog.show();
    }

    public void dismiss() {
        if (dialog.isShowing())
            dialog.cancel();
        dialog.dismiss();
    }
}
