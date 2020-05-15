package com.wordpuzzle.Examples.Chap_4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class UseFileChooser extends JFrame
								implements ActionListener
{
	private JPanel displayPanel, buttonPanel;
	private JLabel surnameLabel, firstNamesLabel, markLabel;
	private JTextField surnameBox, firstNamesBox, markBox;
	private JButton openButton, nextButton;
	private Scanner input;

	public static void main(String[] args)
	{
		UseFileChooser frame = new UseFileChooser();
		frame.setSize(350,150);
		frame.setVisible(true);
	}

	public UseFileChooser()
	{
		setTitle("FileChooser Demo");

		setLayout(new BorderLayout());

		displayPanel = new JPanel();
		displayPanel.setLayout(new GridLayout(3,2));

		surnameLabel = new JLabel("Surname");
		firstNamesLabel = new JLabel("First names");
		markLabel = new JLabel("Mark");
		surnameBox= new JTextField();
		firstNamesBox = new JTextField();
		markBox = new JTextField();

		//For this application, user should not be able
		//to change any records...
		surnameBox.setEditable(false);
		firstNamesBox.setEditable(false);
		markBox.setEditable(false);

		displayPanel.add(surnameLabel);
		displayPanel.add(surnameBox);
		displayPanel.add(firstNamesLabel);
		displayPanel.add(firstNamesBox);
		displayPanel.add(markLabel);
		displayPanel.add(markBox);

		add(displayPanel, BorderLayout.NORTH);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		openButton = new JButton("Open File");
		openButton.addActionListener(this);
		nextButton = new JButton("Next Record");
		nextButton.addActionListener(this);
		nextButton.setEnabled(false); //(No file open yet.)

		buttonPanel.add(openButton);
		buttonPanel.add(nextButton);

		add(buttonPanel, BorderLayout.SOUTH);

		addWindowListener(
			new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					if (input != null)	//A file is open.
						input.close();
					System.exit(0);
				}
			}
		);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == openButton)
		{
			try
			{
				openFile();
			}
			catch(IOException ioEx)
			{
				JOptionPane.showMessageDialog(this,
								"Unable to open file!");
			}
		}
		else
		{
			try
			{
				readRecord();
			}
			catch(EOFException eofEx)
			{
				nextButton.setEnabled(false);//No 'next record'.
				JOptionPane.showMessageDialog(this,
					"Incomplete record!\nEnd of file reached.");
			}
			catch(IOException ioEx)
			{
				JOptionPane.showMessageDialog(this,
								"Unable to read file!");
			}
		}
	}

	public void openFile() throws IOException
	{
		if (input != null)	//A file is already open,
						// so needs to be closed first.
		{
			input.close();
			input = null;
		}
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(
								JFileChooser.FILES_ONLY);

		int selection = fileChooser.showOpenDialog(null);
		//Window opened in centre of screen.

		if (selection == JFileChooser.CANCEL_OPTION)
			return;

		File results = fileChooser.getSelectedFile();
		if (results == null||results.getName().equals(""))
		//No file name entered by user.
		{
			JOptionPane.showMessageDialog(this,
								"Invalid selection!");
			return;
		}
		input = new Scanner(results);
		readRecord();	//Read and display first record.
		nextButton.setEnabled(true);	//(File now open.)
	}

	public void readRecord() throws IOException
	{
		String surname, firstNames, textMark;

		//Clear text fields...
		surnameBox.setText("");
		firstNamesBox.setText("");
		markBox.setText("");

		if (input.hasNext())	//Not at end of file.
		{
			surname = input.nextLine();
			surnameBox.setText(surname);
		}
		else
		{
			JOptionPane.showMessageDialog(this,
								"End of file reached.");
			nextButton.setEnabled(false);//No 'next record'.
			return;
		}

		//Should cater for possibility of incomplete records...

		if (!input.hasNext())
			throw (new EOFException());
		//Otherwise...
		firstNames = input.nextLine();
		firstNamesBox.setText(firstNames);

		if (!input.hasNext())
			throw (new EOFException());
		//Otherwise...
		textMark = input.nextLine();
		markBox.setText(textMark);
	}
}
