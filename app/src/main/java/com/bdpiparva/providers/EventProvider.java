package com.bdpiparva.providers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.provider.CalendarContract.Instances;
import android.support.annotation.NonNull;

import com.bdpiparva.models.CalenderEvent;
import com.bdpiparva.models.CalenderEvents;

import org.joda.time.DateTime;

import java.util.HashSet;
import java.util.Set;

import static android.provider.CalendarContract.Events;

/**
 * Created by bhupendrakumar on 2/18/18.
 */

public class EventProvider extends Provider {
	public static final String[] DEFAULT_PROJECTION = {
		Instances.CALENDAR_ID, Instances.TITLE, Instances.DESCRIPTION,
		Instances.DTSTART, Instances.DTEND, Instances.EVENT_LOCATION,
		Instances.ALL_DAY, Instances.DISPLAY_COLOR
	};

	public EventProvider(Context context) {
		super(context);
	}

	@SuppressLint("MissingPermission")
	public CalenderEvents getAllEvents() {
		Cursor cursor = context.getContentResolver().query(Events.CONTENT_URI, DEFAULT_PROJECTION, null, null, null);

		return toCalenderEvent(cursor);
	}

	@SuppressLint("MissingPermission")
	public CalenderEvents getEventsFromTodayAndNextDays(int days, Set<String> calenderIds) {
		Cursor cursor = context.getContentResolver()
			.query(Events.CONTENT_URI, DEFAULT_PROJECTION, selectionWith(days, calenderIds), null, null);
		return toCalenderEvent(cursor);
	}

	@NonNull
	private CalenderEvents toCalenderEvent(Cursor cursor) {
		CalenderEvents calenderEvents = new CalenderEvents();
		while (cursor.moveToNext()) {
			calenderEvents.addEvent(new CalenderEvent(cursor.getLong(0),
				cursor.getString(1),
				cursor.getString(2),
				cursor.getLong(3),
				cursor.getLong(4),
				cursor.getString(5),
				isAllDay(cursor.getString(6)),
				cursor.getInt(7)
			));
		}
		return calenderEvents;
	}

	private boolean isAllDay(String allDay) {
		return allDay.equals("1");
	}

	private String selectionWith(int days, Set<String> calenderIds) {
		return String.format("%s AND (%s)", selectionWithNextDays(days), selectionWithCalenderIds(calenderIds));
	}

	private String selectionWithCalenderIds(Set<String> calenderIds) {
		if (calenderIds == null) {
			calenderIds = new HashSet<>();
		}

		String idStr = calenderIds.toString();
		return String.format("%s IN (%s)", Events.CALENDAR_ID, idStr.substring(1, idStr.length() - 1));
	}

	private String selectionWithNextDays(int days) {
		DateTime now = DateTime.now()
			.withHourOfDay(0)
			.withMinuteOfHour(0)
			.withSecondOfMinute(0)
			.withMillisOfSecond(0);

		DateTime end = now.plusDays(days + 1)
			.withHourOfDay(0)
			.withMinuteOfHour(0)
			.withSecondOfMinute(0)
			.withMillisOfSecond(0);

		return String.format("( %s >= %d ) AND ( %s < %d ) ",
			Events.DTSTART, now.getMillis(),
			Events.DTSTART, end.getMillis());
	}
}
