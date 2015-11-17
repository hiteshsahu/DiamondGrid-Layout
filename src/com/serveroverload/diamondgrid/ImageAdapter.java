package com.serveroverload.diamondgrid;

import com.example.gridawesome.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;

@SuppressLint("NewApi")
public class ImageAdapter extends BaseAdapter {
	private Context mContext;

	private boolean showMinMode;

	public ImageAdapter(Context c, boolean showMinMode) {
		mContext = c;
		this.showMinMode = showMinMode;
	}

	public int getCount() {
		if (showMinMode) {
			return mThumbId2.length;
		} else {
			return mThumbIds.length;
		}

	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {

		View myView = convertView;
		if (convertView == null) {

			myView = LayoutInflater.from(mContext).inflate(
					R.layout.grid_row_layout, parent, false);

			if (showMinMode) {

				((ImageView) myView.findViewById(R.id.grid_image))
						.setImageResource(mThumbId2[position]);
			} else {
				((ImageView) myView.findViewById(R.id.grid_image))
						.setImageResource(mThumbIds[position]);
			}

			myView.findViewById(R.id.grid_image).animate().rotationBy(-45)
					.setDuration(800)
					.setInterpolator(new AccelerateDecelerateInterpolator())
					.start();
		} else {
			// imageView = (ImageView) convertView;

		}

		return myView;
	}

	// references to our images
	private Integer[] mThumbId2 = { R.drawable.vdo, R.drawable.cam,
			R.drawable.gallery, R.drawable.web, R.drawable.cam,
			R.drawable.gallery, R.drawable.web, R.drawable.cam,
			R.drawable.gallery, R.drawable.web, R.drawable.cam,
			R.drawable.gallery, R.drawable.web, R.drawable.cam,
			R.drawable.gallery, R.drawable.web };

	// references to our images
	private Integer[] mThumbIds = { R.drawable.sample_2, R.drawable.sample_3,
			R.drawable.sample_4, R.drawable.sample_5, R.drawable.sample_6,
			R.drawable.sample_7, R.drawable.sample_0, R.drawable.sample_1,
			R.drawable.sample_2, R.drawable.sample_3, R.drawable.sample_4,
			R.drawable.sample_5, R.drawable.sample_6, R.drawable.sample_7,
			R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2,
			R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5,
			R.drawable.sample_6, R.drawable.sample_7 };
}