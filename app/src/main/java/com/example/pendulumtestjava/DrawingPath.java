package com.example.pendulumtestjava;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import static android.content.ContentValues.TAG;
import androidx.annotation.Nullable;

public class DrawingPath extends View {
    private Paint paint;
    private float x2, temp1x;
    private float y2, temp1y;
    private float[] p = new float[50000];
    private int flag = 0;
    private int counter = 0;

    public DrawingPath(Context context) {
        super(context);
    }

    public DrawingPath(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
    }

    public DrawingPath(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawLines(p, paint);
        invalidate();
    }
    public void setVariables(double x, double y) {
        this.x2 = (float) x + 30;
        this.y2 = (float) y + 30;

        if (flag > p.length - 1)
        {
            flag = 0;
        }

        if(counter == 0)
        {
            temp1x = x2;
            temp1y = y2;
        } else if(counter != 0)
        {
            p[flag] = temp1x;
            flag++;
            p[flag] =temp1y;
            flag++;
            p[flag] = x2;
            flag++;
            p[flag] = y2;
            flag++;

            temp1x = x2;
            temp1y = y2;
        }
        counter++;
    invalidate();
    }
}
