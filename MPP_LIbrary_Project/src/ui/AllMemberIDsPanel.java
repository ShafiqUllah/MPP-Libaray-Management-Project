package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.util.Collections;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.LibraryController;
import dataaccess.Data;
import util.Util;

public class AllMemberIDsPanel implements MessageableWindow {
	
	public JPanel getMainPanel() {
		return mainPanel;
	}
	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel middlePanel;
	private TextArea textArea;
	

	public AllMemberIDsPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		defineTopPanel();
		defineMiddlePanel();
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(middlePanel, BorderLayout.CENTER);
		
	}
	
	public void defineTopPanel() {
		topPanel = new JPanel();
		JLabel titlesLabel = new JLabel("All Member IDs");
		Util.adjustLabelFont(titlesLabel, Util.DARK_BLUE, true);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(titlesLabel);
	}
	
	public void defineMiddlePanel() {
		middlePanel = new JPanel();
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 25, 25);
		middlePanel.setLayout(fl);
		textArea = new TextArea(8, 20);
		textArea.setEditable(false);
		textArea.setBackground(Color.WHITE);
		updateTextAreaData(LibraryController.getInstance().allMemberIds());
		middlePanel.add(textArea);
		
	}
	
	public void updateData() {
		//populate
		updateTextAreaData(LibraryController.getInstance().allMemberIds());
		mainPanel.repaint();
	}
	
	private void updateTextAreaData(List<String> list) {
		List<String> titles = list;
		Collections.sort(titles);
		StringBuilder sb = new StringBuilder();
		for(String s: titles) {
			sb.append(s + "\n");
		}
		textArea.setText(sb.toString());
	}
	
}
