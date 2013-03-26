package me.test;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class TestJnlp1 {
	
	private static final Logger log = LoggerFactory.getLogger(TestJnlp1.class);
	
	private static final ExecutorService executorService = Executors.newSingleThreadExecutor();
	
  public static void main(String args[]) {
  	
  	log.debug("Jnlp webstart test");
  	
  	if (args.length > 0) {
  		log.info("args: {}", Arrays.toString(args));
  	}
  	
    JFrame frame = new JFrame("Jnlp webstart test1");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    JLabel label = new JLabel();
    Container content = frame.getContentPane();
    content.add(label, BorderLayout.CENTER);
    label.setText("Jnln Hello Word");
 
    Container btnBar = new Container();
    btnBar.setLayout(new GridBagLayout());
 
    JButton btnWork = new JButton("Work 2s");
    JButton btnClose = new JButton("Close");
 
    btnBar.add(btnWork);
    btnBar.add(btnClose);
    
    addListeners(btnWork, btnClose, frame);
    
    content.add(btnBar, BorderLayout.SOUTH);
    frame.pack();
    frame.setVisible(true);
  }

	private static void addListeners(final JButton btnWork, final JButton btnClose, final JFrame frame) {
		

		
		btnClose.addActionListener(new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		btnWork.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnWork.setEnabled(false);
//				btnClose.setEnabled(false);
				if (e.getSource() instanceof Component) {
					Component btn = (Component) e.getSource();
					btn.requestFocus();
				}
				
				
				executorService.submit(new Runnable() {
					
					@Override
					public void run() {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
						
						SwingUtilities.invokeLater(new Runnable() {
					        public void run() {
					        	btnWork.setEnabled(true);
								btnClose.setEnabled(true);
					        }
						});
					}
				});
				
			}
		});
		
		KeyStroke enterStroke1 = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
		KeyStroke spaceStroke1 = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false);
		
		KeyStroke enterStroke2 = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true);
		KeyStroke spaceStroke2 = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true);
		
		btnClose.registerKeyboardAction(btnClose.getActionForKeyStroke(spaceStroke1), enterStroke1, JComponent.WHEN_FOCUSED);
		btnClose.registerKeyboardAction(btnClose.getActionForKeyStroke(spaceStroke2), enterStroke2, JComponent.WHEN_FOCUSED);
		
		btnWork.registerKeyboardAction(btnWork.getActionForKeyStroke(spaceStroke1), enterStroke1, JComponent.WHEN_FOCUSED);
		btnWork.registerKeyboardAction(btnWork.getActionForKeyStroke(spaceStroke2), enterStroke2, JComponent.WHEN_FOCUSED);
		

	}
}