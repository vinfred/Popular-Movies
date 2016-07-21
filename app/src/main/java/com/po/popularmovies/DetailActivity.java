package com.po.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.po.popularmovies.models.Movie;

public class DetailActivity extends AppCompatActivity {

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);

		Movie movie = getIntent().getParcelableExtra(Intent.EXTRA_TEXT);
		collapsingToolbar.setTitle(movie.getOriginalTitle());
		((ImageView) findViewById(R.id.imageview_poster)).setBackgroundResource(R.drawable.sample_poster_big);



	}

}
