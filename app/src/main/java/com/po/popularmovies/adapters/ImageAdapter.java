package com.po.popularmovies.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.po.popularmovies.models.Movie;

import java.util.ArrayList;

/**
 * Created by mirka on 21.07.16.
 */

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList <Movie> movies;

	public ImageAdapter(Context c, ArrayList <Movie> movies) {
		this.mContext = c;
		this.movies = movies;
	}

	public void setMovies (ArrayList<Movie> movies) {
		this.movies = movies;
	}

	public int getCount() {
		return movies.size();
	}

	public Movie getItem(int position) {
		return movies.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) {
			// if it's not recycled, initialize some attributes
			imageView = new ImageView(mContext);
			//imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		} else {
			imageView = (ImageView) convertView;
		}

		String BASER_URL = "http://image.tmdb.org/t/p/w185/";

		//FIXME flickering
		Glide.with(mContext)
			.load(BASER_URL + movies.get(position).getMoviePath())
			.fitCenter()
			.into(imageView);

		return imageView;
	}
}
