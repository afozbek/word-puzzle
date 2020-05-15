import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

public class SimpleSoundApplet2 extends JApplet
						implements ActionListener
{
	private AudioClip clip;
	private JButton play, stop, loop;
	private JPanel buttonPanel;

	public void init()
	{
		try
		{
			clip = getAudioClip(new URL(
					getDocumentBase(), "cuckoo.wav"));
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

	public void stop()
	{
		clip.stop();	//Prevents sound from continuing
	}					//after applet has been stopped.

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


