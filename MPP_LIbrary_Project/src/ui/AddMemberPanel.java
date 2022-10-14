package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.LibraryController;
import exceptions.InvalidFieldException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddMemberPanel extends JFrame implements MessageableWindow {

	private JPanel contentPane;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField telNum;
	private JTextField street;
	private JTextField city;
	private JTextField state;
	private JTextField zip;
	private JTextField tfMemberId;

	public JPanel getMainPanel() {
		return contentPane;
	}

	/**
	 * Create the frame.
	 */
	public AddMemberPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(39, 39, 46, 14);
		contentPane.add(lblNewLabel);

		firstName = new JTextField();
		firstName.setToolTipText("first name");
		firstName.setBounds(145, 33, 86, 20);
		contentPane.add(firstName);
		firstName.setColumns(10);

		lastName = new JTextField();
		lastName.setToolTipText("last name");
		lastName.setBounds(250, 33, 86, 20);
		contentPane.add(lastName);
		lastName.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Telephone number");
		lblNewLabel_1.setBounds(10, 64, 99, 14);
		contentPane.add(lblNewLabel_1);

		telNum = new JTextField();
		telNum.setBounds(145, 58, 86, 20);
		contentPane.add(telNum);
		telNum.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Street");
		lblNewLabel_2.setBounds(39, 88, 46, 14);
		contentPane.add(lblNewLabel_2);

		street = new JTextField();
		street.setBounds(145, 82, 86, 20);
		contentPane.add(street);
		street.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("City");
		lblNewLabel_3.setBounds(39, 114, 46, 14);
		contentPane.add(lblNewLabel_3);

		city = new JTextField();
		city.setBounds(145, 108, 86, 20);
		contentPane.add(city);
		city.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Zip");
		lblNewLabel_4.setBounds(39, 160, 46, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("State");
		lblNewLabel_5.setBounds(39, 138, 46, 14);
		contentPane.add(lblNewLabel_5);

		state = new JTextField();
		state.setBounds(145, 132, 86, 20);
		contentPane.add(state);
		state.setColumns(10);

		zip = new JTextField();
		zip.setBounds(145, 157, 86, 20);
		contentPane.add(zip);
		zip.setColumns(10);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tfMemberId.getText().isEmpty() || firstName.getText().isEmpty() || lastName.getText().isEmpty()
						|| state.getText().isEmpty() || street.getText().isEmpty() || city.getText().isEmpty()
						|| zip.getText().isEmpty() || telNum.getText().isEmpty()) {
					displayError("Empty Fied !!!! Please enter each field to perform the action");
					
					throw new InvalidFieldException("Empty Field not accesptable");
				} else {
					LibraryController lc = LibraryController.getInstance();
					lc.createLibraryMember(tfMemberId.getText(), firstName.getText(), lastName.getText(),
							state.getText(), street.getText(), city.getText(), zip.getText(), telNum.getText());
					displayInfo("Add Member successful");
				}

			}
		});
		btnSave.setForeground(Color.BLUE);
		btnSave.setBounds(277, 173, 89, 49);
		contentPane.add(btnSave);

		JLabel lblNewLabel_6 = new JLabel("Member ID");
		lblNewLabel_6.setBounds(39, 190, 70, 14);
		contentPane.add(lblNewLabel_6);

		tfMemberId = new JTextField();
		tfMemberId.setBounds(145, 187, 86, 20);
		contentPane.add(tfMemberId);
		tfMemberId.setColumns(10);

	}

	@Override
	public void updateData() {
		// TODO Auto-generated method stub

	}
}
