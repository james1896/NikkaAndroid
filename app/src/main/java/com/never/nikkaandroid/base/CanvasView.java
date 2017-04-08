package com.never.nikkaandroid.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.never.nikkaandroid.R;

/**
 * Created by toby on 05/04/2017.
 */

public class CanvasView extends View {
    Paint paint;
    Context context;
    int height;

    public CanvasView(Context context, int height) {
        super(context);
       init(context);
        this.height = height;

//        创建的非Activity类引用getResources()方法问题的解决方法

        Log.e("screen","width"+context.getResources().getDisplayMetrics().widthPixels);
        Log.e("screen","height"+context.getResources().getDisplayMetrics().heightPixels);
    }
    private void init(Context context){
        paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.me_headerview));
        paint.setAntiAlias(true);
        this.context = context;

    }
    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    //必需在这个版本下 才会调用这个方法
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    //view  大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // LinearGradient 第一个参数第二个参数为 起始位置x,y  三四参数为终点位置x,y。
        // 如果x不变则为y轴渐变， y不变则为x轴渐变
        // 第五个参数为颜色渐变，此处为红色渐变为绿色
        // 第七个参数为渐变次数，可repeat
//        Shader mShader=new LinearGradient(0, 500, 0, 0,
//                new int[]{Color.RED,Color.GREEN},
//                null,Shader.TileMode.CLAMP);
//
//
//        paint.setShader(mShader);


        Path path5=new Path();
        path5.moveTo(0, 0);
        path5.lineTo(context.getResources().getDisplayMetrics().widthPixels,0);
        path5.lineTo(context.getResources().getDisplayMetrics().widthPixels,550);
        path5.lineTo(0, 420);
        path5.close();
        canvas.drawPath(path5, paint);
    }
}
