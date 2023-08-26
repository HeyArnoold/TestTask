package dto.models.jsonModels;

import com.google.gson.annotations.SerializedName;

public class TransactionModel {
	@SerializedName("date")
	private String date;
	@SerializedName("amount")
	private long amount;
	@SerializedName("success")
	private boolean success;
	@SerializedName("deposit")
	private boolean deposit;
	@SerializedName("withdrawl")
	private boolean withdrawal;
	@SerializedName("type")
	private String type;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isDeposit() {
		return deposit;
	}

	public void setDeposit(boolean deposit) {
		this.deposit = deposit;
	}

	public boolean isWithdrawal() {
		return withdrawal;
	}

	public void setWithdrawal(boolean withdrawal) {
		this.withdrawal = withdrawal;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}