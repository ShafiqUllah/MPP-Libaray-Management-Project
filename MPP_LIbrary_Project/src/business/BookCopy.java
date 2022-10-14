package business;

import java.io.Serializable;
import java.util.UUID;

public class BookCopy  implements Serializable{
	private int copyNo;
	private Book book;
	private boolean isAvailable;
	private String lendedBy;
	
	public BookCopy(int copyNo, Book book) {
		super();
		this.copyNo = copyNo;
		this.book = book;
		isAvailable=true;
	}
	
	public int getCopyNo() {
		return copyNo;
	}

	public void setCopyNo(int copyNo) {
		this.copyNo = copyNo;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getLendedBy() {
		return lendedBy;
	}

	public void setLendedBy(String lendedBy) {
		this.lendedBy = lendedBy;
	}
}
