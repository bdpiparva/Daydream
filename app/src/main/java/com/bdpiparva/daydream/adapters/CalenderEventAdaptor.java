package com.bdpiparva.daydream.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bdpiparva.daydream.R;
import com.bdpiparva.models.CalenderEvent;

import java.text.SimpleDateFormat;
import java.util.List;

public class CalenderEventAdaptor extends ArrayAdapter<CalenderEvent> {
	private int resource;
	private final boolean allDay;
	private SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");

	public CalenderEventAdaptor(@NonNull Context context, int resource, @NonNull List<CalenderEvent> calenderEvents, boolean allDay) {
		super(context, resource, calenderEvents);
		this.resource = resource;
		this.allDay = allDay;
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		final CalenderEvent calenderEvent = getItem(position);
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(resource, parent, false);
			viewHolder.start = convertView.findViewById(R.id.start);
			viewHolder.title = convertView.findViewById(R.id.title);
			viewHolder.topTick = convertView.findViewById(R.id.top_tick);
			viewHolder.bottomTick = convertView.findViewById(R.id.bottom_tick);
			viewHolder.stateIndicator = convertView.findViewById(R.id.state_indicator);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		if (position == 0) {
			viewHolder.topTick.setVisibility(View.GONE);
		}

		if (position == (getCount() - 1)) {
			viewHolder.bottomTick.setVisibility(View.GONE);
		}

		viewHolder.start.setText(stringTime(calenderEvent.getStart()));
		viewHolder.title.setText(calenderEvent.getTitle());

		if (allDay) {
			return convertView;
		}

		if (calenderEvent.isCurrent()) {
			viewHolder.stateIndicator.setBackgroundResource(R.drawable.ic_current);
		}

		if (calenderEvent.isPassed()) {
			viewHolder.stateIndicator.setBackgroundResource(R.drawable.ic_finished);
		}

		if (calenderEvent.isUpcoming()) {
			viewHolder.stateIndicator.setBackgroundResource(R.drawable.ic_upcoming);
		}

		return convertView;
	}

	private class ViewHolder {
		private TextView start;
		private TextView title;
		private TextView stateIndicator;
		private View topTick;
		private View bottomTick;
	}

	private String stringTime(long time) {
		return sdf.format(time);
	}
}
