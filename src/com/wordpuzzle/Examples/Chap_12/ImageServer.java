import java.io.*;
import java.net.*;
import javax.swing.*;

public class ImageServer
{
	private static ServerSocket serverSocket;
	private static final int PORT = 1234;

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

		do
		{
			try
			{
				Socket link = serverSocket.accept();

				ObjectOutputStream outStream =
					new ObjectOutputStream(link.getOutputStream());
				outStream.writeObject(new ImageIcon("beesting.jpg"));
				outStream.flush();
			}
			catch(IOException ioEx)
			{
				ioEx.printStackTrace();
			}
		}while (true);
	}
}

