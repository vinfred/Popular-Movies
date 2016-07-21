package com.po.popularmovies;

import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.po.popularmovies.models.Movie;

public class DetailActivityFragment extends Fragment {
	public final static String TAG = DetailActivityFragment.class.getSimpleName();

	public DetailActivityFragment () {
	}

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container,
	                          Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
		Movie movie = getActivity().getIntent().getParcelableExtra(Intent.EXTRA_TEXT);

		//((TextView) rootView.findViewById(R.id.textview_title)).setText(movie.getOriginalTitle());
		((TextView) rootView.findViewById(R.id.textView_overview)).setText(movie.getOverview());
		((TextView) rootView.findViewById(R.id.textView_releasedate)).setText(movie.getReleaseDate());
		((TextView) rootView.findViewById(R.id.textView_rating)).setText(movie.getRating()+"");



		return rootView;
	}
}
