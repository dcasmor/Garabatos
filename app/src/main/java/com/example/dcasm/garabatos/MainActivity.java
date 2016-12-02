package com.example.dcasm.garabatos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_lateral);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        RelativeLayout content_main = (RelativeLayout) findViewById(R.id.content_main);
        Lienzo fondo = new Lienzo(this);
        content_main.addView(fondo);
        getSupportActionBar().hide();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_lineas) {

        } else if (id == R.id.nav_circulo) {
        } else if (id == R.id.nav_cuadrado) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class Lienzo extends View {



        public Lienzo(Context context) {

            super(context);

        }



        protected void onDraw(Canvas canvas) {

            canvas.drawRGB(0, 0, 255);

            int ancho = canvas.getWidth();

            int alto = canvas.getHeight();

            Bitmap bmp = BitmapFactory.decodeResource(getResources(),

                    R.drawable.circle);

            canvas.drawBitmap(bmp, (ancho - 250) / 2, (alto - 200) / 2, null);

            //dibuja 10 circulos

            Paint pincel1 = new Paint();

            pincel1.setARGB(255, 255, 0, 0);

            pincel1.setStyle(Paint.Style.STROKE); // dibuja solo la figura no rellena

            for (int f = 0; f < 10; f++) {

                canvas.drawCircle(ancho / 3, alto / 4, f * 15, pincel1);

            }

            //dibuja una línea

            canvas.drawLine(10,10,100,200,pincel1);

            // dibuja un rectángulo

            pincel1.setColor(Color.GREEN);

            pincel1.setStyle(Paint.Style.FILL); // rellena la figura

            canvas.drawRect(40,500,200,800,pincel1);

        }

    }
}
