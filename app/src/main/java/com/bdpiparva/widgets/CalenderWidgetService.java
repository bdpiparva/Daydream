package com.bdpiparva.widgets;

import android.content.Intent;
import android.widget.RemoteViewsService;

import com.bdpiparva.models.CalenderEvent;
import com.bdpiparva.providers.CalenderProvider;
import com.bdpiparva.providers.EventProviders;

import java.util.List;
import java.util.Set;

public class CalenderWidgetService extends RemoteViewsService {
	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		CalenderProvider calenderProvider = CalenderProvider.getInstance(getContentResolver());
		final EventProviders eventProviders = new EventProviders(getApplicationContext());
		Set<String> allCalenderIds = calenderProvider.allCalenderIdSet();
		final List<CalenderEvent> calenderEvents = eventProviders.getEventsFromTodayAndNextDays(7, allCalenderIds)
			.listAndSort();

		return new CalenderListViewFactory(getApplicationContext(), intent, calenderEvents);
	}
}
