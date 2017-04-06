package com.never.nikkaandroid.base;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.never.nikkaandroid.R;

/**
 * Created by toby on 06/04/2017.
 */

public class TabLayoutItemView extends LinearLayout {
    public TabLayoutItemView(Context context,String text,int resid) {
        super(context);
        View.inflate(getContext(), R.layout.tablayout_item, this);

        ((ImageView)findViewById(R.id.imageview)).setImageResource(resid);
        ((TextView)findViewById(R.id.textview)).setText(text);
    }

    public TabLayoutItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View.inflate(getContext(), R.layout.tablayout_item, this);
    }

    public TabLayoutItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(getContext(), R.layout.tablayout_item, this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TabLayoutItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        View.inflate(getContext(), R.layout.tablayout_item, this);
    }
}
