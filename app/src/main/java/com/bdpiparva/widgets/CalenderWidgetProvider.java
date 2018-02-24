package com.bdpiparva.widgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.bdpiparva.daydream.R;

public class CalenderWidgetProvider extends AppWidgetProvider {
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] allWidgetIds) {
		for (int widgetId : allWidgetIds) {
			Intent intent = new Intent(context, CalenderWidgetService.class);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
			intent.putExtra("date", System.currentTimeMillis());
			intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

			RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.calender_widget);
			remoteViews.setRemoteAdapter(R.id.calender_events, intent);

			appWidgetManager.updateAppWidget(widgetId, remoteViews);
		}

		super.onUpdate(context, appWidgetManager, allWidgetIds);
	}
}
