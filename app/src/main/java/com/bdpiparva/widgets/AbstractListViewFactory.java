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
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.List;

/**
 * Created by bhupendrakumar on 2/24/18.
 */

public abstract class AbstractListViewFactory<T> implements RemoteViewsService.RemoteViewsFactory {
	protected final Context context;
	private List<T> list;
	private final RemoteViews originalView;

	public AbstractListViewFactory(Context context, Intent intent, List<T> list, int layoutId) {
		this.context = context;
		this.list = list;
		originalView = new RemoteViews(context.getPackageName(), layoutId);
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
		return list.size();
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

	@Override
	public RemoteViews getViewAt(int position) {
		return getView(list.get(position), originalView, position);
	}

	protected abstract RemoteViews getView(T item, RemoteViews remoteViews, int position);
}
