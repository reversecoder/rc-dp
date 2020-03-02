package com.rc.designpatterndemo.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.cleveroad.cyclemenuwidget.CycleMenuWidget;
import com.cleveroad.cyclemenuwidget.OnMenuItemClickListener;
import com.cleveroad.cyclemenuwidget.OnStateChangedListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.rc.attributionpresenter.activity.LicenseActivity;
import com.rc.attributionpresenter.view.AnimatedTextView;
import com.rc.designpattern.pattern.behavioural.command.AddShapeCommand;
import com.rc.designpattern.pattern.behavioural.iterator.TopicIteratorManager;
import com.rc.designpattern.pattern.behavioural.observer.Subscriber;
import com.rc.designpattern.pattern.behavioural.observer.Topic;
import com.rc.designpattern.pattern.behavioural.state.ActionType;
import com.rc.designpattern.pattern.behavioural.state.ShapeCommandType;
import com.rc.designpattern.pattern.behavioural.state.ShapeState;
import com.rc.designpattern.pattern.creational.abstractfactory.Shape;
import com.rc.designpattern.pattern.structural.bridge.Property;
import com.rc.designpattern.pattern.structural.composite.CompoundShape;
import com.rc.designpattern.util.Util;
import com.rc.designpatterndemo.R;
import com.rc.designpatterndemo.controller.ActionController;
import com.skydoves.flourish.Flourish;
import com.skydoves.flourish.FlourishAnimation;
import com.skydoves.flourish.FlourishOrientation;
import com.thebluealliance.spectrum.SpectrumDialog;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import cn.ymex.popup.controller.AlertController;
import cn.ymex.popup.dialog.PopupDialog;

public class MainActivity extends AppCompatActivity implements Subscriber<Shape> {

    private String TAG = "MainActivity";
    //Toolbar
    private ImageView leftMenu;
    private ImageView rightMenu;
    private AnimatedTextView toolbarTitle;

    private RelativeLayout shapeContainer;
    private Shape selectedShape;
    //    private ShapeFactory shapeFactory;
//    private Topic<Property> topicCircleBackground;
//    private CircleViewGroup circleViewGroup;
//    private CompositeShape compositeShape;
//    private CommandManager circleCommandManager;
    private ActionController actionController = new ActionController();

    // Cycle menu
    private CycleMenuWidget cycleMenuWidget;

    // Bottom menu
    private BottomSheetBehavior bottomSheetBehavior;
    private LinearLayout llBottomSheet, llViewOptionsHeader;
    private AppCompatImageView ivToggleAttributes;
    private DiscreteSeekBar seekBarCircleX, seekBarCircleY, seekBarCircleRadius, seekBarMeasureWidth, seekBarMeasureHeight;
    private ImageView ivShapeBackgroundColor, ivShapeColor;

    // Settings
    private Flourish flourish;
    private ViewGroup parentLayout;

    // Tap sound
    private AudioManager mAudioManager;
    private float mStreamVolume;
    private SoundPool mSoundPool;
    private int mSoundID;
    private final static int MAX_STREAMS = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initAction();
    }

    private void initUI() {
        //toolbar view
        toolbarTitle = (AnimatedTextView) findViewById(R.id.toolbar_title);
        leftMenu = (ImageView) findViewById(R.id.left_menu);
        rightMenu = (ImageView) findViewById(R.id.right_menu);

        parentLayout = findViewById(R.id.parentLayout);
        shapeContainer = (RelativeLayout) findViewById(R.id.shape_container);
        cycleMenuWidget = (CycleMenuWidget) findViewById(R.id.itemCycleMenuWidget);

        // Bottom sheet
        llBottomSheet = (LinearLayout) findViewById(R.id.bottomSheet);
        llViewOptionsHeader = (LinearLayout) findViewById(R.id.view_options_header);
        ivToggleAttributes = (AppCompatImageView) findViewById(R.id.image_toggle);
        seekBarCircleX = (DiscreteSeekBar) findViewById(R.id.seekbar_circle_x);
        seekBarCircleY = (DiscreteSeekBar) findViewById(R.id.seekbar_circle_y);
        seekBarCircleRadius = (DiscreteSeekBar) findViewById(R.id.seekbar_circle_radius);
        seekBarMeasureWidth = (DiscreteSeekBar) findViewById(R.id.seekbar_measure_width);
        seekBarMeasureHeight = (DiscreteSeekBar) findViewById(R.id.seekbar_measure_height);
        ivShapeBackgroundColor = (ImageView) findViewById(R.id.image_background_color);
        ivShapeColor = (ImageView) findViewById(R.id.image_shape_color);

        initTapSound();
        initShapeCreator();
        initToolbar();
        initCycleMenu();
        initBottomSheet();
        updateAttributeView();
        initSettings();
    }

    private void initShapeCreator() {
//        shapeFactory = new ShapeFactory();
    }

    private void initToolbar() {
        toolbarTitle.setAnimatedText(getString(R.string.title_activity_main), 0L);
        rightMenu.setVisibility(View.VISIBLE);
        rightMenu.setBackgroundResource(R.drawable.vector_settings_white);
    }

    private void initAction() {
        // Toolbar
        leftMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        rightMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flourish.show();
            }
        });
    }

    private void initCycleMenu() {
        cycleMenuWidget.setMenuRes(R.menu.menu_cycle);
        cycleMenuWidget.setCurrentPosition(-1);
        cycleMenuWidget.setCurrentItemsAngleOffset(CycleMenuWidget.UNDEFINED_ANGLE_VALUE);
        cycleMenuWidget.close(false);
        cycleMenuWidget.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(View view, int itemPosition) {
                switch (itemPosition) {
//                    case 0:
//                        if (shapeList.size() > 0) {
//                            RectangleProperty rectangleProperty = (RectangleProperty) shapeList.get(shapeList.size() - 1).getTopicValue();
//                            ((Rectangle) shapeList.get(shapeList.size() - 1).getShapeView()).setProperty(rectangleProperty.setHeight(rectangleProperty.getHeight() + 10));
//                        }

//                        TriangleProperty triangleProperty = new TriangleProperty(150, 5, Color.BLUE, Color.RED);
//                        Shape triangle = shapeFactory.createShape(MainActivity.this, ShapeType.TRIANGLE, triangleProperty);
//                        shapeList.add(triangle);
//                        dragView.addView(triangle.getShapeView());

//                        actionController.dispatchRequest(MainActivity.this, ActionType.TRIANGLE, shapeContainer);
//                        break;
//                    case 1:
//                        RectangleProperty rectangleProperty = new RectangleProperty(200, 100, 5, Color.BLUE, Color.CYAN);
//                        Shape rectangle = shapeFactory.createShape(MainActivity.this, ShapeType.RECTANGLE, rectangleProperty);
//                        shapeList.add(rectangle);
//                        dragView.addView(rectangle.getShapeView());
//                        break;

//                        actionController.dispatchRequest(MainActivity.this, ActionType.RECTANGLE, shapeContainer);
//                        break;
                    case 0:
//                        circleViewGroup = ShapeManager.getInstance(MainActivity.this).getCircleShape();
//                        compositeShape = ShapeManager.getInstance(MainActivity.this).getCompositeShape();
//                        compositeShape.add(circleViewGroup);
//                        dragLayout.addDragView(circleViewGroup, 0, 400, 380, 760, false, false);

                        // Observer pattern
//                        topicCircleBackground = new Topic<Property>(CircleViewGroupProperty.class.getSimpleName() + circleViewGroup.getProperty().getIdentity(), circleViewGroup.getProperty());
//                        topicCircleBackground.registerSubscriber(circleViewGroup);

                        // Command pattern
//                        circleCommandManager = new CommandManager();

                        actionController.dispatchRequest(MainActivity.this, ActionType.CIRCLE, shapeContainer);

//                        for(int i=0;i<shapeContainer.getChildCount();i++){
//                            Log.d(TAG, "Added views "+i+" is "+ shapeContainer.getChildAt(i).getClass().getSimpleName());
//                        }
                        break;
                    case 1:
                        actionController.dispatchRequest(MainActivity.this, ActionType.UNDO, shapeContainer);
                        break;
                    case 2:
                        actionController.dispatchRequest(MainActivity.this, ActionType.REDO, shapeContainer);
                        break;
                }

                // Close the cycle menu
//                cycleMenuWidget.close(true);

                // Play tap sound
                mSoundPool.play(mSoundID, (float) mStreamVolume, (float) mStreamVolume, 1, 0, 1.0f);

                // Update attribute view
                updateAttributeView();
            }

            @Override
            public void onMenuItemLongClick(View view, int itemPosition) {
            }
        });
        cycleMenuWidget.setStateChangeListener(new OnStateChangedListener() {
            @Override
            public void onStateChanged(CycleMenuWidget.STATE state) {
            }

            @Override
            public void onOpenComplete() {
            }

            @Override
            public void onCloseComplete() {
            }
        });
    }

    private void initBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);

        llViewOptionsHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        ivToggleAttributes.setImageResource(R.drawable.ic_expand_less_black_24dp);
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        ivToggleAttributes.setImageResource(R.drawable.ic_expand_more_black_24dp);
                        break;
                    default:
                        ivToggleAttributes.setImageResource(R.drawable.ic_expand_less_black_24dp);
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

//        cbShowBorder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Shape shape = shapeList.get(shapeList.size() - 1);
//
//                int thickness = (isChecked) ? shape.getTopicValue().getBorderWidth() : 0;
//                LineThinknessDecorator lineThinknessDecorator = new LineThinknessDecorator(shape, thickness);
//                lineThinknessDecorator.refreshShape();
//            }
//        });

        seekBarCircleX.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {

                int valueInDp = Util.dpToPx(value, MainActivity.this);
                Log.d(TAG, "->seekBarCircleX>>onProgressChanged>>valueInDp: " + value);
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        seekBarCircleY.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {

                int valueInDp = Util.dpToPx(value, MainActivity.this);
                Log.d(TAG, "->seekBarCircleY>>onProgressChanged>>valueInDp: " + value);
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        seekBarCircleRadius.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {

                int valueInDp = Util.dpToPx(value, MainActivity.this);
                Log.d(TAG, "->seekBarCircleRadius>>onProgressChanged>>valueInDp: " + value);
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        seekBarMeasureWidth.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {

                int valueInDp = Util.dpToPx(value, MainActivity.this);
                Log.d(TAG, "->seekBarMeasureWidth>>onProgressChanged>>valueInDp: " + value);
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        seekBarMeasureHeight.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {

                int valueInDp = Util.dpToPx(value, MainActivity.this);
                Log.d(TAG, "->seekBarMeasureHeight>>onProgressChanged>>valueInDp: " + value);
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        ivShapeBackgroundColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedShape != null) {
                    int selectedColor = selectedShape.getShapeProperty().getShapeBackgroundColor();
                    Log.d(TAG, "selectedColor>>ivShapeBackgroundColor: " + selectedColor);
                    showColorPicker(ShapeCommandType.SHAPE_BACKGROUND_COLOR, selectedColor, ivShapeBackgroundColor);
                }
            }
        });

        ivShapeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedShape != null) {
                    int selectedColor = selectedShape.getShapeProperty().getShapeColor();
                    Log.d(TAG, "selectedColor>>ivShapeColor: " + selectedColor);
                    showColorPicker(ShapeCommandType.SHAPE_COLOR, selectedColor, ivShapeColor);
                }
            }
        });
    }

    private void showColorPicker(final ShapeCommandType shapeCommandType, int selectedColor, final ImageView colorView) {
        new SpectrumDialog.Builder(MainActivity.this)
                .setColors(R.array.colors)
                .setSelectedColor(selectedColor)
                .setDismissOnColorSelected(true)
                .setOutlineWidth(1)
                .setOnColorSelectedListener(new SpectrumDialog.OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(boolean positiveResult, int color) {
                        if (positiveResult) {
                            colorView.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);

                            Log.d(TAG, "->showColorPicker>>onColorSelected>>color: " + color);
                            // Decorator pattern
//                            BackgroundColorDecorator backgroundColorDecorator = new BackgroundColorDecorator(circleViewGroup, color);
//                            backgroundColorDecorator.refreshShape();

                            // Observer pattern
                            Topic<Shape> topic = TopicIteratorManager.getInstance().getTopic(AddShapeCommand.class.getSimpleName() + selectedShape.getShapeProperty().getShapeId());
                            if (topic != null) {
                                Property property = ((CompoundShape) selectedShape).getShapeProperty();
                                if (shapeCommandType == ShapeCommandType.SHAPE_BACKGROUND_COLOR) {
                                    property.setShapeBackgroundColor(color);
                                } else if (shapeCommandType == ShapeCommandType.SHAPE_COLOR) {
                                    property.setShapeColor(color);
                                }
                                selectedShape.setShapeProperty(property);
                                topic.setValue(selectedShape);
                            }

                            // Command pattern
//                            CircleViewGroupProperty circleViewGroupProperty = (CircleViewGroupProperty) circleViewGroup.getProperty();
//                            circleViewGroupProperty.setBackgroundColor(color);
//                            Log.d("CommandManager", "MainActivity>>undo>>circleViewGroupProperty: " + circleViewGroupProperty.toString());
//                            circleCommandManager.executeCommand(new CircleCommand(CircleCommand.class.getSimpleName() + ">>Shape background change", circleViewGroup), circleViewGroupProperty);
                        }
                    }
                }).build().show(getSupportFragmentManager(), "ColorPicker");
    }

    private void refreshAttributes() {
        if (selectedShape != null) {
//            seekBarCircleX.setProgress();
//            seekBarCircleY.setProgress();
//            seekBarCircleRadius.setProgress();
//            seekBarMeasureWidth.setProgress();
//            seekBarMeasureHeight.setProgress();

            ivShapeBackgroundColor.getBackground().setColorFilter(selectedShape.getShapeProperty().getShapeBackgroundColor(), PorterDuff.Mode.SRC_ATOP);
            ivShapeColor.getBackground().setColorFilter(selectedShape.getShapeProperty().getShapeColor(), PorterDuff.Mode.SRC_ATOP);
        }
    }

    private void updateAttributeView() {
        if (selectedShape != null) {
            llBottomSheet.setVisibility(View.VISIBLE);
        } else {
            llBottomSheet.setVisibility(View.INVISIBLE);
        }

        refreshAttributes();
    }

    private void initSettings() {
        flourish = new Flourish.Builder(parentLayout)
                .setFlourishLayout(R.layout.layout_settings)
                .setFlourishAnimation(FlourishAnimation.BOUNCE)
                .setFlourishOrientation(FlourishOrientation.TOP_RIGHT)
                .setIsShowedOnStart(false)
                .setDuration(800L)
                .build();
        ((AnimatedTextView) flourish.flourishView.findViewById(R.id.toolbar_title)).setAnimatedText(getString(R.string.title_activity_settings), 0L);
        flourish.flourishView.findViewById(R.id.left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flourish.dismiss();
            }
        });

        flourish.flourishView.findViewById(R.id.rl_third_party_notice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLicense = new Intent(getApplicationContext(), LicenseActivity.class);
                startActivity(intentLicense);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (bottomSheetBehavior != null && bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            return;
        }
        if (flourish != null && flourish.isShowing()) {
            flourish.dismiss();
            return;
        }
        if (cycleMenuWidget != null && cycleMenuWidget.isOpened()) {
            cycleMenuWidget.close(true);
            return;
        }
        showAppCloseDialog(getResources().getString(R.string.dialog_close_app_title), getResources().getString(R.string.dialog_do_you_want_to_close_the_app));
    }

    private void showAppCloseDialog(String title, String message) {
        PopupDialog.create(MainActivity.this)
                .outsideTouchHide(false)
                .dismissTime(1000 * 5)
                .controller(AlertController.build()
                        .title(title + "\n")
                        .message(message)
                        .clickDismiss(true)
                        .negativeButton(getString(R.string.dialog_cancel), null)
                        .positiveButton(getString(R.string.dialog_ok), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        }))
                .show();
    }

    private void initTapSound() {
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        mStreamVolume = (float) mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC) / mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        mSoundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                if (status == 0) {
                    // TODO: load success
                }
            }
        });
        mSoundID = mSoundPool.load(this, R.raw.bubble_pop, 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ShapeManager.getInstance(MainActivity.this).destroyObject();
        TopicIteratorManager.getInstance().removeAllTopics();
    }

    @Override
    public void updateSubscriber(Shape item) {
        if (item != null) {
            Toast.makeText(MainActivity.this, TAG + item.getShapeProperty().getShapeId(), Toast.LENGTH_SHORT).show();
            if (item.getShapeProperty().isShapeSelected()) {
                selectedShape = item;

                // Unselect all other shapes
                for (int i = 0; i < shapeContainer.getChildCount(); i++) {
                    Log.d(TAG, "Added views " + i + " is " + shapeContainer.getChildAt(i).getClass().getSimpleName());
                    if (((Shape) shapeContainer.getChildAt(i)) instanceof CompoundShape && (((Shape) shapeContainer.getChildAt(i)).getShapeProperty().getShapeId() != item.getShapeProperty().getShapeId())) {
                        ((Shape) shapeContainer.getChildAt(i)).getShapeProperty().setShapeState(ShapeState.UNSELECTED);
                        ((Shape) shapeContainer.getChildAt(i)).refreshView();
                    }
                }
            } else {
                selectedShape = null;
            }

            // Update attribute view
            updateAttributeView();
        }
    }
}