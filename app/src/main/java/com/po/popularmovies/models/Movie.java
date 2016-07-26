package com.po.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

public class Movie implements Parcelable{
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

	public String getOriginalTitle () {
		return originalTitle;
	}

	public String getOverview () {
		return overview;
	}

	private double getRating () {
		return rating;
	}

	private String getReleaseDate () {
		return releaseDate;
	}

	public String getFormattedRating () {
		return String.format(Locale.ENGLISH, "%.1f", getRating()) + "/10";
	}

	public String getFormattedDate () {
		return getReleaseDate().split("-")[0];
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

	private Movie (Parcel in) {
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
