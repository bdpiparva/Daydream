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

package com.bdpiparva.daydream;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.service.dreams.DreamService;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;

import com.bdpiparva.daydream.adapters.CalenderEventAdaptor;
import com.bdpiparva.models.CalenderEvent;
import com.bdpiparva.providers.EventProvider;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.bdpiparva.daydream.adapters.CalenderAdaptor.SELECTED_CALENDERS;

public class CalenderDaydream extends DreamService {
	private ListView appointmentsListView;
	private TextClock textClock;
	private TextView allDayEvents;
	private TextView events;
	private SharedPreferences sharedPreferences;

	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();

		setInteractive(true);
		setFullscreen(true);
		setContentView(R.layout.calender_daydream);

		final EventProvider eventProvider = new EventProvider(this);

		appointmentsListView = findViewById(R.id.calender_events);
		textClock = findViewById(R.id.clock);
		allDayEvents = findViewById(R.id.all_day_events);
		events = findViewById(R.id.events);

		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

		List<CalenderEvent> allEvents = eventProvider.getEventsFromTodayAndNextDays(7, selectedCalenders()).listAndSort();

		allDayEvents.setOnClickListener(view -> renderDayLongEvents(allEvents));
		events.setOnClickListener(view -> renderSmallEvents(allEvents));

		renderSmallEvents(allEvents);
	}

	private Set<String> selectedCalenders() {
		return sharedPreferences.getStringSet(SELECTED_CALENDERS, new HashSet<>());
	}

	private void renderDayLongEvents(List<CalenderEvent> allEvents) {
		List<CalenderEvent> allDayEvents = allEvents.stream().filter(c -> c.isAllDay()).collect(Collectors.toList());
		final CalenderEventAdaptor calenderEventAdaptor = new CalenderEventAdaptor(this, R.layout.appointment_row, allDayEvents, true);
		this.appointmentsListView.setAdapter(calenderEventAdaptor);

		events.setBackgroundColor(Color.parseColor("#000000"));
		this.allDayEvents.setBackgroundResource(R.drawable.right_rounded_corner);

		events.setTextColor(Color.parseColor("#ffffff"));
		this.allDayEvents.setTextColor(Color.parseColor("#000000"));
	}

	private void renderSmallEvents(List<CalenderEvent> allEvents) {
		List<CalenderEvent> smallEvents = allEvents.stream().filter(c -> !c.isAllDay()).collect(Collectors.toList());
		final CalenderEventAdaptor calenderEventAdaptor = new CalenderEventAdaptor(this, R.layout.appointment_row, smallEvents, false);
		this.appointmentsListView.setAdapter(calenderEventAdaptor);

		allDayEvents.setBackgroundColor(Color.parseColor("#000000"));
		events.setBackgroundResource(R.drawable.left_rounded_corner);

		allDayEvents.setTextColor(Color.parseColor("#ffffff"));
		events.setTextColor(Color.parseColor("#000000"));
	}
}

