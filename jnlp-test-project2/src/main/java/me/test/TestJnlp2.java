package me.test;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class TestJnlp2 {
	
	private static final Logger log = LoggerFactory.getLogger(TestJnlp2.class);
	
  public static void main(String args[]) {
  	
  	log.debug("Jnlp webstart test");
  	
  	if (args.length > 0) {
  		log.info("args: {}", Arrays.toString(args));
  	}
  	
    JFrame frame = new JFrame("Jnlp webstart test2");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JLabel label = new JLabel();
    Container content = frame.getContentPane();
    content.add(label, BorderLayout.CENTER);
    label.setText("Jnln Hello Word");
 
 
    JButton button = new JButton("Button");
 
    content.add(button, BorderLayout.SOUTH);
    frame.pack();
    frame.setVisible(true);
  }
}