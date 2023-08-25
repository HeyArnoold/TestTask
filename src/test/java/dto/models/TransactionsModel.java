package dto.models;

import com.google.gson.annotations.SerializedName;

public class TransactionsModel {
	@SerializedName("1")
	private SectionModel sectionModel1;
	@SerializedName("2")
	private SectionModel sectionModel2;

	public SectionModel getSectionModel1() {
		return sectionModel1;
	}

	public void setSectionModel1(SectionModel sectionModel1) {
		this.sectionModel1 = sectionModel1;
	}

	public SectionModel getSectionModel2() {
		return sectionModel2;
	}

	public void setSectionModel2(SectionModel sectionModel2) {
		this.sectionModel2 = sectionModel2;
	}
}