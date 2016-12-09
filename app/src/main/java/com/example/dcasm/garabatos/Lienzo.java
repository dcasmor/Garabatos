package com.example.dcasm.garabatos;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;

public class Lienzo extends View {

    private Path drawPath;
    private Paint drawPaint, canvasPaint;
    private Canvas canvasDraw;
    private Bitmap canvasBitmap;
    private int g = 15, r = 0, v = 0, a = 0;

    public Lienzo(Context context, AttributeSet attrs) {
        super(context, attrs);
        linea();
    }

    public void linea() {
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(Color.rgb(r, v, a));
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(g);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    public void limpiar() { drawPath.reset(); }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvasDraw = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    public int getGrosor() { return g/5 - 1; }
    public int getRojo() { return r; }
    public int getVerde() { return v; }
    public int getAzul() { return a; }

    public void setLinea(int g, int r, int v, int a) {
        this.g = g;
        this.r = r;
        this.v = v;
        this.a = a;
    }
}
