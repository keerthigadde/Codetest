package com.anz.cam.model.modelutils;
/**
 * TransactionType Enum.
 * @author Keerthi Gadde
 *
 */
public enum TransactionType {
	Debit("DEBIT"), Credit("CREDIT");
	private String transType;

	private TransactionType(String transType) {
		this.transType = transType;
	}

	public String getTransactionType() {
		return transType;
	}
}
