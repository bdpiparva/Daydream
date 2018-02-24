package com.bdpiparva.providers;

import android.content.Context;
import android.content.pm.PackageManager;

import static android.support.v4.content.ContextCompat.checkSelfPermission;
import static java.text.MessageFormat.format;

public abstract class Provider {
	protected Context context;

	public Provider(Context context, String... requiredPermissions) {
		this.context = context;
		checkRequiredPermissions(requiredPermissions);
	}

	private void checkRequiredPermissions(String[] requiredPermissions) {
		if (requiredPermissions == null || requiredPermissions.length == 0) {
			return;
		}

		for (String requiredPermission : requiredPermissions) {
			checkPermission(requiredPermission);
		}
	}

	private void checkPermission(String permission) {
		if (isGranted(permission)) {
			throw new RuntimeException(format("You don't have {0} permission.", permission));
		}
	}

	private boolean isGranted(String permission) {
		return checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED;
	}
}
