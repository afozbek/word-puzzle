import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.media.*;

public class MediaPlayer extends JFrame
		implements ActionListener, ControllerListener
{
	private JLabel prompt;
	private JTextField fileName;
	private JPanel inputPanel;
	private File file;
	private Player player;

	public static void main(String args[])
   	{
      	MediaPlayer frame = new MediaPlayer();

		frame.setSize(600, 400);
      	frame.setVisible(true);

      	frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   public MediaPlayer ()
   {
		setTitle( "Java Media Player Demo" );

		inputPanel = new JPanel();
      	prompt = new JLabel("Audio/video file name: ");
		fileName = new JTextField(25);
		inputPanel.add(prompt);
		inputPanel.add(fileName);
		add(inputPanel, BorderLayout.NORTH);

		fileName.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event)
   	{
		try
		{
          	getFile();
			createPlayer();
		}
		catch(FileNotFoundException fnfEx)
		{
			JOptionPane.showMessageDialog(this,
				"File not found!", "Invalid file name",
				JOptionPane.ERROR_MESSAGE);
		}
		catch (Exception ex)
		{
         	JOptionPane.showMessageDialog( this,
            	"Unable to load file!", "Invalid file type",
           		 JOptionPane.ERROR_MESSAGE );
     	}
	}

   	private void getFile() throws FileNotFoundException
   	{
		file = new File(fileName.getText());
		if (!file.exists())
			throw new FileNotFoundException();
	}


   	private void createPlayer() throws Exception
   	{
		player = Manager.createPlayer(file.toURL());
		player.addControllerListener(this);
		player.start();
		fileName.setEnabled(false);
	}

	public void controllerUpdate(ControllerEvent event)
	{
		Container pane = getContentPane();

       	if (event instanceof RealizeCompleteEvent)
		{
			Component visualComponent =
               			player.getVisualComponent();

     		if (visualComponent != null)
					pane.add(visualComponent, BorderLayout.CENTER);

         	Component controlsComponent =
               			player.getControlPanelComponent();

         	if (controlsComponent != null)
					pane.add(controlsComponent, BorderLayout.SOUTH);

			pane.doLayout();
		}
	}
}


