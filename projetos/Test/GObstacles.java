package projet.jeu.bille;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class GObstacles extends Drawable {
	

	private Drawable dObstacle;
	Obstacles obstacle;
	public GObstacles(Obstacles obs, ViewSpace view) {
		obstacle = obs;
		this.dObstacle = view.getResources().getDrawable(R.drawable.obstacle);
	}

	@Override
	public void draw(Canvas canvas) {
		int UNITE=ViewSpace.UNITE;
		Paint style = new Paint();
	    style.setColor(Color.GREEN);
		if (obstacle instanceof ObstacleTable){
				for(int i =0; i<((ObstacleTable) obstacle).getGrid().length-1;i++){
					for(int j=0; j<((ObstacleTable) obstacle).getGrid()[0].length-1;j++){
						if(((ObstacleTable) obstacle).isObstacle(i,j)){
							dObstacle.setBounds(new Rect(i*UNITE,j*UNITE,(i+1)*UNITE,(j+1)*UNITE));
							dObstacle.draw(canvas);
						}
					}
				}
		}else{//ObstacleVector
			
		}
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
