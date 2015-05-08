package com.example.activityinanimation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;


public class MainActivity extends Activity {
	ImageView imageView;
	int top;
	int left;
	int bottom;
	int right;
	int aWidth;
	int aHeight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		imageView = (ImageView) findViewById(R.id.imageView1);
		
		//开一个线程来获得view距离屏幕的上，左，下，右坐标值，这要就得到了宽度和高度
		imageView.post(new Runnable() {

			@Override
			public void run() {
				Rect viewRect = new Rect();
				imageView.getGlobalVisibleRect(viewRect);
				top = viewRect.top;
				left = viewRect.left;
				bottom = viewRect.bottom;
				right = viewRect.right;

				aWidth = right - left;
				aHeight = bottom - top;
				
				System.out.println(viewRect);
				Log.v("-------创建时", top + ";"+left+ ";"+bottom+ ";"+right+ ";"+aWidth+ ";"+aHeight);
				
			}
		});

		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {

				//点击的时候，把view的坐标信息传给下个Activity
				
				Rect viewRect = new Rect();
				imageView.getGlobalVisibleRect(viewRect);
				top = viewRect.top;
				left = viewRect.left;
				bottom = viewRect.bottom;
				right = viewRect.right;
				
				Intent intent = new Intent(MainActivity.this, ActivityA.class);
				intent.putExtra("aTop", top);
				intent.putExtra("aleft", left);
				intent.putExtra("abottom", bottom);
				intent.putExtra("aright", right);
				intent.putExtra("aWidth", aWidth);
				intent.putExtra("aHeight", aHeight);
				startActivity(intent);
				
				//Activity 切换动画
				overridePendingTransition(R.anim.activity, -1);
				
			}

			// 得到当前View的宽度

			
/*			  TranslateAnimation animation = null;
			  
			  if (bbb) { animation = new TranslateA   nimation(
			  Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1,
			  Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
			  0.0f); bbb = !bbb; } else { animation = new TranslateAnimation(
			  Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, -1,
			  Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
			  0.0f); bbb = !bbb; } // Animation animation = new //
			  TranslateAnimation(0.0f,100.0f,0.0f,0.0f);
			  animation.setDuration(1000); animation.setFillAfter(true);
			  animation.setInterpolator(new OvershootInterpolator()); animation
			  .setAnimationListener(new Animation.AnimationListener() {
			  
			  @Override public void onAnimationStart(Animation animation) { }
			  
			  @Override public void onAnimationRepeat(Animation animation) { }
			  
			  @Override public void onAnimationEnd(Animation animation) { int
			  left = imageView.getLeft(); int top = imageView.getTop(); int
			  right = imageView.getRight(); int bottom = imageView.getBottom();
			  int width = right - left; int height = bottom - top;
			  v.clearAnimation(); if(!bbb){ v.layout(left+width, top,
			  right+width , bottom); }else{
			  
			  v.layout(left-width, top, right-width , bottom); } } });
			  v.startAnimation(animation); }*/
			 
		});

	}

}
