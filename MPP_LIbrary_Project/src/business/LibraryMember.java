package business;

public class LibraryMember extends Person{
 private String memberId;
private CheckoutRecord checkoutRecord;

public LibraryMember(String memberId, String firstName, String lastName, String PhoneNumber, Address address ) {
	super(firstName,lastName,address,PhoneNumber);
	this.memberId = memberId;
}

public String getMemberId() {
	return memberId;
}

public void setMemberID(String memberID) {
	this.memberId = memberID;
}

public CheckoutRecord getCheckoutRecord() {
	return checkoutRecord;
}

public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
	this.checkoutRecord = checkoutRecord;
}
 
}
