package dataaccess;

import java.util.HashMap;

import business.Book;
import business.CheckoutRecord;
import business.LibraryMember;
import dataaccess.DataAccessFacade.StorageType;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	//public HashMap<String,CheckoutRecord> readCheckoutMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member); 
	//public void saveCheckoutRecord(CheckoutRecord checkout); 
	public void updateBooks(HashMap<String,Book> bookHash);
	public void updateMembers(HashMap<String,LibraryMember> librarymembers);
}
