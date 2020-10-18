package com.anz.cam.model.modelutils;
/**
 * AccountType Enum.
 * @author Keerthi Gadde
 *
 */
public enum AccountType {
	CURRENT("Current Account"), SAVINGS("Savings Account");
	private String accType;

	private AccountType(String accType) {
		this.accType = accType;
	}

	public String getAccountType() {
		return accType;
	}
}
