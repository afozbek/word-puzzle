import java.io.*;
import java.net.*;
import javax.swing.*;

public class MediaServer
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

				Scanner inStream =
						new Scanner(link.getInputStream());

				ObjectOutputStream outStream =
					new ObjectOutputStream(link.getOutputStream());

				String message = inStream.nextLine();

				if (message.equals("IMAGE"))
					sendFile("beesting.jpg", outStream);
				if (message.equals("SOUND"))
					sendFile("cuckoo.au", outStream);
			}
			catch(IOException ioEx)
			{
				ioEx.printStackTrace();
			}
		}while (true);
	}

	private static void sendFile(String fileName,
				ObjectOutputStream outStream) throws IOException
	{
		FileInputStream fileIn = new FileInputStream(fileName);
		long fileLen =  (new File(fileName)).length();
		int intFileLen = (int)fileLen;
		byte[] byteArray = new byte[intFileLen];
		fileIn.read(byteArray);
		fileIn.close();
		outStream.writeObject(byteArray);
		outStream.flush();
	}
}

