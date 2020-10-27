package com.thestall.splash;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class SplashView extends View {

    Paint BackGround = new Paint();
    Context MyContext;

    public SplashView(Context context){
        super(context);
        init(context);
    }

    public SplashView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }

    private void init(Context ctx){
        MyContext = ctx;
        BackGround.setStyle(Paint.Style.FILL);
        //BackGround.setColor(Color.BLACK);
        BackGround.setColor(Color.argb(255,252,136,57));
    }

    protected void onMeasure(int width, int height){
        int w = MeasureSpec.getSize(width);
        int h = MeasureSpec.getSize(height);
        setMeasuredDimension(w,h);
    }

    protected void onDraw(Canvas canvas){

        float w, h;
        w = getWidth();
        h = getHeight();

        //drawing orange background
        canvas.drawRect(0,0,w,h,BackGround);

        //drawing the plop logo
        Bitmap logo = BitmapFactory.decodeResource(getResources(),R.drawable.pngfuel_com);

        float aspectRatio = logo.getWidth()/(float) logo.getHeight();
        int width=400;
        int height = Math.round(width/aspectRatio);
        logo = Bitmap.createScaledBitmap(logo, width, height, false);

        canvas.drawBitmap(logo,(w-logo.getWidth())/2,(h-logo.getHeight())/3,null);
    }
}
