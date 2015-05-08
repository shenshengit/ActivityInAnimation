package com.example.activityinanimation;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class ActivityA extends Activity {
	private ImageView iv;
	private float viewWhithBili;
	private float viewHeightBili;
	int vLeft;
	int vBottom;
	int vRight;
	
	int aWidth;
	int aHeight;
	int aTop;
	int aleft;
	int abottom;
	int aright;
	
	int bWidth;
	int bHeight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_activity);
		
		//得到传过来的数据
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		aTop = bundle.getInt("aTop");
		aleft = bundle.getInt("aleft");
		abottom = bundle.getInt("abottom");
		aright = bundle.getInt("aright");
		aWidth = bundle.getInt("aWidth");
		aHeight = bundle.getInt("aHeight");
		
		iv = (ImageView) findViewById(R.id.a_imageView1);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(aWidth,aHeight);
		lp.setMargins(-1, aTop - 60, -1, -1);//高度要减去状态栏，？？？这里状态栏需呀一个方法来动态获取
		iv.setLayoutParams(lp);

		//得到图片的缩放比例
		viewWhithBili = (float) getScreenWidth() / aWidth;
		
		Animation translate = new TranslateAnimation(0.0f, 0.0f, 0.0f,
				-((aTop - 60)-((aHeight*viewWhithBili -aHeight )/2)));
		translate.setDuration(1000);
		
		Animation scale = new ScaleAnimation(1.0f, viewWhithBili, 1.0f,
				viewWhithBili, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		scale.setDuration(900);
		scale.setStartOffset(100);
		
		AnimationSet set = new AnimationSet(true);
		set.addAnimation(scale);
		set.addAnimation(translate);
		set.setInterpolator(new DecelerateInterpolator());
		set.setFillAfter(true);
		set.setAnimationListener(new AnimationListener() {
			LinearLayout.LayoutParams lp;
			@Override
			public void onAnimationStart(Animation animation) {
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				//在动画结束之后把view的位置改到动画结束后的位置
				lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, (int) (aHeight*viewWhithBili));
				lp.setMargins(0, 0, 0, 0);
				
				iv.setLayoutParams(lp);
				iv.clearAnimation();
				Log.i("ActivityA", "动画完成后"+ getScreenWidth()+"|" +(int) (aHeight*viewWhithBili));
			}
		});
		iv.setAnimation(set);

		Log.i("ActivityA", "测试传过来的View的宽度" + aWidth + "高度" + aHeight + "上边距"
				+ aTop);
		iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// Log.i("ActivityA", "View的宽度" + bWidth + "高度" + bHeight +
				// "上边距"
				// + (aHeight - aHeight) / 2);
				// viewWhithBili = (float) aWidth / getScreenWidth();
				// viewHeightBili = (float) aHeight / bHeight;
			}
		});

		// Log.i("ActivityA", "测试能吧能那到View的宽度"+pic.getmViewWidth()+"");

		 TextView tv = (TextView) findViewById(R.id.tv);

		 Animation am1 = AnimationUtils.loadAnimation(this, R.anim.tx_in);
		 tv.setAnimation(am1);
	}

	/**
	 * 获取屏幕的宽度方法
	 * 
	 * @return
	 */
	private int getScreenWidth() {
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);// this指当前activity
		return dm.widthPixels;
	}

	/**
	 * 获取屏幕的高度方法
	 * 
	 * @return
	 */
	private int getScreenHeight() {
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);// this指当前activity
		return dm.heightPixels;
	}
}
