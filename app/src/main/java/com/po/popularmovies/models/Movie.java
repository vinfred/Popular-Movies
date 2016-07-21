package com.po.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mirka on 21.07.16.
 */

public class Movie implements Parcelable {
	private int id;
	private String moviePath;
	private String originalTitle;
	private String overview;
	private double rating;
	private String releaseDate;

	public Movie (int id, String moviePath, String originalTitle, String overview, double rating, String releaseDate) {
		this.id = id;
		this.moviePath = moviePath;
		this.originalTitle = originalTitle;
		this.overview = overview;
		this.rating = rating;
		this.releaseDate = releaseDate;
	}

	public int getId () {
		return id;
	}

	public void setId (int id) {
		this.id = id;
	}

	public String getMoviePath () {
		return moviePath;
	}

	public void setMoviePath (String moviePath) {
		this.moviePath = moviePath;
	}

	public String getOriginalTitle () {
		return originalTitle;
	}

	public void setOriginalTitle (String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getOverview () {
		return overview;
	}

	public void setOverview (String overview) {
		this.overview = overview;
	}

	public double getRating () {
		return rating;
	}

	public void setRating (double rating) {
		this.rating = rating;
	}

	public String getReleaseDate () {
		return releaseDate;
	}

	public void setReleaseDate (String releaseDate) {
		this.releaseDate = releaseDate;
	}


	@Override
	public int describeContents () {
		return 0;
	}

	@Override
	public void writeToParcel (Parcel dest, int flags) {
		dest.writeInt(this.id);
		dest.writeString(this.moviePath);
		dest.writeString(this.originalTitle);
		dest.writeString(this.overview);
		dest.writeDouble(this.rating);
		dest.writeString(this.releaseDate);
	}

	protected Movie (Parcel in) {
		this.id = in.readInt();
		this.moviePath = in.readString();
		this.originalTitle = in.readString();
		this.overview = in.readString();
		this.rating = in.readDouble();
		this.releaseDate = in.readString();
	}

	public static final Creator<Movie> CREATOR = new Creator<Movie>() {
		@Override
		public Movie createFromParcel (Parcel source) {
			return new Movie(source);
		}

		@Override
		public Movie[] newArray (int size) {
			return new Movie[size];
		}
	};
}
