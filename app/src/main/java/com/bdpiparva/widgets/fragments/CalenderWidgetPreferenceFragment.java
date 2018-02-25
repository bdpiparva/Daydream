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

package com.bdpiparva.widgets.fragments;

import android.os.Bundle;
import android.preference.MultiSelectListPreference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.bdpiparva.daydream.R;
import com.bdpiparva.providers.CalenderProvider;

import java.util.List;
import java.util.stream.Collectors;

public class CalenderWidgetPreferenceFragment extends PreferenceFragment {
	public static final String CALENDER_WIDGET_TEXT_COLOR = "calender_widget_text_color";
	public static final String CALENDER_COLOR_BAR_WIDTH = "calender_color_bar_width";
	public static final String CALENDER_COLOR_BAR_TRANSPARENCY = "calender_color_bar_transparency";
	public static final String CALENDER_WIDGET_BACKGROUND_COLOR = "calender_widget_background_color";

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.calender_prefs);
		populateCalenderPreference();
	}

	private void populateCalenderPreference() {
		CalenderProvider calenderProvider = CalenderProvider.getInstance(getContext().getContentResolver());
		List<CharSequence> calenderNames = calenderProvider.toList().stream().map(c -> c.getDisplayName()).collect(Collectors.toList());
		List<CharSequence> calenderIds = calenderProvider.toList().stream().map(c -> c.getStringId()).collect(Collectors.toList());

		MultiSelectListPreference calenders = (MultiSelectListPreference) findPreference("calenders");
		calenders.setEntries(calenderNames.toArray(new CharSequence[0]));
		calenders.setEntryValues(calenderIds.toArray(new CharSequence[0]));
	}
}
