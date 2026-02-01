package com.ui.pojos;

public class AddressPOJO {
	private String company;
	private String addressLine1;
	private String addressLine2;
	private String postCode;
	private String city;
	private String homePhoneNumber;
	private String mobileNumber;
	private String otherinformation;
	private String addresTitle;
	private String vatNumber;
	private String state;
	public AddressPOJO(String company, String addressLine1, String addressLine2, String postCode, String city,
			String homePhoneNumber, String mobileNumber, String otherinformation, String addresTitle, String vatNumber,
			String state) {
		super();
		this.company = company;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.postCode = postCode;
		this.city = city;
		this.homePhoneNumber = homePhoneNumber;
		this.mobileNumber = mobileNumber;
		this.otherinformation = otherinformation;
		this.addresTitle = addresTitle;
		this.vatNumber = vatNumber;
		this.state = state;
	}
	public String getCompany() {
		return company;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public String getPostCode() {
		return postCode;
	}
	public String getCity() {
		return city;
	}
	public String getHomePhoneNumber() {
		return homePhoneNumber;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public String getOtherinformation() {
		return otherinformation;
	}
	public String getAddresTitle() {
		return addresTitle;
	}
	public String getVatNumber() {
		return vatNumber;
	}
	public String getState() {
		return state;
	}
	@Override
	public String toString() {
		return "AddressPOJO [company=" + company + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
				+ ", postCode=" + postCode + ", city=" + city + ", homePhoneNumber=" + homePhoneNumber
				+ ", mobileNumber=" + mobileNumber + ", otherinformation=" + otherinformation + ", addresTitle="
				+ addresTitle + ", vatNumber=" + vatNumber + ", state=" + state + "]";
	}
	
}
