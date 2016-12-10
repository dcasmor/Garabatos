package com.example.dcasm.garabatos;

import android.content.Context;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class Lienzo extends View {

    private Path drawPath;
    private Paint drawPaint, canvasPaint;
    private Canvas canvasDraw;
    private Bitmap canvasBitmap;
    private Linea linea;
    private Circulo circulo;
    private Cuadrado cuadrado;
    private ArrayList lista;
    private int g = 15, r = 0, v = 0, a = 0, tipo = 0, posX, posY;

    public Lienzo(Context context, AttributeSet attrs) {
        super(context, attrs);
        Paint.Style estilo = Paint.Style.STROKE;
        linea = new Linea(g, r, v, a, estilo);
        circulo = new Circulo(g, r, v, a, estilo);
        cuadrado = new Cuadrado(g, r, v, a, estilo);
        lista = new ArrayList();
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
        //canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        //canvas.drawPath(drawPath, drawPaint);
        super.onDraw(canvas);
        for(int i=0; i < lista.size(); i++){
            if(lista.get(i) instanceof Circulo){
                canvas.drawOval(((Circulo)lista.get(i)).getRect(),((Circulo)lista.get(i)).getPaint());
            }else if(lista.get(i) instanceof Cuadrado){
                canvas.drawRect(((Cuadrado)lista.get(i)).getRect(),((Cuadrado)lista.get(i)).getPaint());
            }else{
                canvas.drawPath(((Linea)lista.get(i)).getPath(), ((Linea)lista.get(i)).getPaint());
            }
            if(tipo == 0) {
                canvas.drawPath(linea.getPath(), linea.getPaint());
            }else if(tipo == 1) {
                canvas.drawRect(cuadrado.getRect(),cuadrado.getPaint());
            }else if(tipo == 2){
                canvas.drawOval(circulo.getRect(),circulo.getPaint());
            }
            canvasDraw = canvas;
        }
    }

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //drawPath.moveTo(touchX, touchY);
                linea.getPath().moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                //drawPath.lineTo(touchX, touchY);
                linea.moveTo(touchX, touchY);
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    /*private void startTouch(float x, float y) {
        if(tipo == 0) {
            linea.getPath().moveTo(x, y);
            mX = x;
            mY = y;
        }
        else if (tipo == 1) {
            cuadrado.getRect().set(x, y, y, x);
            rX = x;
            rY = y;
        }
        else if (tipo == 2) {
            circulo.getRect().set(x,y,y,x);
            rX = x;
            rY = y;
        }
    }*/

    public int getGrosor() { return g/5 - 1; }
    public int getRojo() { return r; }
    public int getVerde() { return v; }
    public int getAzul() { return a; }

    public void setGrosorColor(int g, int r, int v, int a) {
        linea.getPaint().setColor(Color.rgb(r, v, a));
        linea.getPaint().setStrokeWidth(g);
        circulo.getPaint().setColor(Color.rgb(r, v, a));
        circulo.getPaint().setStrokeWidth(g);
        cuadrado.getPaint().setColor(Color.rgb(r, v, a));
        cuadrado.getPaint().setStrokeWidth(g);
    }
}

class Linea extends Path {
    private Paint paint;
    private Path path;

    public Linea(int g, int r, int v, int a, Paint.Style estilo){
        path = new Path();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(g);
        paint.setColor(Color.rgb(r, v, a));
        paint.setStyle(estilo);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    public Paint getPaint(){
        return paint;
    }

    public Path getPath(){
        return path;
    }
}

class Circulo {
    private RectF rect;
    private Paint paint;

    public Circulo(int g, int r, int v, int a, Paint.Style estilo){
        rect = new RectF();
        paint = new Paint();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(g);
        paint.setColor(Color.rgb(r, v, a));
        paint.setStyle(estilo);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    public Paint getPaint(){
        return paint;
    }

    public RectF getRect(){
        return rect;
    }
}
 class Cuadrado {
     private RectF rect;
     private Paint paint;

     public Cuadrado(int g, int r, int v, int a, Paint.Style estilo){
         rect = new RectF();
         paint = new Paint();
         paint = new Paint();
         paint.setAntiAlias(true);
         paint.setStrokeWidth(g);
         paint.setColor(Color.rgb(r, v, a));
         paint.setStyle(estilo);
         paint.setStrokeJoin(Paint.Join.ROUND);
         paint.setStrokeCap(Paint.Cap.ROUND);
     }

     public RectF getRect(){
         return rect;
     }

     public Paint getPaint(){
         return paint;
     }
 }
