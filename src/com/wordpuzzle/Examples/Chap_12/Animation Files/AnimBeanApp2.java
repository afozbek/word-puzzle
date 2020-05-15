import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import animBeans.AnimBean2;

public class AnimBeanApp2 extends JFrame
							implements ActionListener
{
   private AnimBean2 sequence;
   private JPanel speedControl, imageControl;
   private JLabel delayPrompt, imagePrompt;
   private JTextField delay, imageName;

   public static void main(String[] args)
   {
      AnimBeanApp2 frame = new AnimBeanApp2();

      frame.setSize(150,250);
      frame.setVisible(true);

      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   public AnimBeanApp2()
   {
      sequence = new AnimBean2();
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
}
