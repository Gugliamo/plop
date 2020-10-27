package com.thestall.splash;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
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

        //initializing logo bitmap
        Bitmap logo = BitmapFactory.decodeResource(getResources(),R.drawable.pngfuel_com);

        //changing the size of the logo while preserving the aspect ratio
        float aspectRatio = logo.getWidth()/(float) logo.getHeight();
        int width=400;
        int height = Math.round(width/aspectRatio);
        logo = Bitmap.createScaledBitmap(logo, width, height, false);

        //getting center of width
        float wLogoPos = (w-logo.getWidth())/2;
        //getting slightly higher than centre height
        float hLogoPos = (h-logo.getHeight())/4;

        //drawing the logo in the middle of the canvas
        canvas.drawBitmap(logo,wLogoPos,hLogoPos,null);

        //Text section under the logo
        //PLOP text
        Paint plopText = new Paint(Paint.ANTI_ALIAS_FLAG);
        Typeface plopTextType = Typeface.create(Typeface.DEFAULT,Typeface.BOLD);
        plopText.setTypeface(plopTextType);
        plopText.setTextSize(140);
        plopText.setTextAlign(Paint.Align.CENTER);

        canvas.drawText("PLOP", w/2, hLogoPos+600, plopText);

        //Toilet Radar text
        Paint toiletText = new Paint(Paint.ANTI_ALIAS_FLAG);
        //Typeface toiletTextType = Typeface.create(Typeface.DEFAULT,Typeface.BOLD);
        //toiletText.setTypeface(toiletTextType);
        toiletText.setTextSize(100);
        toiletText.setTextAlign(Paint.Align.CENTER);

        canvas.drawText("Toilet Radar", w/2, hLogoPos+700, toiletText);
    }
}
