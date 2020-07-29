package projet.jeu.bille;

import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;

public class ViewSpace extends View implements Runnable, OnTouchListener{
	final static int UNITE=10;
	private Game game;
	private int delay = 70;
	private boolean isStarted=false;
	private Thread animator = null;
	private boolean alreadyInitiate = false;

	
	public ViewSpace(Context context, Game game) {
		super(context);
		this.game = game;
		setOnTouchListener(this);
	}

    public void initiate(){
		game.setLevelManager(new LevelManager(game.getLevelManager().getLevelActual(),game.getSounds(),
										game.getLevels(),
										game.getLargeur(),game.getHauteur()));
        postInvalidate();
    }

	@Override
	public void run() {
    	while(game.getLevelManager().getLevelActual()<=Game.levelMax){
    			if(!alreadyInitiate){
    				alreadyInitiate=true;
    				initiate();
    			}
        		if (isStarted){
        			isStarted=false;
    				alreadyInitiate=false;
			        Space space = game.getLevelManager().getSpace();
			        Ball ball = space.getBall();
				/*	while(GameRules.inSpace(space.getBall(), game.getLargeur(),game.getHauteur())
						&& !GameRules.isLevelWin(ball,space.getArrivee())){*/
			        while(!GameRules.isLevelWin(ball,space.getArrivee())){
					//	if(GameRules.inSpaceObstacles(ball,game.getLargeur(),game.getHauteur())){
							//Log.d("VS",game.getLevel()+"->"+(space.getBall().getX()+space.getBall().getDirX())/Game.UNITE+","+(ball.getY()+ball.getDirY())/Game.UNITE);
							if(!space.getObstacle().isObstacle((int)((space.getBall().getX()+space.getBall().getDirX())/UNITE),
																(int)((space.getBall().getY()+space.getBall().getDirY())/UNITE))){
								ball.update();
					    		try { 
					    			Thread.sleep(delay); 
					    		}
					    		catch (InterruptedException e) { 
					    			Thread.currentThread().interrupt(); 
					    		}
					    		if(space.collideBallObstacles()){
					    			game.getLevelManager().getSoundCollision().start();
					    			ball.setDir(space.newDir());
					    		}
							}
					//	}else{
							ball.update();
				    		try { 
				    			Thread.sleep(delay); 
				    		}
				    		catch (InterruptedException e) { 
				    			Thread.currentThread().interrupt(); 
				    		}
						//}
						postInvalidate();
					}
					if(GameRules.isLevelWin(ball,space.getArrivee())){
						game.getLevelManager().setLevelActual();
					}else{
						Log.d("VS","meme level");
					/*		handler.post(new Runnable() {
							@Override
							public void run() {
					    		showDialog(ID_ENERVEE_DIALOG);
							}
						});*/
						//Pop Up quitter ou recommencer
					}
				}
	    	}
	}

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.restore();
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.WHITE);
		canvas.drawPaint(paint);
        Space space = game.getLevelManager().getSpace();   
		if(space!=null){
			//game.getChronometer().setBackgroundColor(Color.BLACK);
			game.getChronometer().draw(canvas);
			GWinnerPoint arriveeAdessiner = new GWinnerPoint(space.getArrivee());
	        arriveeAdessiner.draw(canvas);
	
	        GBall ballOnDraw = new GBall(space.getBall(),this);
	        ballOnDraw.draw(canvas);
	        
	        GObstacles obstacleOnDraw = new GObstacles(space.getObstacle(),this);
	        obstacleOnDraw.draw(canvas);
		}
    }

	public void startIt() {
        animator = new Thread(this);
        animator.start();
    }

	@Override
	public boolean onTouch(View arg0, MotionEvent event) {
	//	int UNITE = Game.UNITE;
		if(MotionEvent.ACTION_UP == event.getAction() && !isStarted){
		    //	soundLevel.start();
			    isStarted=true;
			}
			if(isStarted){
				Ball ball=game.getLevelManager().getSpace().getBall();
				if(MotionEvent.ACTION_MOVE == event.getAction()){
					if (!(event.getX()/UNITE <= (ball.getX()+2*ball.getRadius())/UNITE
					&& event.getX()/UNITE >= (ball.getX()-2*ball.getRadius())/UNITE
					&& event.getY()/UNITE <= (ball.getY()+2*ball.getRadius())/UNITE
					&& event.getY()/UNITE >= (ball.getY()-2*ball.getRadius())/UNITE)){
						game.getLevelManager().getSpace().getObstacle().setObstacles((int)(event.getX()/(UNITE)),(int)(event.getY()/(UNITE)));
						postInvalidate();
					}
				}
			}
			return true;
	

	}

	
}

