package com.nengxin.example.exampleproject.model.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.widget.TextView;

import com.nengxin.example.exampleproject.R;
import com.nengxin.example.exampleproject.model.util.Util;


/**
 * Created by johnzheng on 1/26/16.
 */
public class Toolbar extends android.support.v7.widget.Toolbar {
    public Toolbar(Context context) {
        super(context);
    }

    public Toolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Toolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTitle(@StringRes int resId) {
        setCenterTitle(resId);
    }

    @Override
    public void setTitle(CharSequence title) {

        setCenterTitle(title);
    }


    private void setCenterTitle(CharSequence title) {
        TextView textView = (TextView) findViewById(R.id.title);
        if (null != textView) textView.setText(title);
        else super.setTitle(title);


    }

    private void setCenterTitle(@StringRes int resId) {
        TextView textView = (TextView) findViewById(R.id.title);
        if (null != textView) textView.setText(resId);
        else super.setTitle(resId);
    }

    @Override
    public void setNavigationIcon(@DrawableRes int resId) {
        super.setNavigationIcon(resId);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //setMenuChanged();
    }

    public void setMenuChanged() {
        if (getMenu().size() == 0) {
            setToolbarTitleLayout(0);
            if (getNavigationIcon() == null)
                setToolbarTitleLayout(-48);
            else setToolbarTitleLayout(-48);
        }
        if (getMenu().size() == 1) {
            setToolbarTitleLayout(0);
            if (getNavigationIcon() == null)
                setToolbarTitleLayout(48);
            else setToolbarTitleLayout(0);
        }
        if (getMenu().size() == 2) {
            setToolbarTitleLayout(0);
            if (getNavigationIcon() == null)
                setToolbarTitleLayout(96);
            else setToolbarTitleLayout(48);
        }
    }

    public void setToolbarTitleLayout(int p) {
        TextView textView = (TextView) findViewById(R.id.title);
        LayoutParams lp = (LayoutParams) textView.getLayoutParams();
        if (p > 0)
            lp.leftMargin = Util.dip2px(getContext(), p);
        else lp.rightMargin = Util.dip2px(getContext(), Math.abs(p));
        textView.setLayoutParams(lp);
    }


}
