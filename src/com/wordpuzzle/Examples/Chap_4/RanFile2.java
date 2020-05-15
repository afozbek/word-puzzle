//Allows the user to retrieve individual account
//records and modify their balances.
package com.wordpuzzle.Examples.Chap_4;


import java.io.*;
import java.util.*;

public class RanFile2
{
	private static final int REC_SIZE=48;
	private static final int SURNAME_SIZE=15;
	private static final int NUM_INITS=3;
	private static long acctNum=0;
	private static String surname, initials;
	private static float balance;

	public static void main(String[] args)	throws IOException
	{
		Scanner input = new Scanner(System.in);
		RandomAccessFile ranAccts =
				new RandomAccessFile("accounts.dat", "rw");
		long numRecords = ranAccts.length()/REC_SIZE;
		String reply;
		long currentPos;		//File pointer position.

		do
		{
			System.out.print("\nEnter account number: ");
			acctNum = input.nextLong();
			while ((acctNum<1) || (acctNum>numRecords))
			{
				System.out.println(
						"\n*** Invalid number! ***\n");
				System.out.print("\nEnter account number: ");
				acctNum = input.nextLong();
			}
			showRecord(ranAccts);	//Defined below.
			System.out.print("\nEnter new balance: ");
			balance = input.nextFloat();

			input.nextLine();	//Get rid of carriage return!

			currentPos = ranAccts.getFilePointer();
			ranAccts.seek(currentPos-4); //Move back 4 bytes.
			ranAccts.writeFloat(balance);
			System.out.print(
						"\nModify another balance (y/n)? ");
			reply = (input.nextLine()).toLowerCase();
		}while (reply.equals("y"));	//Alternative to method
									//in previous example.
		ranAccts.close();
	}

	public static void showRecord(RandomAccessFile file)
									throws IOException
	{
		file.seek((acctNum-1)*REC_SIZE);
		acctNum = file.readLong();
		surname = readString(file, SURNAME_SIZE);
		initials = readString(file, NUM_INITS);
		balance = file.readFloat();

		System.out.println("Surname:  " + surname);
		System.out.println("Initials: " + initials);
		System.out.printf("Balance:  %.2f %n",balance);
	}

	public static String readString(RandomAccessFile file,
						int fixedSize) throws IOException
	{
		//Set up empty buffer before reading from file...
		StringBuffer buffer = new StringBuffer();

		for (int i=0; i<fixedSize; i++)
		//Read character from file and append to buffer.
			buffer.append(file.readChar());
		return buffer.toString(); //Convert into String.
	}
}
