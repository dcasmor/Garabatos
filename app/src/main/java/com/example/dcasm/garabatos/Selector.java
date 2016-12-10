package com.example.dcasm.garabatos;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class Selector extends AppCompatActivity{

    protected SeekBar grosor, rojo, verde, azul;
    protected TextView tvG, tvR, tvV, tvA, tvMuestra;
    protected Button bC, bA;
    private int g, r, v, a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selector);

        bC = (Button) findViewById(R.id.bCancelar);
        bA = (Button) findViewById(R.id.bAceptar);

        tvG = (TextView) findViewById(R.id.tvGrosor);
        tvR = (TextView) findViewById(R.id.tvRojo);
        tvV = (TextView) findViewById(R.id.tvVerde);
        tvA = (TextView) findViewById(R.id.tvAzul);
        tvMuestra = (TextView) findViewById(R.id.tvMuestra);

        grosor = (SeekBar) findViewById(R.id.sbGrosor);
        rojo = (SeekBar) findViewById(R.id.sbRojo);
        verde = (SeekBar) findViewById(R.id.sbVerde);
        azul = (SeekBar) findViewById(R.id.sbAzul);

        Bundle extras = getIntent().getExtras();

        g = extras.getInt("g");
        r = extras.getInt("r");
        v = extras.getInt("v");
        a = extras.getInt("a");

        grosor.setProgress(g);
        tvG.setText("" + g);

        rojo.setProgress(r);
        tvR.setText("" + r);

        verde.setProgress(v);
        tvV.setText("" + v);

        azul.setProgress(a);
        tvA.setText("" + a);

        tvMuestra.setBackgroundColor(Color.rgb(r, v, a));

        bC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { onBackPressed(); }
        });
        bA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                data.putExtra("g", g);
                data.putExtra("r", r);
                data.putExtra("v", v);
                data.putExtra("a", a);
                setResult(RESULT_OK, data);
                finish();
            }
        });

        grosor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvG.setText("" + i);
                g = i;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        rojo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvR.setText("" + i);
                r = i;
                tvMuestra.setBackgroundColor(Color.rgb(r, v, a));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        verde.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvV.setText("" + i);
                v = i;
                tvMuestra.setBackgroundColor(Color.rgb(r, v, a));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        azul.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvA.setText("" + i);
                a = i;
                tvMuestra.setBackgroundColor(Color.rgb(r, v, a));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }
}
