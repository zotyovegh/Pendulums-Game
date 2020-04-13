package com.example.pendulumtestjava;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import static android.content.ContentValues.TAG;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DrawingPath extends View {
    private Paint paint;
    private Path path;
    private List<Point> points = new ArrayList<>();
    private Point point;
    private float x2;
    private float y2;

    public DrawingPath(Context context) {
        super(context);
    }

    public DrawingPath(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        path = new Path();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);


    }

    public DrawingPath(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawPath(path, paint);
        

    }
    public void setVariables(double x, double y)
    {
        point  = new Point();
        this.x2 = (float)x + 30;
        this.y2 = (float)y + 30;
        point.set(((int)x+30),((int)y+30));
        points.add(point);

        if(points.size() >2) {
            path.moveTo(points.get(points.size() - 2).x, points.get(points.size() - 2).y);
            path.lineTo(points.get(points.size() - 1).x, points.get(points.size() - 1).y);
        }

//        if(points.size() >2)
//        {
//            points.remove(points.get(0));
//        }
        Log.i(TAG, "asdf" + points.size());
        invalidate();
    }




}
