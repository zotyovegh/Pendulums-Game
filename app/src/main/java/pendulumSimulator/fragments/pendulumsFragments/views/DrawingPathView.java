package pendulumSimulator.fragments.pendulumsFragments.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DrawingPathView extends View {
    private Paint paint;
    private float x, y, tempX, tempY;
    private ArrayList<Float> array = new ArrayList<>();
    private int counter = 0;
    private boolean reset = false;

    public ArrayList<Float> getArray() {
        return array;
    }

    public DrawingPathView(Context context) {
        super(context);
    }

    public DrawingPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setStrokeWidth(5);
    }

    public DrawingPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (reset) {
            array = new ArrayList<>();
            reset = false;
        } else {

            canvas.drawLines(convert(array), paint);
        }
        invalidate();
    }

    public void setVariables(double x, double y, int trace, int color, boolean endless) {
        this.paint.setColor(color);
        this.x = (float) x + 30;
        this.y = (float) y + 30;
        if (endless == false) {
            if (array.size() > trace) {
                for (int i = 0; i < 4; i++) {
                    array.remove(0);
                }
            }
        }

        if (counter == 0) {
            tempX = this.x;
            tempY = this.y;
        }
        array.add(tempX);
        array.add(tempY);
        array.add(this.x);
        array.add(this.y);

        tempX = this.x;
        tempY = this.y;

        counter++;
        invalidate();
    }

    public void setPath(ArrayList<Float> points)
    {
        array = points;
    }

    public float[] convert(ArrayList<Float> f) {
        float[] result = new float[f.size()];

        for (int i = 0; i < f.size(); i++) {
            result[i] = f.get(i);
        }
        return result;
    }

    public void reset() {
        reset = true;
    }
}
