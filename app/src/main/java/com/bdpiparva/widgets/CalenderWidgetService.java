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
