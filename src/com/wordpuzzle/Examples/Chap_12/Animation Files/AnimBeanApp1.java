import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import animBeans.AnimBean1;   //Note this inclusion.

public class AnimBeanApp1 extends JFrame
{
   public static void main(String[] args)
   {
      AnimBeanApp1 frame = new AnimBeanApp1();

      frame.setSize(150,150);
      frame.setVisible(true);

      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   public AnimBeanApp1()
   {
      AnimBean1 sequence = new AnimBean1();

      //Add the bean to application frame...
      add(sequence);
   }
}
