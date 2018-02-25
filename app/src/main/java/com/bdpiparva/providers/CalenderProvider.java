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

package com.bdpiparva.providers;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.CalendarContract.Calendars;

import com.bdpiparva.models.CalenderInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CalenderProvider extends HashSet<CalenderInfo> {
	private static CalenderProvider calenderProvider;
	private static final String[] PROJECTION = new String[]{
		Calendars._ID, Calendars.ACCOUNT_NAME, Calendars.CALENDAR_DISPLAY_NAME, Calendars.OWNER_ACCOUNT
	};

	private CalenderProvider() {
	}

	public Long[] allCalenderIds() {
		return stream().map(ci -> ci.getId()).collect(Collectors.toSet()).toArray(new Long[0]);
	}

	public Set<String> allCalenderIdSet() {
		return stream().map(ci -> String.valueOf(ci.getId())).collect(Collectors.toSet());
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
