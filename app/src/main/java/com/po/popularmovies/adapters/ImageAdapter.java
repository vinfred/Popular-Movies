package com.po.popularmovies.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.po.popularmovies.R;
import com.po.popularmovies.models.Movie;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
	private static final String BASE_URL = "http://image.tmdb.org/t/p/w185/";

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
		return movies.get(position).getId();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) {
			imageView = new ImageView(mContext);
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		} else {
			imageView = (ImageView) convertView;
		}

		//FIXME change placeholder
		Glide.with(mContext)
			.load(BASE_URL + movies.get(position).getMoviePath())
			.fitCenter()
			.dontAnimate()
			.placeholder(R.drawable.sample_poster)
			.into(imageView);


		return imageView;
	}
}
