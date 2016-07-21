package com.po.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.po.popularmovies.adapters.ImageAdapter;
import com.po.popularmovies.models.Movie;

import java.util.ArrayList;

public class MainActivityFragment extends Fragment {
    public final static String TAG = MainActivityFragment.class.getSimpleName();

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

	    View rootView = inflater.inflate(R.layout.fragment_main, container, false);

	    final ArrayList <Movie> movies = new ArrayList<>();
	    movies.add(new Movie(1, "path", "movie1", "cool movie", 5/7, "30-02-19993"));
	    movies.add(new Movie(2, "path", "movie2", "cool movie", 5/7, "30-02-19993"));
	    movies.add(new Movie(3, "path", "movie3", "cool movie", 5/7, "30-02-19993"));
	    movies.add(new Movie(4, "path", "movie4", "cool movie", 5/7, "30-02-19993"));
	    movies.add(new Movie(5, "path", "movie5", "cool movie", 5/7, "30-02-19993"));
	    movies.add(new Movie(6, "path", "movie6", "cool movie", 5/7, "30-02-19993"));
	    movies.add(new Movie(7, "path", "movie7", "cool movie", 5/7, "30-02-19993"));
	    movies.add(new Movie(8, "path", "movie8", "cool movie", 5/7, "30-02-19993"));

	    GridView gridview = (GridView) rootView.findViewById(R.id.gridview_movies);
	    gridview.setAdapter(new ImageAdapter(getActivity()));

	    gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View v,
		                            int position, long id) {
			    Intent i = new Intent(getActivity(), DetailActivity.class);
			    i.putExtra(Intent.EXTRA_TEXT, movies.get(position));
			    startActivity(i);
		    }
	    });


	    return rootView;
    }
}
