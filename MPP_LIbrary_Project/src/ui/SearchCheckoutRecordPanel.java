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

public class SearchCheckoutRecordPanel extends JPanel implements MessageableWindow {
	private JTextField memberID;

	/**
	 * Create the panel.
	 */
	public SearchCheckoutRecordPanel() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Member ID");
		lblNewLabel.setBounds(91, 56, 95, 14);
		add(lblNewLabel);

		memberID = new JTextField();
		memberID.setBounds(218, 53, 106, 20);
		add(memberID);
		memberID.setColumns(10);

		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(memberID.getText().isEmpty()) {
					displayError("Member ID can't be empty");
					throw new InvalidFieldException("Empty Field not accesptable");
				}else {
					LibraryController lc = LibraryController.getInstance();
					CheckoutRecord cc = lc.findCheckoutEntry(memberID.getText());
					if (cc != null) {
						System.out.println(cc);
						displayInfo("Checkout Record GET Successful \n" + cc);

					} else {
						System.out.println("Checkout failed. Press enter to continue.");
						displayError("Checkout Record GET failed, Check the Member ID again..");
					}
				}
				
				
			}
		});
		btnNewButton.setBounds(172, 132, 112, 45);
		add(btnNewButton);

	}

	@Override
	public void updateData() {
		// TODO Auto-generated method stub

	}

}
