package com.po.popularmovies;

import com.po.popularmovies.models.Movie;

import java.util.Comparator;

public class RatingComparator implements Comparator <Movie> {
	@Override
	public int compare (Movie movie1, Movie movie2) {
		return ((Double) movie1.getRating()).compareTo((Double)movie2.getRating());
	}
}
