package com.bdpiparva.widgets;

import android.content.Intent;
import android.widget.RemoteViewsService;

import com.bdpiparva.providers.CalenderProvider;
import com.bdpiparva.providers.EventProviders;

public class CalenderWidgetService extends RemoteViewsService {
	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		CalenderProvider calenderProvider = CalenderProvider.getInstance(getContentResolver());
		final EventProviders eventProviders = new EventProviders(getApplicationContext());
		return new ListViewsFactory(getApplicationContext(), intent, eventProviders.getEventsFromTodayAndNextDays(7, calenderProvider.allCalenderIdSet()).listAndSort());
	}
}
