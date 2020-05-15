import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

public class SimpleSound extends JFrame
							implements ActionListener
{
	private AudioClip clip;
	private JButton play, stop, loop;
	private JPanel buttonPanel;

	public static void main(String[] args)
	{
		SimpleSound frame = new SimpleSound();

		frame.setSize(300,200);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public SimpleSound()
	{
		setTitle("Simple Sound Demo");

		try
		{
			clip = Applet.newAudioClip(
					new URL("file:///c:/Sounds/cuckoo.au"));
		}
		catch(MalformedURLException muEx)
		{
			System.out.println("*** Invalid URL! ***");
			System.exit(1);
		}

		play = new JButton("Play");
		play.addActionListener(this);
		stop = new JButton("Stop");
		stop.addActionListener(this);
		loop = new JButton("Loop");
		loop.addActionListener(this);

		buttonPanel = new JPanel();
		buttonPanel.add(play);
		buttonPanel.add(stop);
		buttonPanel.add(loop);

		add(buttonPanel,BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == play)
			clip.play();
		if (event.getSource() == stop)
			clip.stop();
		if (event.getSource() == loop)
			clip.loop();
	}
}

