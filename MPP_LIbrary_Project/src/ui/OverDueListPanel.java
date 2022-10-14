package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.LibraryController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OverDueListPanel extends JPanel implements MessageableWindow {
	private JTextField isbnNumber;

	public JPanel getMainPanel() {
		return this;
	}

	/**
	 * Create the panel.
	 */
	public OverDueListPanel() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("ISBN Number");
		lblNewLabel.setBounds(76, 73, 86, 14);
		add(lblNewLabel);

		isbnNumber = new JTextField();
		isbnNumber.setBounds(194, 70, 86, 20);
		add(isbnNumber);
		isbnNumber.setColumns(10);

		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LibraryController lc = LibraryController.getInstance();
				String result = lc.overDueList(isbnNumber.getText());
				
				if(result.isEmpty() || result.length() == 0 || result == null) {
					displayError("No Data Found");
				}else {
					displayInfo(result);
				}

			}
		});
		btnNewButton.setBounds(159, 147, 89, 23);
		add(btnNewButton);

	}

	@Override
	public void updateData() {
		// TODO Auto-generated method stub
		isbnNumber.setText("");
		repaint();

	}

}
