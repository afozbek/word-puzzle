import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ImageClient extends JFrame
{
	private  InetAddress host;
	private  final int PORT = 1234;
	private	ImageIcon image;


	public static void main(String[] args)
	{
		ImageClient1 pictureFrame = new ImageClient1();

		pictureFrame.setSize(340,315);
		pictureFrame.setVisible(true);
		pictureFrame.setDefaultCloseOperation(
										EXIT_ON_CLOSE);
	}

	public ImageClient1()
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

			ObjectInputStream inStream =
				new ObjectInputStream(link.getInputStream());
			image = (ImageIcon)inStream.readObject();
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

		repaint();
	}

	public void paint(Graphics g)
	{
		image.paintIcon(this,g,0,0);
	}
}



