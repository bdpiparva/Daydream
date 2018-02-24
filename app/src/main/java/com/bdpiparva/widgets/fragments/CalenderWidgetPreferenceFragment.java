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
