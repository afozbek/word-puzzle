import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class MediaClient
{
	private static InetAddress host;
	private static final int PORT = 1234;

	public static void main(String[] args)
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

		try
		{
			Socket link = new Socket(host,PORT);

			ObjectInputStream inStream = new ObjectInputStream(
											link.getInputStream());

			PrintWriter outStream = new PrintWriter(
						link.getOutputStream(),true);

			//Set up stream for keyboard entry...
			Scanner userEntry = new Scanner(System.in);

			System.out.print("Enter request (IMAGE/SOUND): ");
			String message =  userEntry.nextLine();
			while(!message.equals("IMAGE") && !message.equals("SOUND"))
			{
				System.out.println("\nTry again!\n");
				System.out.print("Enter request (IMAGE/SOUND): ");
				message =  userEntry.nextLine();
			}
			outStream.println(message);

			getFile(inStream,message);

			link.close();
		}
		catch(IOException ioEx)
		{
			ioEx.printStackTrace();
		}
		catch(ClassNotFoundException cnfEx)
		{
			cnfEx.printStackTrace();
		}
	}

	private static void getFile(ObjectInputStream inStream,
										String fileType)
						throws IOException, ClassNotFoundException
	{
		byte[] byteArray = (byte[])inStream.readObject();
      	FileOutputStream mediaStream;

		if (fileType.equals("IMAGE"))
			//Step 5...
			mediaStream = new FileOutputStream("image.gif");
		else
		 	//Must be a sound file...
			//Step 5...
			mediaStream = new FileOutputStream("sound.au");

		//Step 6...
		mediaStream.write(byteArray);
	}
}

