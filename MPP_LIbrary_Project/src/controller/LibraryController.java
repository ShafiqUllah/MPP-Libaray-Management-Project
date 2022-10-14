package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import business.Address;
import business.Author;
import business.Book;
import business.BookCopy;
import business.CheckoutRecord;
import business.LibraryMember;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public class LibraryController {
	DataAccess da;

	private static LibraryController instance = new LibraryController();

	public static LibraryController getInstance() {
		return instance;
	}

	LibraryController() {
		da = new DataAccessFacade();
	}

	public void createLibraryMember(String memberId, String firstName, String lastName, String state, String street,
			String city, String zip, String telephoneNo) {
		Address ad = new Address(street, city, state, zip);
		da.saveNewMember(new LibraryMember(memberId, firstName, lastName, telephoneNo, ad));
	}

	public void addBookCopy(String ISBN, int copyNumber) {
		for(int i =0; i< copyNumber; i++) {
			HashMap<String, business.Book> bookCopyHash = da.readBooksMap();
			business.Book book = (business.Book) bookCopyHash.get(ISBN);
			if (book != null) {
				book.addCopy();
			} else
				return;
			bookCopyHash.put(ISBN, book);
			da.updateBooks(bookCopyHash);

			bookCopyHash = da.readBooksMap();
		}
	}

	public void addBook(String isbn, String title, int maxCheckoutLength, List<Author> author, int numOfCopies) {
		business.Book newBook = new Book(isbn, title, maxCheckoutLength, author);
		for (int i = 0; i < numOfCopies; i++) {
			newBook.addCopy();
		}
		HashMap<String, Book> sBook = da.readBooksMap();
		sBook.put(isbn, newBook);
		da.updateBooks(sBook);
	}

	public CheckoutRecord findCheckoutEntry(String memberId) {
		HashMap<String, LibraryMember> mm = new HashMap<String, LibraryMember>();
		mm = da.readMemberMap();

		if (mm != null)
			return mm.get(memberId).getCheckoutRecord();
		else
			return null;
	}

	public void overDueList(String isbn) {

	}

	public CheckoutRecord checkout(String ibsn, String memberID) {

		boolean isCheckoutComplete = false;
		HashMap<String, Book> books = new HashMap<String, Book>();
		books = da.readBooksMap();
		boolean bookFound = books.containsKey(ibsn);

		HashMap<String, LibraryMember> members = new HashMap<String, LibraryMember>();
		members = da.readMemberMap();
		boolean memberFound = members.containsKey(memberID);

		Book book = books.get(ibsn);
		List<BookCopy> bookCopies;
		bookCopies = book.getBookCopy();
		BookCopy bookCopy = null;
		int bookCopyIndex = -1;
		for (BookCopy b : bookCopies) {
			bookCopyIndex++;
			if (b.isAvailable()) {
				bookCopy = b;

				break;
			}

		}
		if (bookCopy != null && bookFound && memberFound) {
			LibraryMember lm = members.get(memberID);
			CheckoutRecord checkoutRecord;
			CheckoutRecord oldRecord = members.get(memberID).getCheckoutRecord();
			if (oldRecord != null)
				checkoutRecord = oldRecord;
			else
				checkoutRecord = new CheckoutRecord();
			checkoutRecord.addCheckoutEntry(bookCopy);
			lm.setCheckoutRecord(checkoutRecord);
			members.put(memberID, lm);
			da.updateMembers(members);

			books.get(ibsn).getBookCopy().get(bookCopyIndex).setAvailable(false);
			books.get(ibsn).getBookCopy().get(bookCopyIndex).setLendedBy(memberID);
			da.updateBooks(books);

			isCheckoutComplete = true;
		} else {
			if (!memberFound)
				System.out.println("Member not found.");

			if (!bookFound)
				System.out.println("Book not found.");
			if (bookCopy == null)
				System.out.println("Book Copy not avialable.");

			isCheckoutComplete = false;
		}
		if (isCheckoutComplete)
			return findCheckoutEntry(memberID);
		else
			return null;
	}

	public List<String> allBookIds() {
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}

	public List<String> allMemberIds() {

		List<String> retval = new ArrayList<>();

		retval.addAll(da.readMemberMap().keySet());
		System.out.println(Arrays.toString(retval.toArray()));

		List<LibraryMember> listOfLibraryMembers = new ArrayList<>();
		listOfLibraryMembers.addAll(da.readMemberMap().values());
		for (LibraryMember s : listOfLibraryMembers) {
			System.out.println(s.getFirstName());
		}

		return retval;
	}

}