package com.example.dcasm.garabatos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class Selector extends AppCompatActivity{

    protected SeekBar grosor, rojo, verde, azul;
    protected TextView tvG, tvR, tvV, tvA;
    protected Button bC, bA;

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

        grosor = (SeekBar) findViewById(R.id.sbGrosor);
        rojo = (SeekBar) findViewById(R.id.sbRojo);
        verde = (SeekBar) findViewById(R.id.sbVerde);
        azul = (SeekBar) findViewById(R.id.sbAzul);

        bC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { onBackPressed(); }
        });
        bA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        grosor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) { tvG.setText("" + i); }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        rojo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) { tvR.setText("" + i); }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        verde.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) { tvV.setText("" + i); }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        azul.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) { tvA.setText("" + i); }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }
}
