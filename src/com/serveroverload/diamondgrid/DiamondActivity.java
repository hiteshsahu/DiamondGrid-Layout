package com.serveroverload.diamondgrid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.gridawesome.R;

@SuppressLint("NewApi")
public class DiamondActivity extends Activity {

	private static final String Y_VALUE = "y_value";
	private static final String X_VALUE = "x_Value";
	private static final String ANIMATION_TIME = "AnimationTime";
	private float resizedXValue = .1f;
	private float resizedYValue = .1f;
	private int animationTime = 800;
	private GridView gridview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
		animationTime = sharedPref.getInt(ANIMATION_TIME, 800);
		resizedXValue = sharedPref.getFloat(X_VALUE, .1f);
		resizedYValue = sharedPref.getFloat(Y_VALUE, .1f);

		gridview = (GridView) findViewById(R.id.grid_awesome);
		gridview.setAdapter(new ImageAdapter(this, true, animationTime));

		RotateAnimation rotate = new RotateAnimation(0, 45,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rotate.setDuration(animationTime);
		rotate.setInterpolator(new LinearInterpolator());

		((ToggleButton) findViewById(R.id.toggler))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						gridview.setAdapter(new ImageAdapter(
								getApplicationContext(), isChecked,
								animationTime));

					}
				});

		gridview.animate().rotationBy(45).setDuration(animationTime)
				.setInterpolator(new AccelerateDecelerateInterpolator())
				.start();

		gridview.animate().scaleX(resizedXValue).scaleY(resizedYValue)
				.setDuration(animationTime)
				.setInterpolator(new AccelerateDecelerateInterpolator())
				.start();

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Toast.makeText(DiamondActivity.this, "" + position,
						Toast.LENGTH_SHORT).show();
			}
		});

		SeekBar resizeXBar = (SeekBar) findViewById(R.id.resize_X_seek);
		SeekBar resizeYBar = (SeekBar) findViewById(R.id.resize_Y_seek);
		SeekBar animTimeBar = (SeekBar) findViewById(R.id.anime_time_seek);

		final TextView scaleXValue = (TextView) findViewById(R.id.scale_X_value);
		final TextView scaleYValue = (TextView) findViewById(R.id.scale_Y_value);
		final TextView animeTimeValue = (TextView) findViewById(R.id.anime_time_value);

		resizeXBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

				gridview.animate()
						.scaleX(resizedXValue)
						.scaleY(resizedXValue)
						.setDuration(animationTime)
						.setInterpolator(new AccelerateDecelerateInterpolator())
						.start();

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				resizedXValue = (progress * 1f) / 100;
				scaleXValue.setText(String.valueOf(progress));

			}
		});

		resizeYBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

				gridview.animate()
						.scaleX(resizedXValue)
						.scaleY(resizedYValue)
						.setDuration(animationTime)
						.setInterpolator(new AccelerateDecelerateInterpolator())
						.start();

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				resizedYValue = (progress * 1f) / 100;
				scaleYValue.setText(String.valueOf(progress));

			}
		});

		animTimeBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

				gridview.animate()
						.scaleX(resizedXValue)
						.scaleY(resizedYValue)
						.setDuration(animationTime)
						.setInterpolator(new AccelerateDecelerateInterpolator())
						.start();

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				animationTime = progress;
				animeTimeValue.setText(String.valueOf(progress));

			}
		});

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putInt(ANIMATION_TIME, animationTime);
		editor.putFloat(X_VALUE, resizedXValue);
		editor.putFloat(Y_VALUE, resizedYValue);
		editor.commit();
	}
}
