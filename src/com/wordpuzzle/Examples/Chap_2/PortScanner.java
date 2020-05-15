package com.wordpuzzle.Examples.Chap_2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class PortScanner extends JFrame
							implements ActionListener
{
	private JLabel prompt;
	private JTextField hostInput;
	private JTextArea report;
	private JButton seekButton, exitButton;
	private JPanel hostPanel, buttonPanel;
	private static Socket socket = null;

  	public static void main(String[] args)
	{
    	PortScanner frame = new PortScanner();
		frame.setSize(400,300);
     	frame.setVisible(true);

        frame.addWindowListener(
			new WindowAdapter()
			{
				public void windowClosing(WindowEvent event)
				{
					//Check whether a socket is open...
					if (socket != null)
					{
						try
						{
							socket.close();
						}
						catch (IOException ioEx)
						{
							System.out.println(
							  "\n*** Unable to close link!***\n");
							System.exit(1);
						}
					}
					System.exit(0);
				}
			}
		);
 	}

  	public PortScanner()
	{
		hostPanel = new JPanel();

		prompt = new JLabel("Host name: ");
     	hostInput = new JTextField("ivy.shu.ac.uk", 25);
		hostPanel.add(prompt);
		hostPanel.add(hostInput);
     	add(hostPanel,BorderLayout.NORTH);

		report = new JTextArea(10,25);
		add(report,BorderLayout.CENTER);

		buttonPanel = new JPanel();

		seekButton = new JButton("Seek server ports ");
		seekButton.addActionListener(this);
     	buttonPanel.add(seekButton);

     	exitButton = new JButton("Exit");
		exitButton.addActionListener(this);
		buttonPanel.add(exitButton);

		add(buttonPanel,BorderLayout.SOUTH);
	}

   	public void actionPerformed(ActionEvent event)
	{
     	if (event.getSource() == exitButton)
      		System.exit(0);
		//Must have been the 'seek' button that was
		//pressed, so clear the output area of any
		//previous output...
    	report.setText("");

		//Retrieve the URL from the input text field...
     	String host = hostInput.getText();

    	try
		{
			//Convert the URL string into an INetAddress
			//object...
       		InetAddress theAddress =
       						InetAddress.getByName(host);
       		report.append("IP address: " + theAddress + "\n");

      		for (int i = 0; i < 25; i++)
  			{
          		try
				{
					//Attempt to establish a socket on
					//port i...
					socket = new Socket(host, i);

					//If no IOException thrown, there must be
					//a service running on the port...
               		report.append("There is a server on port "
												+ i + ".\n");
              		socket.close();
         		}
         		catch (IOException ioEx)
          		{}// No server on this port
      		}
    	}
     	catch (UnknownHostException uhEx)
     	{
			report.setText("Unknown host!");
		}
	}
}
