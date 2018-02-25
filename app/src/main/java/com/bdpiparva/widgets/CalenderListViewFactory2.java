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

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

import com.bdpiparva.daydream.R;
import com.bdpiparva.models.CalenderEvent;

import java.util.List;

import static com.bdpiparva.widgets.fragments.CalenderWidgetPreferenceFragment.CALENDER_COLOR_BAR_TRANSPARENCY;
import static com.bdpiparva.widgets.fragments.CalenderWidgetPreferenceFragment.CALENDER_COLOR_BAR_WIDTH;
import static com.bdpiparva.widgets.fragments.CalenderWidgetPreferenceFragment.CALENDER_WIDGET_BACKGROUND_COLOR;
import static com.bdpiparva.widgets.fragments.CalenderWidgetPreferenceFragment.CALENDER_WIDGET_TEXT_COLOR;
import static java.text.MessageFormat.format;

public class CalenderListViewFactory2 extends AbstractListViewFactory<CalenderEvent> {
	private final SharedPreferences preferences;
	private final int textColor;
	private final int backgroundColor;
	private final int calenderColorBarTransparency;
	private final int calenderColorBarWidth;

	public CalenderListViewFactory2(Context context, Intent intent, List<CalenderEvent> calenderEvents) {
		super(context, intent, calenderEvents, R.layout.calender_widget_row_2);

		preferences = PreferenceManager.getDefaultSharedPreferences(context);
		textColor = preferences.getInt(CALENDER_WIDGET_TEXT_COLOR, Color.parseColor("#7E57C2"));
		backgroundColor = preferences.getInt(CALENDER_WIDGET_BACKGROUND_COLOR, Color.parseColor("#80000000"));
		calenderColorBarTransparency = preferences.getInt(CALENDER_COLOR_BAR_TRANSPARENCY, 80);
		calenderColorBarWidth = convertPixels(preferences.getInt(CALENDER_COLOR_BAR_WIDTH, 3));
	}

	@Override
	public RemoteViews getView(CalenderEvent calenderEvent, RemoteViews remoteViews, int position) {

		remoteViews.setTextViewText(R.id.event_title, calenderEvent.getTitle());
		remoteViews.setTextColor(R.id.event_title, textColor);
		remoteViews.setInt(R.id.event_row, "setBackgroundColor", backgroundColor);

		setCalenderBarColor(calenderEvent, remoteViews, calenderColorBarWidth, calenderColorBarTransparency);
		setEventTime(calenderEvent, remoteViews, textColor);

		return remoteViews;
	}

	private void setEventTime(CalenderEvent calenderEvent, RemoteViews remoteViews, int textColor) {
		remoteViews.setTextColor(R.id.event_time, textColor);

		if (calenderEvent.isAllDay()) {
			remoteViews.setTextViewText(R.id.event_time, calenderEvent.getDescription());
		} else {
			remoteViews.setTextViewText(R.id.event_time, format("{0} - {1}", calenderEvent.getStartTime(), calenderEvent.getEndTime()));
		}
	}

	private void setCalenderBarColor(CalenderEvent calenderEvent, RemoteViews remoteViews, int calenderColorBarWidth, int calenderColorBarTransparency) {
		remoteViews.setInt(R.id.calender_color, "setWidth", calenderColorBarWidth);
		remoteViews.setInt(R.id.calender_color, "setMinWidth", calenderColorBarWidth);
		remoteViews.setInt(R.id.calender_color, "setBackgroundColor", getColorWithAlpha(calenderColorBarTransparency, calenderEvent.getColor()));
	}

	private int getColorWithAlpha(int alpha, int colorInInteger) {
		return Color.argb(alpha, Color.red(colorInInteger), Color.green(colorInInteger), Color.blue(colorInInteger));
	}

	private int convertPixels(int widthInDp) {
		return (int) (widthInDp * context.getResources().getDisplayMetrics().density);
	}
}
