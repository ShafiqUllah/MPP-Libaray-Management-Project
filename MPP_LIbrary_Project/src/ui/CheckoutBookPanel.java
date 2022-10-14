package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import business.CheckoutRecord;
import controller.LibraryController;
import exceptions.InvalidFieldException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckoutBookPanel extends JPanel implements MessageableWindow {
	
	public JPanel getMainPanel() {
		return this;
	}
	
	
	private JTextField isbnNumber;
	private JTextField memberId;

	/**
	 * Create the panel.
	 */
	public CheckoutBookPanel() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setBounds(79, 42, 86, 14);
		add(lblNewLabel);

		isbnNumber = new JTextField();
		isbnNumber.setBounds(187, 39, 86, 20);
		add(isbnNumber);
		isbnNumber.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Member ID");
		lblNewLabel_1.setBounds(79, 93, 98, 14);
		add(lblNewLabel_1);

		memberId = new JTextField();
		memberId.setBounds(187, 90, 86, 20);
		add(memberId);
		memberId.setColumns(10);

		JButton btnNewButton = new JButton("Check out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(isbnNumber.getText().isEmpty() || memberId.getText().isEmpty()) {
					displayError("Empty Field !!!! Please enter each field to perform the action");
					throw new InvalidFieldException("Empty Field not accesptable");
				}else {
					LibraryController lc =  LibraryController.getInstance();
					CheckoutRecord cc = lc.checkout(isbnNumber.getText(), memberId.getText());

					if (cc != null) {
						System.out.println(cc);
						System.out.println("Checkout successful..");

						displayInfo("Checkout Successful \n" + cc);

					} else {
						System.out.println("Checkout failed. Press enter to continue.");
						displayError("Checkout failed");
					}
				}
			}
		});
		btnNewButton.setBounds(167, 159, 116, 23);
		add(btnNewButton);

	}

	@Override
	public void updateData() {
		// TODO Auto-generated method stub

	}

}
