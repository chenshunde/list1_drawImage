package com.example.csd.list_1_drawimage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{


    private Paint paint;
    private SurfaceHolder mHolder;
    private boolean isDraw = false;
    private MyThread mThread;

    public MySurfaceView(Context context) {
        this(context , null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
       this(context , attrs , 0);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        createPaint();

        mHolder = getHolder();
        mHolder.addCallback(this);
    }

    private void createPaint() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE); //画边框
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        mThread = new MyThread();
        isDraw = true;
        mThread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDraw = false;

        //join方法，阻塞线程，只有当当前线程执行完，才会执行其他线程的方法
        try {
            mThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    class  MyThread extends Thread{
        private int radius = 10;
        @Override
        public void run() {
            super.run();
           while(isDraw){
               Log.i("tag" , "run:"+Thread.currentThread().getName());
               synchronized (mHolder){
                   Canvas canvas = mHolder.lockCanvas();
                   //第一次进入和退出程序时，canvas为空
                   if(canvas != null){
                       canvas.drawCircle(100 , 100 , radius , paint);
                       radius +=10;
                       if(radius >70){
                           radius +=3;
                       }

                       SystemClock.sleep(50);
                       mHolder.unlockCanvasAndPost(canvas);
                   }
               }
           }
        }
    }
}
