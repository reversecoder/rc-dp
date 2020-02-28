package com.rc.shapemanager.pattern.structural.facade;

import android.content.Context;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.rc.shapemanager.R;
import com.rc.shapemanager.pattern.creational.builder.CircleViewGroupPropertyBuilder;
import com.rc.shapemanager.pattern.creational.builder.CompositeShapePropertyBuilder;
import com.rc.shapemanager.pattern.structural.bridge.CircleViewGroupProperty;
import com.rc.shapemanager.pattern.structural.bridge.CompositeShapeProperty;
import com.rc.shapemanager.pattern.structural.bridge.Property;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class PropertyKeeper {

    private Context context;

    public PropertyKeeper(Context context) {
        this.context = context;
    }

    public CircleViewGroupProperty getCircleViewGroupProperty() {
        return new CircleViewGroupPropertyBuilder()
                .setBackgroundColor(ContextCompat.getColor(context, R.color.default_highlight_background_color))
                .setBorderColor(ContextCompat.getColor(context, R.color.colorAccent))
                .setHighLightColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
                .setBorderWidth(10)
                .setRadius(80)
                .setMainIconDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.user, null))
                .setCloseIconDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.delete_checked, null))
                .setIdentity(1)
                .createCircleViewGroupProperty();
    }

    public CompositeShapeProperty getCompositeShapeProperty() {
        return new CompositeShapePropertyBuilder()
                .setBackgroundColor(ContextCompat.getColor(context, R.color.default_highlight_background_color))
                .setBorderColor(ContextCompat.getColor(context, R.color.colorAccent))
                .setBorderWidth(10)
                .setIdentity(1)
                .createCompositeProperty();
    }
}