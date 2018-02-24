package com.bdpiparva.activities;

import android.os.Bundle;

import com.bdpiparva.daydream.R;

import static android.Manifest.permission.READ_CALENDAR;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		requestPermissions(READ_CALENDAR);
	}
}
