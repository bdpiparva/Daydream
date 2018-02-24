package com.bdpiparva.daydream;

import android.os.Bundle;
import android.widget.ListView;

import com.bdpiparva.activities.BaseActivity;
import com.bdpiparva.daydream.adapters.CalenderAdaptor;
import com.bdpiparva.providers.CalenderProvider;

import static android.Manifest.permission.READ_CALENDAR;

public class SettingsActivity extends BaseActivity {
	private ListView calenderListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		requestPermissions(READ_CALENDAR);

		calenderListView = findViewById(R.id.list);
		CalenderAdaptor calenderAdaptor = new CalenderAdaptor(this, R.layout.calender_row, CalenderProvider.getInstance(getContentResolver()));
		calenderListView.setAdapter(calenderAdaptor);
	}
}
