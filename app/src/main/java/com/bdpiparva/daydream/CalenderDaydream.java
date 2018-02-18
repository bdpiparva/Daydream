package com.bdpiparva.daydream;

import android.service.dreams.DreamService;
import android.widget.ListView;
import android.widget.TextClock;

import com.bdpiparva.daydream.adapters.CalenderEventAdaptor;
import com.bdpiparva.daydream.models.CalenderEvent;
import com.bdpiparva.daydream.providers.EventProviders;

import java.util.List;

public class CalenderDaydream extends DreamService {
	private ListView appointmentsListView;
	private TextClock textClock;

	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();

		setInteractive(true);
		setFullscreen(true);
		setContentView(R.layout.calender_daydream);

		final EventProviders eventProviders = new EventProviders(this);

		appointmentsListView = findViewById(R.id.calender_events);
		textClock = findViewById(R.id.clock);

		List<CalenderEvent> calenderEvents = eventProviders.getEventsFromTodayAndNextDays(2).listAndSort();
		CalenderEventAdaptor calenderEventAdaptor = new CalenderEventAdaptor(this, R.layout.appointment_row, calenderEvents);
		this.appointmentsListView.setAdapter(calenderEventAdaptor);
	}
}

