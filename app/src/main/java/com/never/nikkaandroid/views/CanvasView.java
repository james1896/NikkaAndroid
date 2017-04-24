package com.never.nikkaandroid.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.never.nikkaandroid.R;
import com.never.nikkaandroid.base.CommonUtils;

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

    private Paint bitmapPaint;


    private int leftHeight = 444;
    private int rightHeight = 544;

    private String text = "";

    private int imageId;
    public static float dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public CanvasView(Context context, int height,float points,String userName,String subTitle, int imageId) {
        super(context);

        leftHeight = CommonUtils.getWindowHeight((Activity) context)/8*3-100;
        rightHeight = CommonUtils.getWindowHeight((Activity) context)/8*3;

        Log.e("aaaaaa","init"+leftHeight + "bb" + rightHeight);
       init(context);
        this.imageId = imageId;
        this.height = height;
        this.points = points;
        this.userName = userName;
        this.subTitle = subTitle;
        this.sreenWidth = context.getResources().getDisplayMetrics().widthPixels;
//        this.leftHeight = sreenWidth / 3;
    }
    private void init(Context context){

        this.context = context;


        initBackgroundPaint();
        initTextPaint();
        initBitmapPaint();

    }
    private void initBitmapPaint(){
        bitmapPaint = new Paint();
        bitmapPaint.setAntiAlias(true);
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



    public void refresh(float point){
        this.text  = "当前积分: " + point;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("aaaaaa","onDraw");
        super.onDraw(canvas);
        drawBackground(canvas);
        drawPicture(canvas);
        rotateCanvas(canvas);

//        drawText(canvas);
    }

    //画图片
    private void drawPicture(Canvas canvas){
        if(imageId == 0)return;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageId);

        Log.e("height", String.valueOf(CommonUtils.getWindowHeight((Activity) this.context)));
        Log.e("weight", String.valueOf(CommonUtils.getWindowWidth((Activity) this.context)));
        Log.e("weight1", String.valueOf(context.getResources().getDisplayMetrics().widthPixels));
        //高度需要再减去 图片高度的一半
        canvas.drawBitmap(bitmap, 60, (float) (this.leftHeight+100*(dip2px(this.context,60.0f)/CommonUtils.getWindowHeight((Activity) this.context))-bitmap.getHeight()/2), bitmapPaint);

    }

    public void setuserImageId(@DrawableRes int imageId){
        this.imageId = imageId;
        invalidate();
    }
    //画斜线
    private void rotateCanvas(Canvas canvas){
        //画布翻转
        float tanX = (float) (rightHeight-leftHeight)/sreenWidth;
        Log.e("tanX",""+tanX);
        tanX = (float) Math.atan(tanX);
        tanX = (float) (180 / Math.PI * tanX);
        canvas.save();
        canvas.rotate(tanX);
        //绘制文字
        float length = textPaint.measureText(text);
        float x = sreenWidth - dip2px(this.context,60.0f-20.0f) - length;

//        Log.e("y", String.valueOf(this.rightHeight-100*(dip2px(this.context,60.0f)/ CommonUtils.getWindowWidth((Activity) this.context))) +"||||"+ this.rightHeight+"dip2px"+dip2px(this.context,60.0f));
        float y = this.leftHeight-100*(dip2px(this.context,60.0f+20.0f)/CommonUtils.getWindowHeight((Activity) this.context));
        canvas.drawText(text, x, y, textPaint);
        canvas.restore();
    }
    //
    private void initBackgroundPaint(){
        backgroundPaint = new Paint();
        backgroundPaint.setColor(context.getResources().getColor(R.color.me_headerview));
        backgroundPaint.setAntiAlias(true);
    }

    private void initTextPaint(){
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(dip2px(this.context,14));
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setColor(Color.WHITE);
    }
    private void drawText(Canvas canvas){
        float length = textPaint.measureText(text);

        float x = sreenWidth - 60 - length;
        float y = 420;

        canvas.drawText(text, x, y, textPaint);

        canvas.restore();
    }

    //画背景
    private void drawBackground(Canvas canvas){
        Path path5=new Path();
        path5.moveTo(0, 0);
        path5.lineTo(sreenWidth,0);
        path5.lineTo(sreenWidth,rightHeight);
        path5.lineTo(0, leftHeight);
        path5.close();
        canvas.drawPath(path5, backgroundPaint);
    }

}
