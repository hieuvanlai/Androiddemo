package com.example.hieuv.drumpads;

import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ImageView> ivkeyPads = new ArrayList<>();
    private static final String TAG = MainActivity.class.toString() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivkeyPads.add((ImageView) findViewById(R.id.key01));
        ivkeyPads.add((ImageView) findViewById(R.id.key02));
        ivkeyPads.add((ImageView) findViewById(R.id.key03));
        ivkeyPads.add((ImageView) findViewById(R.id.key04));
        ivkeyPads.add((ImageView) findViewById(R.id.key11));
        ivkeyPads.add((ImageView) findViewById(R.id.key12));
        ivkeyPads.add((ImageView) findViewById(R.id.key13));
        ivkeyPads.add((ImageView) findViewById(R.id.key14));
        ivkeyPads.add((ImageView) findViewById(R.id.key21));
        ivkeyPads.add((ImageView) findViewById(R.id.key22));
        ivkeyPads.add((ImageView) findViewById(R.id.key23));
        ivkeyPads.add((ImageView) findViewById(R.id.key24));
        ivkeyPads.add((ImageView) findViewById(R.id.key31));
        ivkeyPads.add((ImageView) findViewById(R.id.key32));
        ivkeyPads.add((ImageView) findViewById(R.id.key33));
        ivkeyPads.add((ImageView) findViewById(R.id.key34));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {



            for (ImageView imageView:ivkeyPads){
                if (isInside(event.getX(),event.getY(),imageView)){
                    imageView.setImageResource(R.drawable.key2);
                }else {
                    imageView.setImageResource(R.drawable.key);
                };
            }
        Log.d(TAG,"onTouchEvent"+event.getX()+" "+event.getY());
        return super.onTouchEvent(event);
    }
    public  boolean isInside(float x, float y, View v){
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right= left + v.getWidth();
        int bottom= top + v.getHeight();
        return x>left && x <right&&y>top&&y<bottom;

    }
}
