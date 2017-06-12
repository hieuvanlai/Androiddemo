package com.example.hieuv.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.toString() ;

    private ArrayList<ImageView> ivBlackKey = new ArrayList<>();
    private ArrayList<ImageView> ivWhiteKey = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivBlackKey.add((ImageView) findViewById(R.id.ivblack1));
        ivBlackKey.add((ImageView) findViewById(R.id.ivblack2));
        ivBlackKey.add((ImageView) findViewById(R.id.ivblack3));
        ivBlackKey.add((ImageView) findViewById(R.id.ivblack4));
        ivBlackKey.add((ImageView) findViewById(R.id.ivblack5));

        ivWhiteKey.add((ImageView) findViewById(R.id.ivwhite1));
        ivWhiteKey.add((ImageView) findViewById(R.id.ivwhite2));
        ivWhiteKey.add((ImageView) findViewById(R.id.ivwhite3));
        ivWhiteKey.add((ImageView) findViewById(R.id.ivwhite4));
        ivWhiteKey.add((ImageView) findViewById(R.id.ivwhite5));
        ivWhiteKey.add((ImageView) findViewById(R.id.ivwhite6));
        ivWhiteKey.add((ImageView) findViewById(R.id.ivwhite7));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        for (ImageView imageView :ivWhiteKey){
            if (isInside(event.getX(),event.getY(),imageView)){
                Log.d(TAG,"onTouchEvent"+event.getX()+" "+imageView.getTag());}
        }
        for (ImageView imageView :ivBlackKey){
            if (isInside(event.getX(),event.getY(),imageView)){
                Log.d(TAG,"onTouchEvent"+event.getX()+" "+imageView.getTag());}
        }
        return super.onTouchEvent(event);
    }
    public boolean isInside(float x,float y ,View v){
        int[] location = new  int[2];
        v.getLocationOnScreen(location);
        int left= location[0];
        int top= location[1];
        int right= left + v.getWidth();
        int bottom = top+v.getHeight();
        return x> left && x <  right && y > top && y <bottom;

    }
}
