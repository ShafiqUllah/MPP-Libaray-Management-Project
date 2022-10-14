package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;

import com.sun.jdi.event.EventQueue;

import controller.LoginController;
import dataaccess.Auth;
import dataaccess.Data;
import dataaccess.User;
import util.Util;

public class LoginPanel implements MessageableWindow {
	BookClubPanel bookClub;

	public void setBookClub(BookClubPanel club) {
		bookClub = club;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	private JPanel mainPanel;
	private JPanel upperHalf;
	private JPanel middleHalf;
	// private JPanel lowerHalf;
	private JPanel container;

	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel lowerPanel;
	private JPanel leftTextPanel;
	private JPanel rightTextPanel;

	private JTextField username;
	private JTextField password;
	private JLabel label;
	private JButton loginButton;
	private JButton logoutButton;

	public LoginPanel() {

		mainPanel = new JPanel();
		defineUpperHalf();
		defineMiddleHalf();
		mainPanel.setLayout(null);

		mainPanel.add(upperHalf);
		mainPanel.add(middleHalf);

		btnLogout = new JButton("logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Data.currentAuth = null;
				displayInfo("Logout successful");
				updateLeftPanel(Data.currentAuth);
				bookClub.repaint();

			}
		});
		btnLogout.setBounds(57, 49, 108, 23);
		middleHalf.add(btnLogout);
		// mainPanel.add(lowerHalf, BorderLayout.SOUTH);

	}

	private void defineUpperHalf() {

		upperHalf = new JPanel();
		upperHalf.setBounds(0, 0, 450, 117);
		defineTopPanel();
		defineMiddlePanel();
		defineLowerPanel();
		upperHalf.setLayout(null);
		upperHalf.add(topPanel);
		upperHalf.add(middlePanel);
		upperHalf.add(lowerPanel);

	}

	private void defineMiddleHalf() {
		middleHalf = new JPanel();
		middleHalf.setBounds(0, 128, 450, 172);
		middleHalf.setLayout(null);

	}

	private void defineTopPanel() {
		topPanel = new JPanel();
		topPanel.setBounds(0, 0, 450, 24);

		JLabel loginLabel = new JLabel("Login");
		Util.adjustLabelFont(loginLabel, Color.BLUE.darker(), true);

		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(loginLabel);

	}

	private void defineMiddlePanel() {
		middlePanel = new JPanel();
		middlePanel.setBounds(0, 24, 450, 41);
		defineLeftTextPanel();
		defineRightTextPanel();
		middlePanel.setLayout(null);
		middlePanel.add(leftTextPanel);
		middlePanel.add(rightTextPanel);
	}

	private void defineLowerPanel() {
		lowerPanel = new JPanel();
		lowerPanel.setBounds(0, 65, 450, 48);
		lowerPanel.setLayout(null);
		loginButton = new JButton("Login");
		loginButton.setBounds(57, 11, 117, 23);
		addLoginButtonListener(loginButton);
		lowerPanel.add(loginButton);
	}

	private void defineLeftTextPanel() {

		JPanel topText = new JPanel();
		topText.setBounds(0, 0, 166, 20);
		JPanel bottomText = new JPanel();
		bottomText.setBounds(0, 20, 96, 11);
		bottomText.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

		username = new JTextField(10);
		username.setBounds(5, 0, 86, 20);
		label = new JLabel("Username");
		label.setFont(Util.makeSmallFont(label.getFont()));
		topText.setLayout(null);
		topText.add(username);
		bottomText.add(label);

		leftTextPanel = new JPanel();
		leftTextPanel.setBounds(5, 5, 176, 31);
		leftTextPanel.setLayout(null);
		leftTextPanel.add(topText);
		leftTextPanel.add(bottomText);
	}

	private void defineRightTextPanel() {

		JPanel topText = new JPanel();
		topText.setBounds(0, 0, 130, 20);
		JPanel bottomText = new JPanel();
		bottomText.setBounds(0, 20, 96, 11);
		bottomText.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

		password = new JPasswordField(10);
		password.setBounds(5, 0, 86, 20);
		label = new JLabel("Password");
		label.setFont(Util.makeSmallFont(label.getFont()));
		topText.setLayout(null);
		topText.add(password);
		bottomText.add(label);

		rightTextPanel = new JPanel();
		rightTextPanel.setBounds(191, 5, 152, 31);
		rightTextPanel.setLayout(null);
		rightTextPanel.add(topText);
		rightTextPanel.add(bottomText);
	}

	private void addLoginButtonListener(JButton butn) {
		butn.addActionListener(evt -> {

			String user = username.getText().trim();
			String pwd = password.getText().trim();
			if (user.length() == 0 || pwd.length() == 0) {
				displayError("Id and Password fields must be nonempty");
			} else {
				LoginController login = new LoginController(user, pwd);
				if (login.checkUser()) {
					Auth auth = LoginController.currentAuth;
					System.out.println("Succesfully logged in as " + auth);

					Data.currentAuth = auth;
					displayInfo("Login successful");
					updateLeftPanel(Data.currentAuth);
					bookClub.repaint();
				} else {
					displayError("Login failed!");
				}
			}

		});
	}

	private void updateLeftPanel(Auth auth) {
		if (auth == Auth.LIBRARIAN)
			sellerItems();
		else if (auth == Auth.ADMIN)
			memberItems();
		else if (auth == Auth.BOTH)
			bothItems();
		else if( auth == null) {
			noItems();
		}

	}

	private void sellerItems() {
		ListItem[] adminItems = bookClub.getAdminItems();
		updateList(adminItems);
	}

	private void memberItems() {
		ListItem[] librarianItems = bookClub.getMemberItems();
		updateList(librarianItems);
	}

	private void bothItems() {
		updateList(null);
	}
	
	private void noItems() {
		ListItem[] librarianItems =  bookClub.getNoItems();
		updateList(librarianItems);
	}

	@SuppressWarnings("unchecked")
	private void updateList(ListItem[] items) {
		JList<ListItem> linkList = bookClub.getLinkList();
		DefaultListModel<ListItem> model = (DefaultListModel) linkList.getModel();
		int size = model.getSize();
		if (items != null) {
			java.util.List<Integer> indices = new ArrayList<>();
			for (ListItem item : items) {
				int index = model.indexOf(item);
				indices.add(index);
				ListItem next = (ListItem) model.get(index);
				next.setHighlight(true);

			}
			for (int i = 0; i < size; ++i) {
				if (!indices.contains(i)) {
					ListItem next = (ListItem) model.get(i);
					next.setHighlight(false);
				}
			}
		} else {
			for (int i = 0; i < size; ++i) {
				ListItem next = (ListItem) model.get(i);
				next.setHighlight(true);
			}

		}
	}

	@Override
	public void updateData() {
		// nothing to do
		username.setText("");
		password.setText("");

		mainPanel.repaint();
	}

	private static final long serialVersionUID = 3618976789175941432L;
	private JButton btnLogout;

}
