package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

import business.Address;
import business.Author;
import controller.LibraryController;
import dataaccess.Data;
import dataaccess.DataAccessFacade;
import exceptions.InvalidFieldException;
import util.Util;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class AddNewBookPanel extends JPanel implements MessageableWindow {

	public JPanel getMainPanel() {
		return this;
	}

	private JTextField isbnNUmber;
	private JTextField bookTitle;
	private JTextField bookMaxCheckoutDay;
	private JTextField authorFirstName;
	private JTextField authorLastName;
	private JTextField authorPhoneNumber;
	private JTextField authorBio;
	private JTextField authorStreet;
	private JTextField authorCity;
	private JTextField authorState;
	private JTextField authorZip;

	/**
	 * Create the panel.
	 */

	// for author list
	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel middlePanel;
	private TextArea textArea;
	private List<Author> listOfAuthors = new ArrayList<>();
	private JLabel lblNewLabel_3;
	private JTextField bookCopyNumber;;

	public AddNewBookPanel() {

		setLayout(null);

		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setBounds(27, 11, 46, 14);
		add(lblNewLabel);

		isbnNUmber = new JTextField();
		isbnNUmber.setBounds(175, 8, 86, 20);
		add(isbnNUmber);
		isbnNUmber.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setBounds(27, 36, 46, 14);
		add(lblNewLabel_1);

		bookTitle = new JTextField();
		bookTitle.setBounds(175, 33, 86, 20);
		add(bookTitle);
		bookTitle.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Max Checkout Day");
		lblNewLabel_2.setBounds(27, 61, 138, 14);
		add(lblNewLabel_2);

		bookMaxCheckoutDay = new JTextField();
		bookMaxCheckoutDay.setBounds(175, 58, 86, 20);
		add(bookMaxCheckoutDay);
		bookMaxCheckoutDay.setColumns(10);

		JButton btnNewButton = new JButton("Save Book");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (isbnNUmber.getText().isEmpty() || bookTitle.getText().isEmpty()
						|| bookMaxCheckoutDay.getText().isEmpty() || bookCopyNumber.getText().isEmpty()) {

					displayError("Empty Fied !!!! Please enter each field to perform the action");
					throw new InvalidFieldException("Empty Field not accesptable");
				} else if (listOfAuthors.isEmpty()) {
					
					displayError("Empty Author  !!!! Please enter Author to perform the action");
					throw new InvalidFieldException("Empty Author not accesptable");
				} else {
					LibraryController lc = LibraryController.getInstance();
					lc.addBook(isbnNUmber.getText(), bookTitle.getText(),
							Integer.parseInt(bookMaxCheckoutDay.getText()), listOfAuthors,
							Integer.parseInt(bookCopyNumber.getText()));

					displayInfo("Add Book successful");
				}

			}
		});
		btnNewButton.setBounds(27, 369, 138, 45);
		add(btnNewButton);

		JLabel lblNewLabel_4 = new JLabel("First name");
		lblNewLabel_4.setBounds(353, 11, 72, 14);
		add(lblNewLabel_4);

		authorFirstName = new JTextField();
		authorFirstName.setBounds(432, 8, 86, 20);
		add(authorFirstName);
		authorFirstName.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Last Name");
		lblNewLabel_5.setBounds(353, 36, 72, 14);
		add(lblNewLabel_5);

		authorLastName = new JTextField();
		authorLastName.setBounds(432, 33, 86, 20);
		add(authorLastName);
		authorLastName.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Phone");
		lblNewLabel_6.setBounds(353, 61, 46, 14);
		add(lblNewLabel_6);

		authorPhoneNumber = new JTextField();
		authorPhoneNumber.setBounds(432, 58, 86, 20);
		add(authorPhoneNumber);
		authorPhoneNumber.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("bio");
		lblNewLabel_7.setBounds(353, 92, 46, 14);
		add(lblNewLabel_7);

		authorBio = new JTextField();
		authorBio.setBounds(432, 89, 86, 20);
		add(authorBio);
		authorBio.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Street");
		lblNewLabel_8.setBounds(353, 119, 46, 14);
		add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("city");
		lblNewLabel_9.setBounds(353, 144, 46, 14);
		add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("State");
		lblNewLabel_10.setBounds(353, 169, 46, 14);
		add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("zip");
		lblNewLabel_11.setBounds(353, 194, 46, 14);
		add(lblNewLabel_11);

		authorStreet = new JTextField();
		authorStreet.setBounds(432, 116, 86, 20);
		add(authorStreet);
		authorStreet.setColumns(10);

		authorCity = new JTextField();
		authorCity.setBounds(432, 141, 86, 20);
		add(authorCity);
		authorCity.setColumns(10);

		authorState = new JTextField();
		authorState.setBounds(432, 166, 86, 20);
		add(authorState);
		authorState.setColumns(10);

		authorZip = new JTextField();
		authorZip.setBounds(432, 191, 86, 20);
		add(authorZip);
		authorZip.setColumns(10);

		JButton btnNewButton_1 = new JButton("Add Author");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				listOfAuthors.add(new Author("Shafiq", "ULLAH", "1212121",
//						new Address("10000n south", "Fairfiled", "IOWA", "5227"), "this is bio"));

				Address address = new Address(authorStreet.getText(), authorCity.getText(), authorState.getText(),
						authorZip.getText());
				Author author = new Author(authorFirstName.getText(), authorLastName.getText(),
						authorPhoneNumber.getText(), address, authorBio.getText());

				listOfAuthors.add(author);
				updateData();
			}
		});
		btnNewButton_1.setBounds(371, 244, 130, 23);
		add(btnNewButton_1);

		initAuthorList();

	}

	public void initAuthorList() {
		mainPanel = new JPanel();
		mainPanel.setBounds(24, 100, 200, 230);
		mainPanel.setLayout(new BorderLayout());
		defineTopPanel();
		defineMiddlePanel();
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(middlePanel, BorderLayout.CENTER);

		add(mainPanel);

		lblNewLabel_3 = new JLabel("Number of Copies");
		lblNewLabel_3.setBounds(27, 86, 138, 14);
		add(lblNewLabel_3);

		bookCopyNumber = new JTextField();
		bookCopyNumber.setBounds(175, 83, 86, 20);
		
//		bookCopyNumber.addKeyListener(new KeyAdapter() {
//	         public void keyPressed(KeyEvent ke) {
//	            String value = bookCopyNumber.getText();
//	            int l = value.length();
//	            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
//	            	//bookCopyNumber.setEditable(true);
//	               //label.setText("");
//	            	//bookCopyNumber.setText(bookCopyNumber.getText());
//	            	bookCopyNumber.setToolTipText("");
//	            } else {
//	            	//bookCopyNumber.setEditable(false);
//	            	bookCopyNumber.setText( "");
//	            	
//	            	ke.consume();
//	            	bookCopyNumber.setToolTipText("* Enter only numeric digits(0-9)");
////	            	updateData();
//	            	displayError("* Enter only numeric digits(0-9)");
//	              // label.setText("* Enter only numeric digits(0-9)");
//	            }
//	         }
//	      });
		
	
		add(bookCopyNumber);
		bookCopyNumber.setColumns(10);

	}

	public void defineTopPanel() {
		topPanel = new JPanel();
		JLabel titlesLabel = new JLabel("Author List");
		Util.adjustLabelFont(titlesLabel, Util.DARK_BLUE, true);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(titlesLabel);
	}

	public void defineMiddlePanel() {
		middlePanel = new JPanel();
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 0, 0);
		middlePanel.setLayout(fl);
		textArea = new TextArea(8, 20);
		updateData();
		middlePanel.add(textArea);

	}

	@Override
	public void updateData() {
		// TODO Auto-generated method stub

		List<String> titles = getAuthorNames();

		Collections.sort(titles);
		StringBuilder sb = new StringBuilder();
		for (String s : titles) {
			sb.append(s + "\n");
		}
		textArea.setText(sb.toString());
		repaint();

	}

	private List<String> getAuthorNames() {
		List<String> authorTitleList = new ArrayList<>();

		for (Author a : listOfAuthors) {
			authorTitleList.add(a.getFirstName() + " " + a.getLastName());
		}

		return authorTitleList;
	}
}
