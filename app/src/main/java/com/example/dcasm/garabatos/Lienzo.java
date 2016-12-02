package com.example.dcasm.garabatos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class Lienzo extends View {

    private Bitmap bit;
    private Canvas can;
    Context context;

    public Lienzo(Context c, AttributeSet as) {
        super(c, as);
    }
}
