package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.LibraryController;
import exceptions.InvalidFieldException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddBookCopy extends JPanel implements MessageableWindow {
	private JTextField isbn_number;
	private JTextField number_of_copy;

	public JPanel getMainPanel() {
		return this;
	}

	/**
	 * Create the panel.
	 */
	public AddBookCopy() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setBounds(72, 94, 46, 14);
		add(lblNewLabel);

		isbn_number = new JTextField();
		isbn_number.setBounds(226, 91, 86, 20);
		add(isbn_number);
		isbn_number.setColumns(10);

		JButton btnNewButton = new JButton("Add Copy");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (isbn_number.getText().isEmpty() || number_of_copy.getText().isEmpty()) {

					displayError("Empty Field !!!! Please enter each field to perform the action");
					throw new InvalidFieldException("Empty Field not accesptable");

				} else {
					LibraryController lc = LibraryController.getInstance();
					boolean status = lc.addBookCopy(isbn_number.getText(), Integer.parseInt(number_of_copy.getText()));
					if (status)
						displayInfo("Add Copy of Books successful");
					else
						displayError("Failed !!!! Check the ISBN number again...");
				}
			}
		});
		btnNewButton.setBounds(175, 180, 95, 54);
		add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("Number of Copy");
		lblNewLabel_1.setBounds(72, 132, 106, 14);
		add(lblNewLabel_1);

		number_of_copy = new JTextField();
		number_of_copy.setBounds(226, 126, 86, 20);
		add(number_of_copy);
		number_of_copy.setColumns(10);

	}

	@Override
	public void updateData() {
		// TODO Auto-generated method stub

	}
}
