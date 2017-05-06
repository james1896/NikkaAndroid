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
import com.never.nikkaandroid.venv.CommonUtils;

/**
 * Created by toby on 05/04/2017.
 */

public class CanvasView extends View{

    private Paint textPaint;
    private Paint bitmapPaint;
    private Paint userNamePaint;
    private Paint backgroundPaint;

    private Context context;
    private int height;
    private int sreenWidth;
    private float cellPath;
    private float leftHeight = 444;
    private float rightHeight = 544;
    private float userImg_left;
    private float userImg_top;
    private String text = "";
    //
    private float points;
    private String userName;
    private String subTitle;
    private int imageId;



    public void setuserImageId(@DrawableRes int imageId){
        this.imageId = imageId;
        invalidate();
    }
    public void setUserName(String userName){
        this.userName = userName;
        invalidate();
    }

    public void refreshPoint(float point){
        this.text  = "当前积分: " + point;
        invalidate();
    }




    public static float dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public CanvasView(Context context, int height,float points,String userName,String subTitle, int imageId) {
        super(context);

        this.context = context;
        this.cellPath = 52f;
        this.imageId = imageId;
        this.height = height;
        this.points = points;
        this.userName = userName;
        this.subTitle = subTitle;
        this.leftHeight = CommonUtils.getWindowHeight((Activity) context)/8*3-150;
        this.rightHeight = CommonUtils.getWindowHeight((Activity) context)/8*3;
        this.sreenWidth = (int) CommonUtils.getWindowWidth((Activity) context);

        init(context);

    }

    //init

    private void init(Context context){
        initBackgroundPaint();
        initTextPaint();
        initBitmapPaint();
        initUserNamePaint();

    }

    private void initUserNamePaint(){
        userNamePaint = new Paint();
        userNamePaint.setAntiAlias(true);
        userNamePaint.setTextSize(dip2px(this.context,16));
        userNamePaint.setTypeface(Typeface.DEFAULT_BOLD);
        userNamePaint.setTextAlign(Paint.Align.LEFT);
        userNamePaint.setColor(Color.BLACK);
    }

    private void initBackgroundPaint(){
        backgroundPaint = new Paint();
        backgroundPaint.setColor(context.getResources().getColor(R.color.theme_pink));
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


    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        drawBackground(canvas);
        drawPicture(canvas);
        rotateCanvas(canvas);
        drawUserName(canvas);
//        drawText(canvas);
    }

    //画图片
    private void drawPicture(Canvas canvas){
        if(imageId == 0) return;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageId);

        //高度需要再减去 图片高度的一半
        this.userImg_left = cellPath ;

        this.userImg_top = this.leftHeight+100*(dip2px(this.context,cellPath)/CommonUtils.getWindowHeight((Activity) this.context))-bitmap.getHeight()/2;
        canvas.drawBitmap(bitmap,dip2px(this.context,cellPath) ,this.userImg_top , bitmapPaint);
    }

    private void drawUserName(Canvas canvas){
        float length = userNamePaint.measureText(this.userName);
//        float x = sreenWidth - 60 - length;
//        float y = 420;
        canvas.drawText(this.userName, this.userImg_left, this.userImg_top-100, userNamePaint);

//        canvas.restore();
    }
    //画斜线
    private void rotateCanvas(Canvas canvas){
        //画布翻转
        float tanX = (rightHeight-leftHeight)/sreenWidth;
        Log.e("tanX",""+tanX);
        tanX = (float) Math.atan(tanX);
        tanX = (float) (180 / Math.PI * tanX);
        canvas.save();
        canvas.rotate(tanX);
        //绘制文字
        float length = textPaint.measureText(text);
        float x = sreenWidth - dip2px(this.context,this.cellPath-20.0f) - length;

//        Log.e("y", String.valueOf(this.rightHeight-100*(dip2px(this.context,60.0f)/ CommonUtils.getWindowWidth((Activity) this.context))) +"||||"+ this.rightHeight+"dip2px"+dip2px(this.context,60.0f));
        float y = this.leftHeight-100*(dip2px(this.context,this.cellPath+20.0f)/CommonUtils.getWindowHeight((Activity) this.context));
        canvas.drawText(text, x, y, textPaint);
        canvas.restore();
    }
    //

    private void drawText(Canvas canvas,float x,float y,String text){
        float length = textPaint.measureText(text);
//        float x = sreenWidth - 60 - length;
//        float y = 420;
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
