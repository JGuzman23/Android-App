package com.example.multifuncion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnKeyListener {


    EditText edittxt;
    TextView txt;
    ConstraintLayout lys;
    RelativeLayout Rl;
    SwipeListener swipe;

    int cont_billete_100;
    int cont_billete_50;
    int cont_billete_20;
    int cont_moneda_10;
    int cont_moneda_5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittxt = findViewById(R.id.edt);
        txt = findViewById(R.id.txt);

        lys = findViewById(R.id.ly);
        lys = findViewById(R.id.lys);

        Rl = findViewById(R.id.rla);
        swipe = new SwipeListener(Rl);


        edittxt.setOnKeyListener(this);
    }




    public void calculo() {

        int valor = Integer.parseInt(edittxt.getText().toString());

        while (valor <= 1000 && valor >= 99) {

            cont_billete_100++;
            valor -= 100;

        }
        while (valor <= 100 && valor >= 50) {

            cont_billete_50++;
            valor -= 50;

        }
        while (valor <= 49 && valor >= 20) {

            cont_billete_20++;
            valor -= 20;

        }
        while (valor <= 19 && valor >= 10) {

            cont_moneda_10++;
            valor -= 10;

        }
        while (valor <= 9 && valor >= 1) {

            cont_moneda_5++;
            valor -= 5;

        }


    }
    public void calculo2() {

        int valor = Integer.parseInt(edittxt.getText().toString());

       cont_billete_100 = valor/100;
       cont_billete_50= valor/50;
       cont_billete_20 = valor/20;
       cont_moneda_10 = valor/10;
       cont_moneda_5=valor/5;




    }

    public void LimpiarVariables() {
        cont_billete_100 = 0;
        cont_billete_50 = 0;
        cont_billete_20 = 0;
        cont_moneda_10 = 0;
        cont_moneda_5 = 0;
    }


    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {

                calculo2();
                txt.setText("billete de 100:" + cont_billete_100 + "\n" + "billetes de 50: " + cont_billete_50 + "\n" + "billetes de 20: " + cont_billete_20 + "\n" + "Mondedas de 10:" + cont_moneda_10 + "\n" + "monedas de 5:" + cont_moneda_5);
                LimpiarVariables();

            }
        }

        return true;

    }
    public void bgBlanco(View view) {

        lys.setBackgroundColor(Color.argb(255, 255, 255, 255));
        Rl.setBackgroundColor(Color.argb(255, 255, 255, 255));
    }


    public void bgGris(View view) {

        lys.setBackgroundColor(Color.argb(255, 200, 200, 200));
        Rl.setBackgroundColor(Color.argb(255, 200, 200, 200));


    }

    public void bgGrisOcuro(View view) {
        lys.setBackgroundColor(Color.argb(255, 120, 120, 120));
        Rl.setBackgroundColor(Color.argb(255, 120, 120, 120));
    }

    public void bgNegro(View view) {
        lys.setBackgroundColor(Color.argb(255, 0, 0, 0));
        Rl.setBackgroundColor(Color.argb(255, 0, 0, 0));

    }


   private class SwipeListener implements View.OnTouchListener {

        GestureDetector gestureDetector;

        SwipeListener(View view){
            int threshold =100;
            int velocity_threshold = 100;

            GestureDetector.SimpleOnGestureListener listener =
                    new GestureDetector.SimpleOnGestureListener(){
                     @Override
                        public boolean onDown(MotionEvent e){
                         return true;
                     }
                     public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
                         float xDiff = e2.getX() - e1.getX();
                         float yDiff = e2.getY() - e1.getY();
                         try{
                             if(Math.abs(xDiff) > Math.abs(yDiff)){
                                 if(Math.abs(xDiff)> threshold && Math.abs(velocityX) > velocity_threshold){

                                     if(xDiff > 0){
                                         System.exit(1);
                                     }else{
                                         txt.setText("");
                                         edittxt.setText("");
                                     }
                                     return true;
                                 }
                             }else{
                                 if(Math.abs(yDiff) > threshold && Math.abs(velocityY) > velocity_threshold ){
                                     if(yDiff > 0 ){
                                         txt.setText("Abajo");
                                     }else{
                                         txt.setText("arriba");
                                     }
                                     return  true;
                                 }
                             }

                         }catch (Exception e){
                             e.printStackTrace();
                         }
                         return  false;

                     }
                    };
            gestureDetector = new GestureDetector(listener);

            view.setOnTouchListener(this);

        }
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return gestureDetector.onTouchEvent(motionEvent);
        }
    }
}
