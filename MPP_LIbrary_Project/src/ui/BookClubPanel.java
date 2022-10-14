package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

	LoginPanel loginPanel;
	AllBookIDsPanel allBookIdsPanel;
	AddNewBookPanel addNewBookPanel;
	AddMemberPanel addMemberPanel;
	AllMemberIDsPanel allMemberIDsPanel;
	CheckoutBookPanel checkoutBookPanel;
	AddBookCopy addBookCopyPanel;
	SearchCheckoutRecordPanel searchCheckoutRecordPanel;
	OverDueListPanel overDueListPanel;
	// boolean startup = true;

	// list items
	ListItem loginListItem = new ListItem("Login", true);
	ListItem addBookItem = new ListItem("Add Book", false);
	ListItem allBookItem = new ListItem("All Book IDs", false);
	ListItem addMemberItem = new ListItem("Add Member", false);
	ListItem checkoutBookItem = new ListItem("Checkout Book", false);
	ListItem addBookCopyItem = new ListItem("Add Book Copy", false);
	ListItem searchCheckoutRecordItem = new ListItem("Checkout Record", false);
	ListItem overDueListItem = new ListItem("Overdue", false);
	ListItem allMemberIDsItem = new ListItem("All Member IDs", false);

	//This items will be hidden for this role
	ListItem[] adminItems = { loginListItem, allBookItem , allMemberIDsItem, checkoutBookItem, overDueListItem}; 
	ListItem[] librarianItems = { loginListItem, allBookItem ,addBookItem, addMemberItem, addBookCopyItem,allMemberIDsItem , overDueListItem};
	ListItem[] noItems = { loginListItem };

	public ListItem[] getAdminItems() {
		return adminItems;
	}

	public ListItem[] getLibrarianItems() {
		return librarianItems;
	}

	public ListItem[] getNoItems() {
		return noItems;
	}
	
	public JList<ListItem> getLinkList() {
		return linkList;
	}

	public BookClubPanel() {
		Util.adjustLabelFont(statusBar, Util.DARK_BLUE, true);
		
		setSize(1200, 650);
		
//		FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 0, 0);
//		statusBar.setLayout(fl);
		
//		JFrame frame = new JFrame ("Test");
//		JScrollPane scroll = new JScrollPane (statusBar, 
//		   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//
//		frame.add(scroll);
//		frame.setVisible (true);
		
	

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
		
		
		//outerPane.add(scroll);
		
		add(outerPane, BorderLayout.CENTER);
		loginPanel.setBookClub(this);

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
		model.addElement(overDueListItem);

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
						updateData();
					}
				}
				return c;
			}

		});
	}

	public void createMainPanels() {
		// login
		loginPanel = new LoginPanel();
		JPanel login = loginPanel.getMainPanel();

		// add book
		addNewBookPanel = new AddNewBookPanel();
		JPanel addBookPanel = addNewBookPanel.getMainPanel();

		// all book IDs
		allBookIdsPanel = new AllBookIDsPanel();
		JPanel allBookIDsPanel = allBookIdsPanel.getMainPanel();

		addMemberPanel = new AddMemberPanel();
		JPanel memberP = this.addMemberPanel.getMainPanel();

		allMemberIDsPanel = new AllMemberIDsPanel();
		JPanel allMember = allMemberIDsPanel.getMainPanel();
		
		checkoutBookPanel = new CheckoutBookPanel();
		JPanel checkout = checkoutBookPanel.getMainPanel();
		
		addBookCopyPanel = new AddBookCopy();
		JPanel addBookCopy = addBookCopyPanel.getMainPanel();
		
		searchCheckoutRecordPanel = new SearchCheckoutRecordPanel();
		JPanel searchCheckoutRecord = searchCheckoutRecordPanel.getMainPanel();
		
		overDueListPanel = new OverDueListPanel();
		JPanel overDueList = overDueListPanel.getMainPanel();

		cards = new JPanel(new CardLayout());
		cards.add(login, loginListItem.getItemName());
		cards.add(allBookIDsPanel, allBookItem.getItemName());
		cards.add(addBookPanel, addBookItem.getItemName());
		cards.add(memberP, addMemberItem.getItemName());
		cards.add(allMember, allMemberIDsItem.getItemName());
		cards.add(checkout, checkoutBookItem.getItemName());
		cards.add(addBookCopy, addBookCopyItem.getItemName());
		cards.add(searchCheckoutRecord, searchCheckoutRecordItem.getItemName());
		cards.add(overDueList, overDueListItem.getItemName());


	}

	@Override
	public void updateData() {
		
		loginPanel.updateData();
		addNewBookPanel.updateData();
		allBookIdsPanel.updateData();
		addMemberPanel.updateData();
		allMemberIDsPanel.updateData();
		checkoutBookPanel.updateData();
		addBookCopyPanel.updateData();
		searchCheckoutRecordPanel.updateData();
		overDueListPanel.updateData();
		
	
	}
}