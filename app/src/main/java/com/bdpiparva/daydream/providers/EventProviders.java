package com.bdpiparva.daydream.providers;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Instances;
import android.support.annotation.NonNull;

import com.bdpiparva.daydream.models.CalenderEvent;
import com.bdpiparva.daydream.models.CalenderEvents;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bhupendrakumar on 2/18/18.
 */

public class EventProviders extends Provider {
	public static final String[] DEFAULT_PROJECTION = {Instances.CALENDAR_ID, Instances.TITLE,
		Instances.DESCRIPTION, Instances.DTSTART, Instances.DTEND, Instances.EVENT_LOCATION
	};

	public EventProviders(Context context) {
		super(context);
	}

	@SuppressLint("MissingPermission")
	public CalenderEvents getAllEvents() {
		Cursor cursor = context.getContentResolver().query(CalendarContract.Events.CONTENT_URI, DEFAULT_PROJECTION, null, null, null);

		return toCalenderEvent(cursor);
	}

	@SuppressLint("MissingPermission")
	public CalenderEvents getEventsFromTodayAndNextDays(int days) {
		Cursor cursor = context.getContentResolver().query(CalendarContract.Events.CONTENT_URI, DEFAULT_PROJECTION, selectionForNextDays(days), null, null);
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
				cursor.getString(5)
			));
		}
		return calenderEvents;
	}

	private String selectionForNextDays(int days) {
		Calendar beginTime = Calendar.getInstance();
		beginTime.set(Calendar.HOUR_OF_DAY, 00);
		beginTime.set(Calendar.MINUTE, 00);
		beginTime.set(Calendar.SECOND, 00);

		Calendar endTime = Calendar.getInstance();
		endTime.set(Calendar.DAY_OF_MONTH, beginTime.get(Calendar.DAY_OF_MONTH) + days);
		endTime.set(Calendar.HOUR_OF_DAY, 24);
		endTime.set(Calendar.MINUTE, 00);
		endTime.set(Calendar.SECOND, 00);
		endTime.set(Calendar.MILLISECOND, 0);
		long end = endTime.getTimeInMillis();

		return String.format("( %s >= %d ) AND ( %s < %d )", CalendarContract.Events.DTSTART, beginTime.getTimeInMillis(), CalendarContract.Events.DTSTART, end);
	}
}
