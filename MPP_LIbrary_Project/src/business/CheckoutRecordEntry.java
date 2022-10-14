package business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutRecordEntry implements Serializable{
	private static final long serialVersionUID = -9000891200460765128L;
	private CheckoutRecord checkoutRecord;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private BookCopy bookCopy;
	public CheckoutRecordEntry(CheckoutRecord checkoutRecord, LocalDate checkoutDate, LocalDate dueDate,BookCopy bookCopy) {
		this.checkoutRecord = checkoutRecord;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
		this.bookCopy=bookCopy;
	}
	public CheckoutRecord getCheckoutRecord() {
		return checkoutRecord;
	}
	public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
		this.checkoutRecord = checkoutRecord;
	}
	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	@Override
	public String toString()
	{
		return "Book ISBN:"+bookCopy.getBook().getIsbn()+" Checkout Date:"+checkoutDate+" Due Date:"+dueDate+" Book Number checked out:"+bookCopy.getCopyNo()+"\n";
	}
	public BookCopy getBookCopy() {
		return bookCopy;
	}
	public void setBookCopy(BookCopy bookCopy) {
		this.bookCopy = bookCopy;
	}
	
//	public void addCheckoutEntry()
//	{
//		
//	}
}
