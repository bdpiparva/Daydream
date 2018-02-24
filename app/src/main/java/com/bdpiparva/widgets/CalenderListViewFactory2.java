package com.bdpiparva.widgets;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.RemoteViews;

import com.bdpiparva.daydream.R;
import com.bdpiparva.models.CalenderEvent;

import java.util.List;

import static java.text.MessageFormat.format;

public class CalenderListViewFactory2 extends AbstractListViewFactory<CalenderEvent> {
	public CalenderListViewFactory2(Context context, Intent intent, List<CalenderEvent> calenderEvents) {
		super(context, intent, calenderEvents, R.layout.calender_widget_row_2);
	}

	@Override
	public RemoteViews getView(CalenderEvent calenderEvent, RemoteViews remoteViews, int position) {
		remoteViews.setTextViewText(R.id.event_title, calenderEvent.getTitle());

		remoteViews.setInt(R.id.calender_color, "setBackgroundColor", getColorWithAlpha(calenderEvent.getColor()));

		if (calenderEvent.isAllDay()) {
			remoteViews.setViewVisibility(R.id.event_time, View.GONE);
		} else {
			remoteViews.setViewVisibility(R.id.event_time, View.VISIBLE);
			remoteViews.setTextViewText(R.id.event_time, format("{0} - {1}", calenderEvent.getStartTime(), calenderEvent.getEndTime()));
		}
		return remoteViews;
	}

	private int getColorWithAlpha(int colorInInteger) {
		return Color.argb(80, Color.red(colorInInteger), Color.green(colorInInteger), Color.blue(colorInInteger));
	}
}
