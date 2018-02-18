package com.bdpiparva.daydream.providers;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.CalendarContract.Calendars;

import com.bdpiparva.daydream.models.CalenderInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class CalenderProvider extends HashSet<CalenderInfo> {
	private static CalenderProvider calenderProvider;
	private static final String[] PROJECTION = new String[]{
		Calendars._ID, Calendars.ACCOUNT_NAME, Calendars.CALENDAR_DISPLAY_NAME, Calendars.OWNER_ACCOUNT
	};

	private CalenderProvider() {
	}

	public Long[] allCalenderIds() {
		return stream().map(ci -> ci.getId()).collect(Collectors.toList()).toArray(new Long[0]);
	}

	public List<CalenderInfo> toList() {
		return new ArrayList<>(this);
	}

	@SuppressLint("MissingPermission")
	public synchronized static CalenderProvider getInstance(ContentResolver contentResolver) {
		if (calenderProvider != null) {
			return calenderProvider;
		}

		calenderProvider = new CalenderProvider();
		Cursor cursor = contentResolver.query(Calendars.CONTENT_URI, PROJECTION, null, null, null);
		while (cursor.moveToNext()) {
			calenderProvider.add(new CalenderInfo(
				cursor.getLong(0),
				cursor.getString(1),
				cursor.getString(2),
				cursor.getString(3)
			));
		}

		return calenderProvider;
	}
}
