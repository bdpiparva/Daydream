package com.bdpiparva.widgets;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.bdpiparva.daydream.R;
import com.bdpiparva.models.CalenderEvent;

import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class CalenderListViewFactory extends AbstractListViewFactory<CalenderEvent> {
	public CalenderListViewFactory(Context context, Intent intent, List<CalenderEvent> calenderEvents) {
		super(context, intent, calenderEvents, R.layout.calender_widget_row);
	}

	@Override
	public RemoteViews getView(CalenderEvent calenderEvent, RemoteViews remoteViews, int position) {
		remoteViews.setTextViewText(R.id.start, calenderEvent.getStartTime());
		remoteViews.setTextViewText(R.id.title, calenderEvent.getTitle());

		setTickVisibility(position, remoteViews);
		setStateIndicatorState(calenderEvent, remoteViews);

		return remoteViews;
	}

	private void setStateIndicatorState(CalenderEvent calenderEvent, RemoteViews eventRow) {
		if (calenderEvent.isAllDay()) {
			eventRow.setTextViewText(R.id.state_indicator, "A");
			eventRow.setInt(R.id.state_indicator, "setBackgroundResource", R.drawable.ic_upcoming);
			return;
		}

		eventRow.setTextViewText(R.id.state_indicator, "");

		if (calenderEvent.isCurrent()) {
			eventRow.setInt(R.id.state_indicator, "setBackgroundResource", R.drawable.ic_current);
		}

		if (calenderEvent.isPassed()) {
			eventRow.setInt(R.id.state_indicator, "setBackgroundResource", R.drawable.ic_finished);
		}

		if (calenderEvent.isUpcoming()) {
			eventRow.setInt(R.id.state_indicator, "setBackgroundResource", R.drawable.ic_upcoming);
		}
	}

	private void setTickVisibility(int position, RemoteViews eventRow) {
		eventRow.setViewVisibility(R.id.top_tick, VISIBLE);
		eventRow.setViewVisibility(R.id.bottom_tick, VISIBLE);

		if (position == 0) {
			eventRow.setViewVisibility(R.id.top_tick, INVISIBLE);
		}

		if (position == getCount() - 1) {
			eventRow.setViewVisibility(R.id.bottom_tick, INVISIBLE);
		}
	}
}
