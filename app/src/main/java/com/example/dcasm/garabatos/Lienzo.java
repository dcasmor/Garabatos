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

    private Canvas canvasDraw;
    private Bitmap canvasBitmap;
    private Linea linea;
    private Circulo circulo;
    private Cuadrado cuadrado;
    private ArrayList objetos;
    private int g = 15, r = 0, v = 0, a = 0, tipo;
    private Paint.Style estilo;
    private float mX, mY, rX, rY;
    private static final float TOLERANCE = 5;

    public Lienzo(Context context, AttributeSet attrs) {
        super(context, attrs);
        tipo = 0;
        estilo = Paint.Style.STROKE;
        linea = new Linea(g, r, v, a, estilo);
        circulo = new Circulo(g, r, v, a, estilo);
        cuadrado = new Cuadrado(g, r, v, a, estilo);
        objetos = new ArrayList();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvasDraw = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < objetos.size(); i++){
            if(objetos.get(i) instanceof Circulo)
                canvas.drawOval(((Circulo) objetos.get(i)).getRect(),((Circulo) objetos.get(i)).getPaint());
            else if(objetos.get(i) instanceof Cuadrado)
                    canvas.drawRect(((Cuadrado) objetos.get(i)).getRect(),((Cuadrado) objetos.get(i)).getPaint());
            else
                canvas.drawPath(((Linea) objetos.get(i)).getPath(), ((Linea) objetos.get(i)).getPaint());

            if(tipo == 0)
                canvas.drawPath(linea.getPath(), linea.getPaint());
            else if(tipo == 1)
                canvas.drawOval(circulo.getRect(),circulo.getPaint());
            else if(tipo == 2)
                canvas.drawRect(cuadrado.getRect(),cuadrado.getPaint());

            canvasDraw = canvas;
        }
    }

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                pulsa(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                mueve(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                levanta();
                break;
        }
        invalidate();
        return true;
    }

    public void pulsa(float x, float y) {
        if (tipo == 0) {
            linea.getPath().moveTo(x, y);
            mX = x;
            mY = y;
        }
        else if (tipo == 1) {
            circulo.getRect().set(x, y, y, x);
            rX = x;
            rY = y;
        }
        else if (tipo == 2) {
            cuadrado.getRect().set(x, y, y, x);
            rX = x;
            rY = y;
        }
    }

    private void mueve(float x, float y) {
        float auxX = Math.abs(x - mX);
        float auxY = Math.abs(y - mY);

        if (auxX >= TOLERANCE || auxY >= TOLERANCE) {
            if (tipo == 0)
                linea.getPath().quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            else if (tipo == 1)
                circulo.getRect().set(rX, rY, (mX + x - mX), (mY + y - mY));
            else if (tipo == 2)
                cuadrado.getRect().set(rX, rY, (mX + x - mX), (mY + y - mY));

            mX = x;
            mY = y;
        }
    }

    private void levanta() {
        if(tipo == 0) {
            linea.getPath().lineTo(mX, mY);
            objetos.add(linea);
            linea = new Linea(g, r, v, a, estilo);
        } else if(tipo == 1) {
            objetos.add(circulo);
            circulo = new Circulo(g, r, v, a, estilo);
        } else if(tipo == 2) {
            objetos.add(cuadrado);
            cuadrado = new Cuadrado(g, r, v, a, estilo);
        }
    }

    public int getGrosor() { return g/5 - 1; }

    public int getRojo() { return r; }

    public int getVerde() { return v; }

    public int getAzul() { return a; }


    //CAMBIAR COLOR Y TIPO DE DIBUJO
    public void setGrosorColor(int g, int r, int v, int a) {
        this.g = g;
        this.r = r;
        this.v = v;
        this.a = a;
        linea.getPaint().setColor(Color.rgb(r, v, a));
        linea.getPaint().setStrokeWidth(g);
        circulo.getPaint().setColor(Color.rgb(r, v, a));
        circulo.getPaint().setStrokeWidth(g);
        cuadrado.getPaint().setColor(Color.rgb(r, v, a));
        cuadrado.getPaint().setStrokeWidth(g);
    }

    public void cambiar(int tipo) {
        this.tipo = tipo;
    }

    //LIENZO NUEVO
    public void limpiar() {
        linea.getPath().reset();
        objetos.clear();
        invalidate();
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
