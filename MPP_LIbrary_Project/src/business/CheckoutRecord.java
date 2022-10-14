package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable{
	private static final long serialVersionUID = -8763215652969004822L;
	//private String memberId;
	public List<CheckoutRecordEntry> checkoutRecordEntries;

	public CheckoutRecord() {
		checkoutRecordEntries = new ArrayList<>();
		
	}
	
	public List<CheckoutRecordEntry> getCheckoutRecordEntries() {
		return checkoutRecordEntries;
	}


	public void addCheckoutEntry(BookCopy bookCopy) {

		LocalDate today = LocalDate.now();
		int maxCheckoutLength = bookCopy.getBook().getMaxCheckoutLength();
		LocalDate dueDate = today.plusDays(maxCheckoutLength);
		CheckoutRecordEntry checkoutEntry = new CheckoutRecordEntry(this, today, dueDate,bookCopy);
		checkoutRecordEntries.add(checkoutEntry);

	}
	



	@Override
	public String toString()
	{
		StringBuilder sb=new StringBuilder();
		sb.append( "Checkout Entry:\n");
		for(CheckoutRecordEntry ce:checkoutRecordEntries)
		{
			sb.append(ce);
		}
		return sb.toString();
	}

}
