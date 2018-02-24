package com.bdpiparva.widgets;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bdpiparva.daydream.R;
import com.bdpiparva.models.CalenderEvent;

import java.util.List;

import static android.appwidget.AppWidgetManager.EXTRA_APPWIDGET_ID;
import static android.appwidget.AppWidgetManager.INVALID_APPWIDGET_ID;
import static android.view.View.GONE;

public class ListViewsFactory implements RemoteViewsService.RemoteViewsFactory {
	private final Context context;
	private final Intent intent;
	private final int widgetId;
	private List<CalenderEvent> calenderEvents;

	public ListViewsFactory(Context context, Intent intent, List<CalenderEvent> calenderEvents) {
		this.context = context;
		this.intent = intent;
		widgetId = intent.getIntExtra(EXTRA_APPWIDGET_ID, INVALID_APPWIDGET_ID);
		this.calenderEvents = calenderEvents;
	}

	@Override
	public void onCreate() {

	}

	@Override
	public void onDataSetChanged() {

	}

	@Override
	public void onDestroy() {

	}

	@Override
	public int getCount() {
		return calenderEvents.size();
	}

	@Override
	public RemoteViews getViewAt(int position) {
		final CalenderEvent calenderEvent = calenderEvents.get(position);
		final RemoteViews eventRow = new RemoteViews(context.getPackageName(), R.layout.calender_widget_row);

		eventRow.setTextViewText(R.id.start, calenderEvent.getStartTime());
		eventRow.setTextViewText(R.id.title, calenderEvent.getTitle());

		setTickVisibility(position, eventRow);
		setStateIndicatorState(calenderEvent, eventRow);

		return eventRow;
	}

	private void setStateIndicatorState(CalenderEvent calenderEvent, RemoteViews eventRow) {
		if (calenderEvent.isAllDay()) {
			eventRow.setTextViewText(R.id.state_indicator, "A");
		}

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
		if (position == 0) {
			eventRow.setViewVisibility(R.id.top_tick, GONE);
		}

		if (position == getCount() - 1) {
			eventRow.setViewVisibility(R.id.bottom_tick, GONE);
		}
	}

	@Override
	public RemoteViews getLoadingView() {
		return null;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}
}
