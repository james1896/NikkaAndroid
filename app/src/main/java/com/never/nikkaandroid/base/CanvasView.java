package com.never.nikkaandroid.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.never.nikkaandroid.R;

/**
 * Created by toby on 05/04/2017.
 */

public class CanvasView extends View{
    private Paint backgroundPaint;
    private Paint textPaint;
    private Context context;
    private int height;
    private int sreenWidth;
    private float points;
    private String userName;
    private String subTitle;


    private int leftHeight = 420;
    private int rightHeight = 550;

    private String text = "toby是的dgdjsahfjdhalkfkjdsaffdsafdsafdsafdsafdsa";

    public CanvasView(Context context, int height,float points,String userName,String subTitle) {
        super(context);
       init(context);
        this.height = height;
        this.points = points;
        this.userName = userName;
        this.subTitle = subTitle;
        this.sreenWidth = context.getResources().getDisplayMetrics().widthPixels;
        this.leftHeight = sreenWidth / 3;
    }
    private void init(Context context){

        this.context = context;
        initBackgroundPaint();
        initTextPaint();

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



    public void refresh(String text){
        this.text  = text;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);

        rotateCanvas(canvas);

        drawText(canvas);
    }

    private void rotateCanvas(Canvas canvas){
        float tanX = (float) (rightHeight-leftHeight)/sreenWidth;
        tanX = (float) Math.atan(tanX);
        tanX = (float) (180 / Math.PI * tanX);
        canvas.save();
        canvas.rotate(tanX);
    }
    private void initBackgroundPaint(){
        backgroundPaint = new Paint();
        backgroundPaint.setColor(context.getResources().getColor(R.color.me_headerview));
        backgroundPaint.setAntiAlias(true);
    }

    private void initTextPaint(){
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(46);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setColor(Color.BLUE);
    }
    private void drawText(Canvas canvas){
        float length = textPaint.measureText(text);

        float x = sreenWidth - 60 - length;
        float y = 300;

        canvas.drawText(text, x, y, textPaint);

        canvas.restore();
    }


    private void drawBackground(Canvas canvas){
        Path path5=new Path();
        path5.moveTo(0, 0);
        path5.lineTo(context.getResources().getDisplayMetrics().widthPixels,0);
        path5.lineTo(context.getResources().getDisplayMetrics().widthPixels,rightHeight);
        path5.lineTo(0, leftHeight);
        path5.close();
        canvas.drawPath(path5, backgroundPaint);
    }

}
