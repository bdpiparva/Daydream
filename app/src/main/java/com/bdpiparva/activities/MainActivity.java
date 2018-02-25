/*
 * Copyright 2018 Bhupendrakumar Piprava
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
