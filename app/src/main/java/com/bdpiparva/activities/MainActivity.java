package com.bdpiparva.activities;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.bdpiparva.widgets.CalenderWidgetProvider;
import com.bdpiparva.widgets.fragments.CalenderWidgetPreferenceFragment;

import static android.Manifest.permission.READ_CALENDAR;

public class MainActivity extends BaseActivity {
	private final SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener = (sharedPreferences, key) -> reloadWidget();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestPermissions(READ_CALENDAR);

		getFragmentManager()
			.beginTransaction()
			.replace(android.R.id.content, new CalenderWidgetPreferenceFragment())
			.commit();

		PreferenceManager.getDefaultSharedPreferences(this)
			.registerOnSharedPreferenceChangeListener(preferenceChangeListener);
	}

	private void reloadWidget() {
		final Intent intent = new Intent(this, CalenderWidgetProvider.class);
		intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

		int[] ids = AppWidgetManager.getInstance(getApplication())
			.getAppWidgetIds(new ComponentName(getApplication(), CalenderWidgetProvider.class));
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
		sendBroadcast(intent);
	}

	@Override
	protected void onResume() {
		super.onResume();
		PreferenceManager.getDefaultSharedPreferences(this)
			.registerOnSharedPreferenceChangeListener(preferenceChangeListener);
	}

	@Override
	protected void onPause() {
		super.onPause();
		PreferenceManager.getDefaultSharedPreferences(this)
			.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
	}
}
