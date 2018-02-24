package com.bdpiparva.activities;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.widget.Toast;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class BaseActivity extends Activity {
	private static final int REQUEST_PERMISSION_CODE = 2;

	protected void requestPermissions(String... permissions) {
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

	protected boolean isGranted(String permission) {
		return checkSelfPermission(permission) != PERMISSION_GRANTED;
	}


	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		switch (requestCode) {
			case REQUEST_PERMISSION_CODE:
				if (grantResults.length > 0) {
					for (int i = 0; i < grantResults.length; i++) {
						String message = String.format("Permission %s granted: %s", permissions[i], grantResults[i] == PERMISSION_GRANTED);
						Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
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
