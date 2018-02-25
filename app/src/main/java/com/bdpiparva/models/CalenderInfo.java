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

package com.bdpiparva.models;

/**
 * Created by bhupendrakumar on 2/18/18.
 */

public class CalenderInfo {
	private long id;
	private String accountName;
	private String displayName;
	private String ownerAccount;

	public CalenderInfo(long id, String accountName, String displayName, String ownerAccount) {
		this.id = id;
		this.accountName = accountName;
		this.displayName = displayName;
		this.ownerAccount = ownerAccount;
	}

	public long getId() {
		return id;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getOwnerAccount() {
		return ownerAccount;
	}

	public String getStringId() {
		return String.format("%d", id);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CalenderInfo that = (CalenderInfo) o;

		if (id != that.id) return false;
		if (accountName != null ? !accountName.equals(that.accountName) : that.accountName != null)
			return false;
		if (displayName != null ? !displayName.equals(that.displayName) : that.displayName != null)
			return false;
		return ownerAccount != null ? ownerAccount.equals(that.ownerAccount) : that.ownerAccount == null;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (accountName != null ? accountName.hashCode() : 0);
		result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
		result = 31 * result + (ownerAccount != null ? ownerAccount.hashCode() : 0);
		return result;
	}
}
