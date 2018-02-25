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
