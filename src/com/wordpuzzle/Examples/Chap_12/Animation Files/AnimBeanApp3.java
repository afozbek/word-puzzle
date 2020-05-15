import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.beans.*;
import animBeans.AnimBean4;

public class AnimBeanApp3 extends JFrame
	implements ActionListener,PropertyChangeListener
{
	private AnimBean4 sequence;
	private JPanel speedControl, imageControl;
	private JLabel delayPrompt, imagePrompt;
	private JTextField delay, imageName;

	public static void main(String[] args)
	{
		AnimBeanApp3 frame = new AnimBeanApp3();

		frame.setSize(150,250);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public AnimBeanApp3()
	{
		sequence = new AnimBean4();
		sequence.addPropertyChangeListener(this);
		setTitle(sequence.getImageName());
		speedControl = new JPanel();
		delayPrompt = new JLabel("Delay(ms): ");
		delay = new JTextField(4);
		imageControl = new JPanel();
		imagePrompt = new JLabel("Image: ");
		imageName = new JTextField(8);

		add(sequence, BorderLayout.NORTH);
		speedControl.add(delayPrompt);
		speedControl.add(delay);
		delay.addActionListener(this);
		add(speedControl, BorderLayout.CENTER);

		imageControl.add(imagePrompt);
		imageControl.add(imageName);
		imageName.addActionListener(this);
		add(imageControl, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == delay)
		{
			 //<Return> key pressed at end of entry into
			 //delay text field, so reset delay...
			 int pause = Integer.parseInt(delay.getText());
			 sequence.setDelay(pause);
			 delay.setText("");
		}
		else
		{
			 //<Return> key must have been pressed at end of
			 //entry into image name text field, so change
			 //animation...
			 sequence.setImageName(imageName.getText());
			 imageName.setText("");
		}
	}

	public void propertyChange(PropertyChangeEvent event)
	{
		setTitle(sequence.getImageName());
	}
}
