package projet.jeu.bille;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

public class GWinnerPoint extends Drawable{

    private WinnerPoint element;
            
    public GWinnerPoint(WinnerPoint aRepresenter) {
            super();
            this.element = aRepresenter;
    }

    @Override
    public void draw(Canvas canvas) {
            Paint style = new Paint();
            style.setColor(Color.BLUE);
            canvas.drawCircle((int)element.getX(),(int)element.getY(),element.getRadius(), style);
    }

	@Override
	public int getOpacity() {
		return 0;
	}

	@Override
	public void setAlpha(int arg0) {

	}

	@Override
	public void setColorFilter(ColorFilter arg0) {
	
	}

}
