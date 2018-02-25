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
