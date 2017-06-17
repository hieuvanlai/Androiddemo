package com.example.hieuv.drumpads;

import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.hieuv.drumpads.noteplayer.NotePlayer;
import com.example.hieuv.drumpads.touches.Touch;
import com.example.hieuv.drumpads.touches.TouchAction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ImageView> ivkeyPads = new ArrayList<>();
    private static final String TAG = MainActivity.class.toString() ;
    private List<TouchInfo> touchInfoList = new ArrayList<>();

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
        NotePlayer.loadSounds(this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        List<Touch> touches = Touch.processEvent(event);

        Log.d(TAG, String.format("onTouchEvent: %s", touches));

        if (touches.size() == 0) return false;

        Touch firstTouch = touches.get(0);

        if (firstTouch.getAction() == TouchAction.DOWN) {
            ImageView pressedKey = findKeyByTouch(firstTouch);
            if (pressedKey != null && !checkPressedKey(pressedKey)) {
                //TODO: Play note
                String note = pressedKey.getTag().toString();
                NotePlayer.play(note);

                touchInfoList.add(new TouchInfo(pressedKey, firstTouch));
            }
        }
        else if (firstTouch.getAction() == TouchAction.UP) {
            ImageView pressedKey = findKeyByTouch(firstTouch);

            if (pressedKey != null) {
                Iterator<TouchInfo> touchInfoIterator = touchInfoList.iterator();
                while(touchInfoIterator.hasNext()) {
                    TouchInfo touchInfo = touchInfoIterator.next();
                    if (touchInfo.touch.getTouchId() == firstTouch.getTouchId()) {
                        touchInfoIterator.remove();
                    }
                }
            }
        }
        else if (firstTouch.getAction() == TouchAction.MOVE) {
            for (Touch touch: touches) {
                ImageView pressedKey = findKeyByTouch(touch);

                Iterator<TouchInfo> touchInfoIterator = touchInfoList.iterator();
                while(touchInfoIterator.hasNext()) {
                    TouchInfo touchInfo = touchInfoIterator.next();
                    if (touchInfo.touch.equals(touch) && touchInfo.pressedKey != pressedKey) {
                        touchInfoIterator.remove();
                    }
                }

                if (pressedKey != null) {
                    if (!checkPressedKey(pressedKey)) {
                        //TODO: Play note
                        String note = pressedKey.getTag().toString();
                        NotePlayer.play(note);

                        touchInfoList.add(new TouchInfo(pressedKey, touch));
                    }
                }

            }
        }


//        Log.d(TAG, String.format("onTouchEvent: %s", touches));

//        for (ImageView blackKey : blackKeys) {
//            if (isInside(event.getX(), event.getY(), blackKey)) {
//                Log.d(TAG, "onTouchEvent: " + blackKey.getTag());
//            }
//        }
//
//        for (ImageView whiteKey : whiteKeys) {
//            if (isInside(event.getX(), event.getY(), whiteKey)) {
//                Log.d(TAG, "onTouchEvent: " + whiteKey.getTag());
//            }
//        }

        updateKeyImages();
        return super.onTouchEvent(event);
    }
    private void updateKeyImages(){
        for (ImageView ivKeyPad : ivkeyPads){
            if (checkPressedKey(ivKeyPad)){
                ivKeyPad.setImageResource(R.drawable.key2);
            }else {
                ivKeyPad.setImageResource(R.drawable.key);
            }
        }
    }
    private boolean checkPressedKey(ImageView pressedKey){
        for (TouchInfo touchInfo: touchInfoList){
            if (touchInfo.pressedKey==pressedKey){
                return true;
            }
        }
        return false;
    }
    private ImageView findKeyByTouch(Touch touch){
        for (ImageView ivKeyPad: ivkeyPads){
            if (touch.checkHit(ivKeyPad)){
                return ivKeyPad;
            }
        }
        return null;
    }


}
class TouchInfo{
    public ImageView pressedKey;
    public Touch touch;

    public TouchInfo(ImageView pressedKey, Touch touch) {
        this.pressedKey = pressedKey;
        this.touch = touch;
    }

}
