package com.rc.designpattern.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.rc.designpattern.R;
import com.rc.designpattern.memento.CareTaker;
import com.rc.designpattern.memento.Memento;
import com.rc.designpattern.memento.Originator;
import com.rc.designpattern.shapes.Circle;
import com.rc.designpattern.shapes.CompoundShape;
import com.rc.designpattern.shapes.Shape;
import com.rc.designpattern.tools.Generator;
import com.rc.designpattern.view.DragLayout;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private static final float YOFFSET = 100;
//    private final static int MAX_STREAMS = 10; //mio

    private RelativeLayout mFrame;
    int mDisplayWidth;
    int mDisplayHeight;
    private GestureDetector mGestureDetector;
    private ImageButton bttnCompound;
    private ImageButton bttnRectangle;
    private ImageButton bttnCircle;
    private ImageButton bttnTriangle;
    private Originator mOriginator = new Originator();
    private CareTaker aCaretaker = new CareTaker();
    private Button bttnUndo;
    private int currentState = 0;
    private final int duration = Toast.LENGTH_SHORT;
//    private AudioManager mAudioManager;
//    private float mStreamVolume;
    // SoundPool
//    private SoundPool mSoundPool;
    // ID for the bubble popping sound
//    private int mSoundID;
    //speeed mode
//    public final static int RANDOM = 0;
//    public static int speedMode = RANDOM;
//    public final static int SINGLE = 1;
//    public final static int STILL = 2;


//    int whatShape = -1;

//    private static final ShapeFactory.ShapeType shapes[] = {ShapeFactory.ShapeType.RECTANGLE,
//            ShapeFactory.ShapeType.CIRCLE, ShapeFactory.ShapeType.TRIANGLE};

    private HashMap<String, Integer> shapeMapping = new HashMap<String, Integer>();

    private final String CIRCLECLASS = "com.rc.designpattern.shapes.Circle$CircleView";
    private final String RECTANGLECLASS = "com.rc.designpattern.shapes.Rectangle$RectangleView";
    private final String TRIANGLECLASS = "com.rc.designpattern.shapes.Triangle$TriangleView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFrame = (RelativeLayout) findViewById(R.id.frame);
        bttnCompound = (ImageButton) findViewById(R.id.bttnCompound);
        bttnCircle = (ImageButton) findViewById(R.id.bttnCircle);
        bttnRectangle = (ImageButton) findViewById(R.id.bttnRectangle);
        bttnTriangle = (ImageButton) findViewById(R.id.bttnTriangle);
        bttnUndo = (Button) findViewById(R.id.bttnUndo);
        shapeMapping.put(CIRCLECLASS, 1);
        shapeMapping.put(RECTANGLECLASS, 0);
        shapeMapping.put(TRIANGLECLASS, 2);

        bttnCompound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shape myShape = new Circle(MainActivity.this, Generator.randInt(100, 500), Generator.randInt(100, 800), Generator.randInt(50, 100));
                myShape.setShapeColor(Generator.generateColor());
//                ((View)myShape).setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                CompoundShape compoundShape = new CompoundShape(MainActivity.this, myShape);
                compoundShape.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark));

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, 200);
                params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                compoundShape.setLayoutParams(params);

                mFrame.addView(compoundShape);

                // memento
                mOriginator.setState(mFrame.getChildAt(mFrame.getChildCount() - 1));
                Memento currentMemento = mOriginator.save2Memento();
                aCaretaker.add(currentMemento, currentState);
                currentState++;
            }
        });

        bttnCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Circle myShape = new Circle(MainActivity.this, Generator.randInt(100, 500), Generator.randInt(100, 800), Generator.randInt(50, 100));
                myShape.setShapeColor(Generator.generateColor());
                mFrame.addView(myShape);

                // memento
                mOriginator.setState(mFrame.getChildAt(mFrame.getChildCount() - 1));
                Memento currentMemento = mOriginator.save2Memento();
                aCaretaker.add(currentMemento, currentState);
                currentState++;
            }
        });
        bttnRectangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Shape myShape = new Rectangle(Generator.randInt(100, 500), Generator.randInt(100, 800), Generator.randInt(50, 100), Generator.randInt(50, 100), Generator.generateColor());
//
////                Shape myShape = ShapeFactory.getShape(ShapeType.RECTANGLE);
//                myShape.drawShape(mFrame, getApplicationContext());
//
//                // memento
//                mOriginator.setState(mFrame.getChildAt(mFrame.getChildCount() - 1));
//                Memento currentMemento = mOriginator.save2Memento();
//                aCaretaker.add(currentMemento, currentState);
//                currentState++;
            }
        });
        bttnTriangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Shape myShape = new Triangle(Generator.randInt(100, 500), Generator.randInt(100, 800), Generator.randInt(50, 100), Generator.randInt(50, 100), Generator.generateColor());
//
////                Shape myShape = ShapeFactory.getShape(ShapeType.TRIANGLE);
//                myShape.drawShape(mFrame, getApplicationContext());
//
//                // memento
//                mOriginator.setState(mFrame.getChildAt(mFrame.getChildCount() - 1));
//                Memento currentMemento = mOriginator.save2Memento();
//                aCaretaker.add(currentMemento, currentState);
//                currentState++;
            }
        });
        bttnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mOriginator.getStateFromMemento(aCaretaker.get(0));
                //mFrame = mOriginator.getState();


                if (currentState == 0) {
                    return;
                }
                currentState--;

                reDrawLayout();
            }
        });

        //mOriginator.setState(mFrame,getApplicationContext());
        //aCaretaker.add(mOriginator.save2Memento());
    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus) {
//            // Get the size of the display so this com.rc.designpattern.view knows where borders are
//            mDisplayWidth = mFrame.getWidth();
//            mDisplayHeight = mFrame.getHeight();
//        }
//    }

    @Override
    public void onResume() {
        super.onResume();

//        setupGestureDetector();

//        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
//        mStreamVolume = (float) mAudioManager
//                .getStreamVolume(AudioManager.STREAM_MUSIC)
//                / mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//
//        mSoundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC,0);
//
//
//        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() { // sellama cuando el sonido se carga totalmente
//            @Override
//            public void onLoadComplete(SoundPool soundPool, int sampleId,
//                                       int status) {
//                if (status == 0)
//                    setupGestureDetector();
//            }
//        });
//        mSoundID = mSoundPool.load(this, R.raw.bubble_pop,1);

    }

    private void setupGestureDetector() {

        mGestureDetector = new GestureDetector(this,

                new GestureDetector.SimpleOnGestureListener() {

                    // If a fling gesture starts on a BubbleView then change the
                    // BubbleView's velocity
                    @Override
                    public boolean onFling(MotionEvent event1, MotionEvent event2,
                                           float velocityX, float velocityY) {

//                        float xPos = event1.getShapeX();
//                        float yPos = event1.getShapeY()-YOFFSET;
//                        if(whatShape == -1){
//                            return false;
//                        }
//
//                        // TODO - Implement onFling actions.
//                        // You can get all Views in mFrame using the
//                        // ViewGroup.getChildCount() method
//                        for (int i=0;i<mFrame.getChildCount();i++){
//                            ShapeView myViewTmp =(ShapeView) mFrame.getChildAt(i);
//                            if(myViewTmp.intersects(xPos,yPos) ){
//                                myViewTmp.deflect(velocityX, velocityY);
//                                return false;
//                            }
//
//                        }
//
//

                        return false;

                    }


                    // If a single tap intersects a BubbleView, then pop the BubbleView
                    // Otherwise, create a new BubbleView at the tap's location and add
                    // it to mFrame. You can get all views from mFrame with ViewGroup.getChildAt()

                    @Override
                    public boolean onSingleTapConfirmed(MotionEvent event) {

                        // TODO - Implement onSingleTapConfirmed actions.
                        // You can get all Views in mFrame using the
                        // ViewGroup.getChildCount() method
//                        float xPos = event.getX();
//                        float yPos = event.getY() - YOFFSET;
//                        if (whatShape == -1) {
//                            return false;
//                        }
//
//                        Shape myShape = ShapeFactory.getShape(shapes[whatShape]);

//                        for (int i=0;i<mFrame.getChildCount();i++){
//                            ShapeView myViewTmp = (ShapeView) mFrame.getChildAt(i);
                            /*
                            Log.i("posicion","xpos(evento)="+xPos +"ypos(evento) = " + yPos + "x com.rc.designpattern.view = "+
                                    myViewTmp.getShapeX()+"y com.rc.designpattern.view =" + myViewTmp.getShapeY() + "ancho =" +myViewTmp.getShapeWidth());
                                    */

//                            if(myViewTmp.intersects(xPos,yPos) ){
//                                CharSequence text = "You are in the position: " + myViewTmp.getClass().getName();
//                                Log.i("REFLECTION",myViewTmp.getClass().getName());
//                                Toast toast = Toast.makeText(getApplicationContext(),text,duration);
//                                toast.show();
//                                int index = shapeMapping.get(myViewTmp.getClass().getName());
//                                myShape = ShapeFactory.getShape(com.rc.designpattern.shapes[index]);
//                                myShape.setShapeColor(Generator.generateColor());
//                                myShape.setWidth(Generator.randInt(ShapeFactory.MINWIDTH,ShapeFactory.MAXWIDTH));
//                                reDrawLayout();
//                                return true;
//                            }
//
//                        }


//                        myShape.drawShape(mFrame, xPos, yPos, getApplicationContext());
//                        mSoundPool.play(mSoundID, (float)mStreamVolume , (float)mStreamVolume, 1, 0,1.0f);


//                        mOriginator.setState(mFrame.getChildAt(mFrame.getChildCount() - 1)); //agregando el ultimo com.rc.designpattern.view que inserte al frame
//                        Memento currentMemento = mOriginator.save2Memento(); //guardar estado
//
//                        aCaretaker.add(currentMemento, currentState); //guargar a la lista de estados
//
//                        currentState++;

                        return false;
                    }
                });
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        // TODO - delegate the touch to the gestureDetector
//
//        return mGestureDetector.onTouchEvent(event);
//
//    }

    public void reDrawLayout() {
        mFrame.removeAllViews();
        for (int i = 0; i < currentState; i++) {
            mFrame.addView(aCaretaker.get(i).getState());
        }
        //aCaretaker.get(currentState).switchUndone(); //le pongo flag en undone = true, al ultimo que hice undo
    }
}
