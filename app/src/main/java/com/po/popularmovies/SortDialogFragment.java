package com.po.popularmovies;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import java.util.Arrays;

public class SortDialogFragment extends DialogFragment {
	interface OnDialogSelectorListener {
		void onSelectedOption();
	}

	private OnDialogSelectorListener listener;


	public SortDialogFragment () {
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);

		Activity activity = null;

		if (context instanceof Activity){
			activity = (Activity) context;

			try {
				listener = (OnDialogSelectorListener) activity;
			}
			catch (ClassCastException e) {
				throw new ClassCastException(context.toString() + " must implement OnDialogSelectorListener");
			}
		}
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final String [] opts = getResources().getStringArray(R.array.order_labels_array);

		String order  = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(
				getString(R.string.pref_order_key),
				getString(R.string.pref_order_default));

		final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.dialog_sort_title)
				.setSingleChoiceItems(opts, Arrays.asList(opts).indexOf(order), new DialogInterface.OnClickListener() {
					@Override
					public void onClick (DialogInterface dialogInterface, int i) {
						SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
						editor.putString(getString(R.string.pref_order_key), opts[i]);
						editor.commit();

						listener.onSelectedOption();

						dialogInterface.dismiss();
					}
				});

		return builder.create();
	}
}
