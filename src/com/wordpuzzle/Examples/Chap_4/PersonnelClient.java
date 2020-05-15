package com.wordpuzzle.Examples.Chap_4;

import java.io.*;
import java.net.*;
import java.util.*;

public class PersonnelClient
{
	private static InetAddress host;
	private static final int PORT = 1234;

	public static void main(String[] args)
							throws ClassNotFoundException
	{
		try
		{
			host = InetAddress.getLocalHost();
		}
		catch(UnknownHostException uhEx)
		{
			System.out.println("Host ID not found!");
			System.exit(1);
		}
		talkToServer();
	}

	private static void talkToServer()
							throws ClassNotFoundException
	{
		try
		{
			Socket link = new Socket(host,PORT);

			ObjectInputStream inStream =
				new ObjectInputStream(link.getInputStream());

			PrintWriter outStream =
				new PrintWriter(link.getOutputStream(),true);

			//Set up stream for keyboard entry...
			Scanner userEntry = new Scanner(System.in);

			outStream.println("SEND PERSONNEL DETAILS");
			Vector<Personnel> response =
					(Vector<Personnel>)inStream.readObject();
			/*
			As in VectorSerialise, the compiler will issue
			a warning for the line above.
			Simply ignore this warning.
			*/

			System.out.println("\n* Closing connection... *");
			link.close();

	     	int staffCount = 0;

			for (Personnel person:response)
			{
				staffCount++;
				System.out.println(
							"\nStaff member " + staffCount);
				System.out.println(
						"Payroll number: " + person.getPayNum());
				System.out.println(
								"Surname: " + person.getSurname());
				System.out.println(
						"First names: " + person.getFirstNames());
			}
			System.out.println("\n\n");
		}
		catch(IOException ioEx)
		{
			ioEx.printStackTrace();
		}
	}
}
