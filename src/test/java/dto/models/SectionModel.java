package dto.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SectionModel {

	@SerializedName("1001")
	private List<TransactionModel> account1001;

	@SerializedName("1004")
	private List<TransactionModel> account1004;

	public List<TransactionModel> getAccount1001() {
		return account1001;
	}

	public void setAccount1001(List<TransactionModel> account1001) {
		this.account1001 = account1001;
	}

	public List<TransactionModel> getAccount1004() {
		return account1004;
	}

	public void setAccount1004(List<TransactionModel> account1004) {
		this.account1004 = account1004;
	}
}