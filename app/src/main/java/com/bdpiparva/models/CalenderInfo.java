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
