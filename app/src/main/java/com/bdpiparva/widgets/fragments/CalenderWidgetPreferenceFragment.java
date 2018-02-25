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
