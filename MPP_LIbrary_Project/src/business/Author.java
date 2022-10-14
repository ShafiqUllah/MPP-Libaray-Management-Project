package business;

import java.io.Serializable;

public class Author extends Person implements Serializable {

	private String shortBio;

	public Author(String fName, String Lname, String pNo, Address address, String shortBio) {
		super(fName, Lname, address, pNo);
		this.shortBio = shortBio;
	}

	public String getShortBio() {
		return shortBio;
	}

	public void setShortBio(String shortBio) {
		this.shortBio = shortBio;
	}
}
