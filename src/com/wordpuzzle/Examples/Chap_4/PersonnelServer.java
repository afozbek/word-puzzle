package com.wordpuzzle.Examples.Chap_4;

import java.io.*;
import java.net.*;
import java.util.*;

public class PersonnelServer
{
	private static ServerSocket serverSocket;
	private static final int PORT = 1234;
	private static Socket link;
	private static Vector<Personnel> staffVectorOut;
	private static Scanner inStream;
	private static ObjectOutputStream outStream;


	public static void main(String[] args)
	{
		System.out.println("Opening port...\n");
		try
		{
			serverSocket = new ServerSocket(PORT);
		}
		catch(IOException ioEx)
		{
			System.out.println("Unable to attach to port!");
			System.exit(1);
		}

		staffVectorOut = new Vector<Personnel>();

		Personnel[] staff =
			{new Personnel(123456,"Smith", "John"),
			 new Personnel(234567,"Jones", "Sally Ann"),
			 new Personnel(999999,"Black", "James Paul")};

		for (int i=0; i<staff.length; i++)
			staffVectorOut.add(staff[i]);
		startServer();
	}

	private static void startServer()
	{
		do
		{
			try
			{
				link = serverSocket.accept();

				inStream = new Scanner(link.getInputStream());

				outStream =
					new ObjectOutputStream(link.getOutputStream());
				//The above line and associated declaration
				//are the only really new code featured in
				//this example.

				String message = inStream.nextLine();
				if (message.equals("SEND PERSONNEL DETAILS"))
				{
					outStream.writeObject(staffVectorOut);
					outStream.close();
				}
				System.out.println("\n* Closing connection... *");
				link.close();
			}
			catch(IOException ioEx)
			{
				ioEx.printStackTrace();
			}
		}while (true);
	}
}
