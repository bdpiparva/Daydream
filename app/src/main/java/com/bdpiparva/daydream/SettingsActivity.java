package com.bdpiparva.daydream;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.bdpiparva.daydream.adapters.CalenderAdaptor;
import com.bdpiparva.daydream.providers.CalenderProvider;

import static android.Manifest.permission.READ_CALENDAR;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class SettingsActivity extends AppCompatActivity {
	private static final int REQUEST_PERMISSION_CODE = 2;
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

	private void requestPermissions(String... permissions) {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
			return;
		}

		for (int i = 0; i < permissions.length; i++) {
			if (isGranted(permissions[i])) {
				if (shouldShowRequestPermissionRationale(permissions[i])) {
					requestPermissions(new String[]{permissions[i]}, REQUEST_PERMISSION_CODE);
				} else {
					requestPermissions(new String[]{permissions[i]}, REQUEST_PERMISSION_CODE);
				}
			}
		}
	}

	private boolean isGranted(String permission) {
		return checkSelfPermission(permission) != PERMISSION_GRANTED;
	}


	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		switch (requestCode) {
			case REQUEST_PERMISSION_CODE:
				if (grantResults.length > 0) {
					for (int i = 0; i < grantResults.length; i++) {
						String message = String.format("Permission %s granted: %s", permissions[i], grantResults[i] == PERMISSION_GRANTED);
						Toast.makeText(SettingsActivity.this, message, Toast.LENGTH_LONG).show();
					}
				}
				break;
		}
	}

	class AppPermission {
		private String displayName;
		private String permission;

		public AppPermission(String displayName, String permission) {
			this.displayName = displayName;
			this.permission = permission;
		}

		public String getDisplayName() {
			return displayName;
		}

		public String getPermission() {
			return permission;
		}
	}
}
