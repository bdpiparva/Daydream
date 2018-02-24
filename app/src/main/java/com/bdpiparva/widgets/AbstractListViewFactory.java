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
