package com.bdpiparva.widgets;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViewsService;

import com.bdpiparva.models.CalenderEvent;
import com.bdpiparva.providers.EventProvider;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CalenderWidgetService extends RemoteViewsService {
	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		Set<String> selectedCalenders = preferences.getStringSet("calenders", new HashSet<>());
		final EventProvider eventProvider = new EventProvider(getApplicationContext());
		int eventForDays = Integer.valueOf(preferences.getString("event_for_days", "7"));

		final List<CalenderEvent> calenderEvents = eventProvider.getEventsFromTodayAndNextDays(eventForDays, selectedCalenders)
			.listAndSort();

		return new CalenderListViewFactory2(getApplicationContext(), intent, calenderEvents);
	}
}
