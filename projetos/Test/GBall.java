package projet.jeu.bille;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
//import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class GBall extends Drawable{

    private Ball element;

    public GBall(Ball aRepresenter,ViewSpace view) {
            super();
            this.element = aRepresenter;
    }


    @Override
    public void draw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.RED);
			canvas.drawCircle((int)element.getX(),(int)element.getY(),element.getRadius(), paint);
    }


	@Override
	public int getOpacity() {
		return 0;
	}


	@Override
	public void setAlpha(int alpha) {
	}


	@Override
	public void setColorFilter(ColorFilter cf) {
	}
}
