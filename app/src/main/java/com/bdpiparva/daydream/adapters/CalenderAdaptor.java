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

package com.bdpiparva.daydream.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import com.bdpiparva.daydream.R;
import com.bdpiparva.models.CalenderInfo;
import com.bdpiparva.providers.CalenderProvider;

import java.util.HashSet;
import java.util.Set;

public class CalenderAdaptor extends ArrayAdapter<CalenderInfo> {
	private int resource;
	private SharedPreferences sharedPreferences;
	public static final String SELECTED_CALENDERS = "SELECTED_CALENDERS";

	public CalenderAdaptor(Context context, int resource, CalenderProvider calenderProvider) {
		super(context, resource, calenderProvider.toList());
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
		this.resource = resource;
		selectedCalenders();
	}

	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		final CalenderInfo calenderInfo = getItem(position);
		ViewHolder viewHolder;

		if (convertView == null) {
			viewHolder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(resource, parent, false);
			viewHolder.calender = convertView.findViewById(R.id.calender_id);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		Set<String> selectedCalenders = selectedCalenders();

		viewHolder.calender.setOnCheckedChangeListener(null);
		viewHolder.calender.setText(calenderInfo.getDisplayName());
		viewHolder.calender.setChecked(selectedCalenders.contains(calenderInfo.getStringId()));
		onItemClick(calenderInfo, viewHolder.calender);

		return convertView;
	}

	private void onItemClick(CalenderInfo calenderInfo, CheckBox checkBox) {
		checkBox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
			final Set<String> selectedCalenders = CalenderAdaptor.this.selectedCalenders();

			if (isChecked) {
				selectedCalenders.add(calenderInfo.getStringId());
			} else {
				selectedCalenders.remove(calenderInfo.getStringId());
			}

			CalenderAdaptor.this.updateSelectedCalender(selectedCalenders);

		});
	}

	private void updateSelectedCalender(Set<String> selectedCalenders) {
		sharedPreferences.edit().putStringSet(SELECTED_CALENDERS, selectedCalenders).apply();
	}

	private Set<String> selectedCalenders() {
		return sharedPreferences.getStringSet(SELECTED_CALENDERS, new HashSet<>());
	}

	private class ViewHolder {
		public CheckBox calender;
	}
}
