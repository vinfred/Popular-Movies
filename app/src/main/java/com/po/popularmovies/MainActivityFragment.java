package com.po.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.po.popularmovies.adapters.ImageAdapter;
import com.po.popularmovies.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivityFragment extends Fragment {
	public final static String TAG = MainActivityFragment.class.getSimpleName();
	private ImageAdapter adapter;

	public MainActivityFragment () {
	}

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container,
	                          Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_main, container, false);

		adapter = new ImageAdapter(getActivity(), new ArrayList<Movie>());

		GridView gridview = (GridView) rootView.findViewById(R.id.gridview_movies);
		gridview.setAdapter(adapter);

		gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick (AdapterView<?> parent, View v,
			                         int position, long id) {
				Intent i = new Intent(getActivity(), DetailActivity.class);
				i.putExtra(Intent.EXTRA_TEXT, adapter.getItem(position));
				startActivity(i);
			}
		});


		return rootView;
	}

	@Override
	public void onStart () {
		super.onStart();
		new GetDataTask().execute();
	}

	public class GetDataTask extends AsyncTask<Void, Void, ArrayList<Movie>> {
		public final String TAG = GetDataTask.class.getSimpleName();


		private ArrayList<Movie> parseJson (String movieJsonStr) throws JSONException {
			final String POSTER = "poster_path";
			final String OVERVIEW = "overview";
			final String DATE = "release_date";
			final String RATING = "vote_average";
			final String TITLE = "original_title";
			final String ID = "id";

			JSONObject movieJson = new JSONObject(movieJsonStr);
			JSONArray movieArray = movieJson.getJSONArray("results");

			ArrayList<Movie> resMovies = new ArrayList<>();

			for (int i = 0; i < movieArray.length(); i++) {
				JSONObject movieObject = movieArray.getJSONObject(i);

				resMovies.add(new Movie(Integer.parseInt(movieObject.getString(ID)), movieObject.getString(POSTER),
						movieObject.getString(TITLE), movieObject.getString(OVERVIEW),
						Double.parseDouble(movieObject.getString(RATING)), movieObject.getString(DATE)));
			}

			return resMovies;

		}


		@Override
		protected ArrayList<Movie> doInBackground (Void... params) {
			HttpURLConnection urlConnection = null;
			BufferedReader reader = null;

			String movieJsonStr = null;

			try {

				final String BASE_URL =
						"http://api.themoviedb.org/3/movie/popular?";
				final String APPID_PARAM = "api_key";

				Uri builtUri = Uri.parse(BASE_URL).buildUpon()
						.appendQueryParameter(APPID_PARAM, BuildConfig.API_KEY)
						.build();

				URL url = new URL(builtUri.toString());

				urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setRequestMethod("GET");
				urlConnection.connect();

				// Read the input stream into a String
				InputStream inputStream = urlConnection.getInputStream();
				StringBuffer buffer = new StringBuffer();
				if (inputStream == null) {
					return null;
				}
				reader = new BufferedReader(new InputStreamReader(inputStream));

				String line;
				while ((line = reader.readLine()) != null) {
					buffer.append(line + "\n");
				}

				if (buffer.length() == 0) {
					return null;
				}
				movieJsonStr = buffer.toString();
			}
			catch (IOException e) {
				Log.e(TAG, "Error ", e);
				return null;
			}
			finally {
				if (urlConnection != null) {
					urlConnection.disconnect();
				}
				if (reader != null) {
					try {
						reader.close();
					}
					catch (final IOException e) {
						Log.e(TAG, "Error closing stream", e);
					}
				}
			}

			try {
				return parseJson(movieJsonStr);
			}
			catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute (ArrayList<Movie> result) {
			if (result != null) {
				adapter.setMovies(result);
				adapter.notifyDataSetChanged();
			}
		}
	}
}
