package com.po.popularmovies;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.widget.ImageView;

import com.po.popularmovies.models.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		Movie movie = getIntent().getParcelableExtra(Intent.EXTRA_TEXT);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);

		CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
		collapsingToolbar.setTitleEnabled(true);
		collapsingToolbar.setTitle(movie.getOriginalTitle());

		//((ImageView) findViewById(R.id.imageview_poster)).setBackgroundResource(R.drawable.sample_poster_big);

		ImageView iv = (ImageView) findViewById(R.id.imageview_poster);



		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);

		iv.getLayoutParams().height = size.x;
		iv.getLayoutParams().width = size.x;

		final String BASE_URL = "http://image.tmdb.org/t/p/w342/";
		Picasso.with(this)
				.load(BASE_URL + movie.getMoviePath())
				//.resize(size.x, size.x)
				.fit()

				.centerCrop()
				.into(iv);



	}

}
