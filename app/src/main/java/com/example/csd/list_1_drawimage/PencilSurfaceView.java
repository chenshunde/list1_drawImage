package com.example.csd.list_1_drawimage;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PencilSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private boolean mIsRunning;

    public PencilSurfaceView(Context context) {
        this(context , null);
    }

    public PencilSurfaceView(Context context, AttributeSet attrs) {
        this(context , attrs , 0);
    }

    public PencilSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mHolder = getHolder();
        mHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        mIsRunning = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsRunning = false;

    }

    @Override
    public void run() {

        long start = System.currentTimeMillis();
        while (mIsRunning){
            draw();
        }

    }

    private void draw(){
        mCanvas = mHolder.lockCanvas();
        if(mCanvas!= null){
            try {
                //使用获得的canvas做具体的绘制
            }catch (Exception e){
                e.printStackTrace();
            }finally{
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
