package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;

@SuppressWarnings("serial")
public class BookClubPanel extends JFrame implements MessageableWindow {

	String[] links;
	JList<ListItem> linkList;
	JPanel cards;
	public static JTextArea statusBar = new JTextArea("Welcome to the Book Club!");

	LoginPanel lp;
	AllBookIDsPanel allBookIdsPanel;
	AddNewBookPanel addBookP;
	AddMemberPanel addMemberPanel;
	AllMemberIDsPanel allMemberIDs;
	CheckoutBookPanel checkoutBookPanel;
	AddBookCopy addBookCopyPanel;
	SearchCheckoutRecordPanel searchCheckoutRecordPanel;
	// boolean startup = true;

	// list items
	ListItem loginListItem = new ListItem("Login", true);
	ListItem addBookItem = new ListItem("Add Book", true);
	ListItem allBookItem = new ListItem("All Book IDs", true);
	ListItem addMemberItem = new ListItem("Add Member", true);
	ListItem checkoutBookItem = new ListItem("Checkout Book", true);
	ListItem addBookCopyItem = new ListItem("Add Book Copy", true);
	ListItem searchCheckoutRecordItem = new ListItem("Checkout Record", true);
	
	
	ListItem allMemberIDsItem = new ListItem("All Member IDs", true);

	ListItem[] sellerItems = { loginListItem, addBookItem };
	ListItem[] memberItems = { loginListItem, allBookItem };

	public ListItem[] getSellerItems() {
		return sellerItems;
	}

	public ListItem[] getMemberItems() {
		return memberItems;
	}

	public JList<ListItem> getLinkList() {
		return linkList;
	}

	public BookClubPanel() {
		Util.adjustLabelFont(statusBar, Util.DARK_BLUE, true);
		setSize(1200, 750);

		createLinkLabels();
		createMainPanels();
		CardLayout cl = (CardLayout) (cards.getLayout());
		linkList.addListSelectionListener(event -> {
			String value = linkList.getSelectedValue().getItemName();
			boolean allowed = linkList.getSelectedValue().highlight();
			System.out.println(value + " " + allowed);

			// System.out.println("selected = " + value);
			// cl = (CardLayout)(cards.getLayout());
			statusBar.setText("");
			if (!allowed) {
				value = loginListItem.getItemName();
				linkList.setSelectedIndex(0);
			}
			if (value.equals("All Book IDs"))
				allBookIdsPanel.updateData();
			
//			if(value.equals("All Book IDs")) {
//				all.updateData();
//			}
			cl.show(cards, value);

		});

		// set up split panes

		JSplitPane innerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, linkList, cards);
		innerPane.setDividerLocation(180);
		JSplitPane outerPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, innerPane, statusBar);
		outerPane.setDividerLocation(550);
		add(outerPane, BorderLayout.CENTER);
		lp.setBookClub(this);

	}

	public JTextArea getStatusBar() {
		return statusBar;
	}

	public void createLinkLabels() {
		DefaultListModel<ListItem> model = new DefaultListModel<>();
		model.addElement(loginListItem);
		model.addElement(addBookItem);
		model.addElement(addBookCopyItem);
		model.addElement(addMemberItem);
		model.addElement(allBookItem);
		model.addElement(allMemberIDsItem);
		model.addElement(checkoutBookItem);
		model.addElement(searchCheckoutRecordItem);

		linkList = new JList<ListItem>(model);
		linkList.setCellRenderer(new DefaultListCellRenderer() {

			@SuppressWarnings("rawtypes")
			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (value instanceof ListItem) {
					ListItem nextItem = (ListItem) value;
					setText(nextItem.getItemName());
					if (nextItem.highlight()) {
						setForeground(Util.LINK_AVAILABLE);
					} else {
						setForeground(Util.LINK_NOT_AVAILABLE);
					}
					if (isSelected) {
						setForeground(Color.BLACK);
						setBackground(new Color(236, 243, 245));
						// setBackground(Color.WHITE);	
					}
				}
				return c;
			}

		});
	}

	public void createMainPanels() {
		// login
		lp = new LoginPanel();
		JPanel loginPanel = lp.getMainPanel();

		// add book
		addBookP = new AddNewBookPanel();
		JPanel addBookPanel = addBookP.getMainPanel();

		// all book IDs
		allBookIdsPanel = new AllBookIDsPanel();
		JPanel allBookIDsPanel = allBookIdsPanel.getMainPanel();

		addMemberPanel = new AddMemberPanel();
		JPanel memberP = this.addMemberPanel.getMainPanel();

		allMemberIDs = new AllMemberIDsPanel();
		JPanel allMember = allMemberIDs.getMainPanel();
		
		checkoutBookPanel = new CheckoutBookPanel();
		JPanel checkout = checkoutBookPanel.getMainPanel();
		
		addBookCopyPanel = new AddBookCopy();
		JPanel addBookCopy = addBookCopyPanel.getMainPanel();
		
		searchCheckoutRecordPanel = new SearchCheckoutRecordPanel();
		JPanel searchCheckoutRecord = searchCheckoutRecordPanel.getMainPanel();

		cards = new JPanel(new CardLayout());
		cards.add(loginPanel, loginListItem.getItemName());
		cards.add(allBookIDsPanel, allBookItem.getItemName());
		cards.add(addBookPanel, addBookItem.getItemName());
		cards.add(memberP, addMemberItem.getItemName());
		cards.add(allMember, allMemberIDsItem.getItemName());
		cards.add(checkout, checkoutBookItem.getItemName());
		cards.add(addBookCopy, addBookCopyItem.getItemName());
		cards.add(searchCheckoutRecord, searchCheckoutRecordItem.getItemName());


	}

	@Override
	public void updateData() {
		// nothing to do

	}
}