/**
 * ARViewActivitySimple.java
 * Example SDK Internal
 *
 * Created by Arsalan Malik on 08.03.2011
 * Copyright 2011 metaio GmbH. All rights reserved.
 *
 */

package com.metaio.MobileSDKExample.simple;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.metaio.MobileSDKExample.MenuActivity;
import com.metaio.MobileSDKExample.R;
import com.metaio.unifeye.UnifeyeDebug;
import com.metaio.unifeye.ndk.IUnifeyeMobileCallback;
import com.metaio.unifeye.ndk.IUnifeyeMobileGeometry;
import com.metaio.unifeye.ndk.Vector3d;
import com.metaio.unifeye.ndk.Vector4d;
import java.lang.Math;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * EXAMPLE 1
 * 
 * This is a simple AR activity which shows 2D planar marker tracking with
 * animations. The target pattern and the model is MetaioMan.
 * 
 * Please visit the following link for a detailed explanation. {@link http
 * ://docs.metaio.com/bin/view/Main/HelloAugmentedWorldExample}
 * 
 * @author arsalan.malik, tim.oppermann
 * 
 */

public class HelloAugmentedWorldActivity extends ARViewActivity {
	private MobileSDKCallbackHandler mMobileSDKCallbackHandler;

	@Override
	protected int getGUILayout() {
		// TODO Auto-generated method stub
		return R.layout.battlemenu2;
	}

	@Override
	protected IUnifeyeMobileCallback getMobileSDKCallbackHandler() {
		return mMobileSDKCallbackHandler;
	}

	/**
	 * The geometry to be displayed
	 */
	private IUnifeyeMobileGeometry mDarthVader, mMetaioMan;
	private IUnifeyeMobileGeometry mLightSaber;
	private IUnifeyeMobileGeometry mYoshi;
	private IUnifeyeMobileGeometry mGameBoy;
	private IUnifeyeMobileGeometry mSelectedGeometry, mEnemyGeometry;
	private IUnifeyeMobileGeometry mButton;
	private Timer timer;
	private TimerTask task;
	/**
	 * Tracking file you like to use. The file must be within the assets folder
	 * of this project. The reference image and explanation can be found at
	 * http://docs.metaio.com/bin/view/Main/UnifeyeMobileTrackingConfiguration
	 */
	private final String mTrackingDataFileName = "TrackingData_Marker.xml";

	/**
	 * This tracking configuration used Markerless 3D tracking. You can use the
	 * meatioman_target to veriy the tracking
	 */
	private final String mTrackingDataML3D = "TrackingData_ML3D.xml";

	/**
	 * This tracking configuration used ID Markers for tracking
	 */
	private final String mTrackingDataMarker = "TrackingData_Marker.xml";

	/**
	 * Button, layouts and bars to be displayed
	 */
	private ImageView playerIcon, enemyIcon;
	private ProgressBar bar, bar2;
	private TextView selectYourChar, timeLeft;
	private LinearLayout buttons, playerHealth, enemyHealth;
	private CountDownTimer cdTimer, enemyTimer;
	private boolean charSelected, gameOver;
	private String choice, enemyChoice;
	private Random generator = new Random();
	private int num;

	// Take the model selected by the player and place it on the appropriate
	// side
	protected void showGeometryPlayer(IUnifeyeMobileGeometry geometry,
			IUnifeyeMobileGeometry weapon) {
		geometry.setMoveRotation(new Vector4d(1f, 0f, 0f, 1.57f));
		geometry.setMoveRotation(new Vector4d(0f, 0f, 1f, 1.57f), true);
		geometry.setMoveTranslation(new Vector3d(0, 100, 0));
		weapon.setVisible(true);
		weapon.setMoveRotation(new Vector4d(1f, 0f, 0f, 1.57f));
		weapon.setMoveRotation(new Vector4d(0f, 0f, 1f, 1.57f), true);
		weapon.setMoveTranslation(new Vector3d(0, 100, 0));
		if (geometry.equals(mDarthVader)) {
			geometry.startAnimation("Stand", true);
			weapon.startAnimation("Stand", true);
		} else if (geometry.equals(mYoshi)) {
			geometry.startAnimation("stand", true);
			weapon.startAnimation("stand", true);

		}
	}

	// Take the enemy model and place it on the opposite side
	protected void showGeometryEnemy(IUnifeyeMobileGeometry geometry,
			IUnifeyeMobileGeometry weapon) {
		geometry.setMoveRotation(new Vector4d(1f, 0f, 0f, 1.57f));
		geometry.setMoveRotation(new Vector4d(0f, 0f, 1f, -1.57f), true);
		geometry.setMoveTranslation(new Vector3d(0, -100, 0));
		weapon.setVisible(true);
		weapon.setMoveRotation(new Vector4d(1f, 0f, 0f, 1.57f));
		weapon.setMoveRotation(new Vector4d(0f, 0f, 1f, -1.57f), true);
		weapon.setMoveTranslation(new Vector3d(0, -100, 0));
		if (geometry.equals(mDarthVader)) {
			geometry.startAnimation("Stand", true);
		} else if (geometry.equals(mYoshi)) {
			geometry.startAnimation("stand", true);
		}
	}

	// Handlers needed to help detect when an animation ends
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mMobileSDKCallbackHandler = new MobileSDKCallbackHandler();
		charSelected = false;
		gameOver = false;
		choice = "Attack";
	}

	/**
	 * Gets called by the super-class after the GLSurface has been created. It
	 * runs on the OpenGL-thread.
	 * 
	 * Initial loading of characters for the user to choose from
	 */
	@Override
	protected void loadUnifeyeContents() {
		try {

			// load buttons and bars
			playerIcon = (ImageView) findViewById(R.id.imageView2);
			enemyIcon = (ImageView) findViewById(R.id.imageView1);
			timeLeft = (TextView) findViewById(R.id.textView2);
			bar = (ProgressBar) findViewById(R.id.progressBar1);
			bar2 = (ProgressBar) findViewById(R.id.progressBar2);
			buttons = (LinearLayout) findViewById(R.id.linearLayout1);
			playerHealth = (LinearLayout) findViewById(R.id.linearLayout3);
			enemyHealth = (LinearLayout) findViewById(R.id.linearLayout2);
			selectYourChar = (TextView) findViewById(R.id.textView1);

			// Load Tracking data
			loadTrackingData(mTrackingDataFileName);

			// Load all geometry
			mDarthVader = loadGeometry("darthvader/darthvader.md2");
			mDarthVader.setVisible(true);
			mDarthVader.setMoveRotation(new Vector4d(1f, 0f, 0f, 1.57f));
			mDarthVader.setMoveRotation(new Vector4d(0f, 0f, 1f, 3.14f), true);
			mDarthVader.setMoveTranslation(new Vector3d(0, 20, 0));
			mDarthVader.startAnimation("Stand", true);

			mLightSaber = loadGeometry("darthvader/weapon.md2");
			mLightSaber.setVisible(false);
			mLightSaber.setMoveRotation(new Vector4d(1f, 0f, 0f, 1.57f));
			mLightSaber.setMoveRotation(new Vector4d(0f, 0f, 1f, 3.14f), true);
			mLightSaber.setMoveTranslation(new Vector3d(0, 20, 0));
			mLightSaber.startAnimation("Stand", true);

			mYoshi = loadGeometry("yoshi/yoshi.md2");
			mYoshi.setVisible(true);
			mYoshi.setMoveRotation(new Vector4d(1f, 0f, 0f, 1.57f));
			mYoshi.setMoveRotation(new Vector4d(0f, 0f, 1f, 3.14f), true);
			mYoshi.setMoveTranslation(new Vector3d(0, -20, 0));
			mYoshi.startAnimation("stand", true);

			mGameBoy = loadGeometry("yoshi/weapon.md2");
			mGameBoy.setVisible(true);
			mGameBoy.setMoveRotation(new Vector4d(1f, 0f, 0f, 1.57f));
			mGameBoy.setMoveRotation(new Vector4d(0f, 0f, 1f, 3.14f), true);
			mGameBoy.setMoveTranslation(new Vector3d(0, -20, 0));
			mGameBoy.startAnimation("stand", true);

		} catch (Exception e) {
			UnifeyeDebug.printStackTrace(Log.ERROR, e);
		}
	}

	// If any model is touched, do something
	@Override
	protected void onGeometryTouched(IUnifeyeMobileGeometry geometry) {
		// TODO Auto-generated method stub
		if (!charSelected) {
			if (geometry.equals(mDarthVader)) {
				mSelectedGeometry = mDarthVader;
				mEnemyGeometry = mYoshi;
				playerIcon.setImageResource(R.drawable.darthicon);
				enemyIcon.setImageResource(R.drawable.yoshiicon);
				showGeometryPlayer(mDarthVader, mLightSaber);
				showGeometryEnemy(mYoshi, mGameBoy);
				num = generator.nextInt(100000) % 3;
				switch (num) {
				case 0:
					enemyChoice = "Attack";
					break;
				case 1:
					enemyChoice = "Defend";
					break;
				case 2:
					enemyChoice = "Special";
					break;
				}

			}
			if (geometry.equals(mYoshi)) {
				mSelectedGeometry = mYoshi;
				mEnemyGeometry = mDarthVader;
				playerIcon.setImageResource(R.drawable.yoshiicon);
				enemyIcon.setImageResource(R.drawable.darthicon);
				showGeometryPlayer(mYoshi, mGameBoy);
				showGeometryEnemy(mDarthVader, mLightSaber);
				num = generator.nextInt(100000) % 3;
				switch (num) {
				case 0:
					enemyChoice = "Attack";
					break;
				case 1:
					enemyChoice = "Defend";
					break;
				case 2:
					enemyChoice = "Special";
					break;
				}
			}
			buttons.setVisibility(View.VISIBLE);
			playerHealth.setVisibility(View.VISIBLE);
			playerIcon.setVisibility(View.VISIBLE);
			enemyHealth.setVisibility(View.VISIBLE);
			enemyIcon.setVisibility(View.VISIBLE);
			selectYourChar.setVisibility(View.GONE);
			
			repeat(6000, 1000);
			charSelected = true;
			timeLeft.setTextColor(Color.BLACK);
			
			

		}
	}

	private void repeat(final long time, final long interval) {
		cdTimer = new CountDownTimer(time, interval) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				timeLeft.setText("Time: " + millisUntilFinished / 1000 + " s");
			}

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				if (!gameOver) {
					num = generator.nextInt(100000) % 3;
					switch (num) {
					case 0:
						enemyChoice = "Attack";
						break;
					case 1:
						enemyChoice = "Defend";
						break;
					case 2:
						enemyChoice = "Special";
						break;
					}
					makeMove(mSelectedGeometry);
					repeat(time, interval);
				}
			}
		}.start();
	}

	private void makeMove(IUnifeyeMobileGeometry player) {

		if (choice.equals("Attack")) {
			if (mSelectedGeometry.equals(mDarthVader)) {
				if (enemyChoice.equals("Attack")) {
					Toast.makeText(this, "Enemy chose to Attack too. Nothing happens!",
							Toast.LENGTH_SHORT).show();
					mSelectedGeometry.startAnimation("Stand", true);
					mLightSaber.startAnimation("Stand", true);
					mEnemyGeometry.startAnimation("stand", true);
				} else if (enemyChoice.equals("Defend")) {
					Toast.makeText(this,
							"Enemy chose to Defend. You've been hit!",
							Toast.LENGTH_SHORT).show();
					bar2.setProgress(bar2.getProgress() - 20);
					mSelectedGeometry.startAnimation("CrPain", false);
					mLightSaber.startAnimation("CrPain", false);
					mEnemyGeometry.startAnimation("crstand", true);
				} else {
					Toast.makeText(this,
							"Enemy chose Special, but your attack hits first!",
							Toast.LENGTH_SHORT).show();
					bar.setProgress(bar.getProgress() - 20);
					mDarthVader.startAnimation("Attack", false);
					mLightSaber.startAnimation("Attack", false);
					// icon.setImageResource(R.drawable.darthicon);
					mEnemyGeometry.startAnimation("paina", false);
				}
			} else if (mSelectedGeometry.equals(mYoshi)) {
				if (enemyChoice.equals("Attack")) {
					Toast.makeText(this, "Enemy chose Attack too. No Damage.",
							Toast.LENGTH_SHORT).show();
					mSelectedGeometry.startAnimation("stand", true);
					mEnemyGeometry.startAnimation("Stand", true);
					mLightSaber.startAnimation("Stand", true);
				} else if (enemyChoice.equals("Defend")) {
					Toast.makeText(this,
							"Enemy chose Defend. You've been hit!",
							Toast.LENGTH_SHORT).show();
					bar2.setProgress(bar2.getProgress() - 20);
					mSelectedGeometry.startAnimation("paina", false);
					mEnemyGeometry.startAnimation("CrStnd", true);
					mLightSaber.startAnimation("CrStnd", true);
				} else {
					Toast.makeText(this,
							"Enemy chose Special, but your attack hits first!",
							Toast.LENGTH_SHORT).show();
					bar.setProgress(bar.getProgress() - 20);
					mYoshi.startAnimation("attack", false);
					mEnemyGeometry.startAnimation("CrPain", false);
					mLightSaber.startAnimation("CrPain", false);
				}
			}

		} else if (choice.equals("Defend")) {
			if (mSelectedGeometry.equals(mDarthVader)) {
				if (enemyChoice.equals("Attack")) {
					bar.setProgress(bar.getProgress() - 20);
					Toast.makeText(this,
							"Enemy chose Attack. They've been damaged!",
							Toast.LENGTH_SHORT).show();
					mSelectedGeometry.startAnimation("CrStand", true);
					mLightSaber.startAnimation("CrStand", true);
					mEnemyGeometry.startAnimation("paina", false);
				} else if (enemyChoice.equals("Defend")) {
					Toast.makeText(this,
							"Enemy chose Defend too. Nothing happens!",
							Toast.LENGTH_SHORT).show();
					mSelectedGeometry.startAnimation("CrStnd", false);
					mEnemyGeometry.startAnimation("crstand", true);
					mLightSaber.startAnimation("CrStnd", true);
				} else {
					Toast.makeText(this,
							"Enemy chose Special. You've been hit!",
							Toast.LENGTH_SHORT).show();
					bar2.setProgress(bar2.getProgress() - 20);
					mSelectedGeometry.startAnimation("CrPain", false);
					mLightSaber.startAnimation("CrPain", false);
					mEnemyGeometry.startAnimation("backflip", false);
					// icon.setImageResource(R.drawable.darthicon);
				}
			} else if (mSelectedGeometry.equals(mYoshi)) {
				if (enemyChoice.equals("Attack")) {
					bar.setProgress(bar.getProgress() - 20);
					Toast.makeText(this,
							"Enemy chose Attack. They've been damaged!",
							Toast.LENGTH_SHORT).show();
					mSelectedGeometry.startAnimation("crstand", true);
					mEnemyGeometry.startAnimation("CrPain", false);
					mLightSaber.startAnimation("CrPain", false);
				} else if (enemyChoice.equals("Defend")) {
					Toast.makeText(this,
							"Enemy chose Defend too. Nothing happens!",
							Toast.LENGTH_SHORT).show();
					mSelectedGeometry.startAnimation("crstand", true);
					mLightSaber.startAnimation("CrStand", true);
					mEnemyGeometry.startAnimation("CrStand", true);
				} else {
					Toast.makeText(this,
							"Enemy chose Special. You've been hit!",
							Toast.LENGTH_SHORT).show();
					bar2.setProgress(bar2.getProgress() - 20);
					mSelectedGeometry.startAnimation("paina", false);
					mLightSaber.startAnimation("Taunt", false);
					mEnemyGeometry.startAnimation("Taunt", false);
				}

			}
		} else if (choice.equals("Special")) {
			if (mSelectedGeometry.equals(mDarthVader)) {
				if (enemyChoice.equals("Attack")) {
					bar2.setProgress(bar2.getProgress() - 20);
					Toast.makeText(this,
							"Enemy chose Attack. You've been damaged!",
							Toast.LENGTH_SHORT).show();
					mSelectedGeometry.startAnimation("CrPain", false);
					mLightSaber.startAnimation("CrPain", false);
					mEnemyGeometry.startAnimation("attack", false);
				} else if (enemyChoice.equals("Defend")) {
					bar.setProgress(bar.getProgress() - 20);
					Toast.makeText(this,
							"Enemy chose Defend. Your Special hits anyways!",
							Toast.LENGTH_SHORT).show();
					mSelectedGeometry.startAnimation("Taunt", false);
					mEnemyGeometry.startAnimation("paina", false);
					mLightSaber.startAnimation("Taunt", false);
				} else {
					Toast.makeText(this,
							"Enemy chose Special too. Nothing happens!",
							Toast.LENGTH_SHORT).show();
					mDarthVader.startAnimation("Stand", true);
					mLightSaber.startAnimation("Stand", true);
					mEnemyGeometry.startAnimation("stand", true);
					// icon.setImageResource(R.drawable.darthicon);

				}

			} else if (mSelectedGeometry.equals(mYoshi)) {
				if (enemyChoice.equals("Attack")) {
					bar2.setProgress(bar2.getProgress() - 20);
					Toast.makeText(this,
							"Enemy chose Attack. You've been damaged!",
							Toast.LENGTH_SHORT).show();
					mSelectedGeometry.startAnimation("paina", false);
					mLightSaber.startAnimation("Attack", false);
					mEnemyGeometry.startAnimation("Attack", false);
				} else if (enemyChoice.equals("Defend")) {
					bar.setProgress(bar.getProgress() - 20);
					Toast.makeText(this,
							"Enemy chose Defend. Your Special hits anyways!",
							Toast.LENGTH_SHORT).show();
					mSelectedGeometry.startAnimation("backflip", false);
					mEnemyGeometry.startAnimation("CrPain", false);
					mLightSaber.startAnimation("CrPain", false);
				} else {
					Toast.makeText(this,
							"Enemy chose Special too. Nothing happens!",
							Toast.LENGTH_SHORT).show();
					mDarthVader.startAnimation("Stand", true);
					mLightSaber.startAnimation("Stand", true);
					mSelectedGeometry.startAnimation("stand", true);
					// icon.setImageResource(R.drawable.darthicon);

				}
			}
		}
		if (bar.getProgress() <= 0) {
			gameOver = true;
			Toast.makeText(this, "You Win!", Toast.LENGTH_SHORT).show();
			cdTimer.cancel();
			Intent intent = new Intent(this, MenuActivity.class);
			startActivity(intent);
		} else if (bar2.getProgress() <= 0) {
			gameOver = true;
			Toast.makeText(this, "You Lose...", Toast.LENGTH_SHORT).show();
			cdTimer.cancel();
			Intent intent = new Intent(this, MenuActivity.class);
			startActivity(intent);
		}
	}

	// Needed to detect when an animation has ended so we can return to their
	// 'idle' pose
	// Still can't get it to work for some reason
	private final class MobileSDKCallbackHandler extends IUnifeyeMobileCallback {

		/**
		 * Called by the MobileSDK when an animation has finished.
		 * 
		 * Note: Runs on the OpenGL-Thread
		 * 
		 * @param geometry
		 *            Reference to the geometry that was animated
		 * @param animationName
		 *            Name of the animation that has been played.
		 */
		public void onAnimationEnd(IUnifeyeMobileGeometry geometry,
				String animationName) {
			UnifeyeDebug.log("MobileSDKCallbackHandler.onAnimationEnd: "
					+ animationName);

			/*
			 * Decide which animation should be played next. If no special
			 * conditions apply, it will be the 'idle' animation.
			 */

			geometry.startAnimation("Stand", true);

		}
	}

	// If the attack button is clicked, play the attack animation as well as the
	// enemy pain animation
	public void onAttackClicked(final View eventSource) {
		choice = "Attack";

	}

	// Defend, only plays animation right now
	public void onDefendClicked(final View eventSource) {
		// showGeometry(mMetaioMan);
		choice = "Defend";
	}

	// Special, only plays animation right now
	public void onSpecialClicked(final View eventSource) {
		// showGeometry(mMetaioMan);
		choice = "Special";
	}

}
